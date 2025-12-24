package com.soundcheck.factory.context;

import com.soundcheck.factory.annotations.*;
import com.soundcheck.factory.enums.LoadType;
import com.soundcheck.factory.exceptions.BeanCreationException;
import com.soundcheck.factory.exceptions.LazyInitializationException;
import com.soundcheck.factory.exceptions.NoQualifyingBeanForTypeException;
import com.soundcheck.factory.exceptions.NoUniqueQualifyingBeanForTypeException;
import com.soundcheck.factory.utils.FactoryUtils;
import com.soundcheck.factory.utils.LazyBeanMethodInvocationHandler;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FactoryContext {

  /**
   * holds objects against class names
   */
  private static Map<String, Object> objectMap = new ConcurrentHashMap<>();

  /**
   * holds the factory class instance annotated with @FactoryConfig against class names
   */
  private static Map<String, Object> factoryObjectMap = new ConcurrentHashMap<>();

  /**
   * holds initializer methods annotated with @Bean against class names
   */
  private static Map<String, Method> beanToMethodMap = new ConcurrentHashMap<>();

  /**
   * holds the factory class reference annotated with @FactoryConfig against class names
   */
  private static Map<String, Class<?>> beanToFactoryMap = new ConcurrentHashMap<>();

  /**
   * @param consumer    The method that starts the application
   * @param params      The params for <i><b>consumer</b></i>
   * @param basePackage The package with the class annotated with <i><b>@FactoryConfig</b></i>
   * @apiNote This method provides a way for the application to start with <i><b>FactoryContext</b></i> support
   */
  public static void init(String basePackage, Consumer<Object[]> consumer, Object... params) {
    List<String> basePackages = new ArrayList<>();
    Reflections tempReflections = new Reflections(basePackage);

    tempReflections.getTypesAnnotatedWith(FactoryConfig.class).stream().forEach(config -> {
      basePackages.addAll(Arrays.asList(config.getAnnotation(FactoryConfig.class).basePackages()));
      Stream.of(config.getDeclaredMethods()).filter(m -> m.isAnnotationPresent(Bean.class)).forEach(method -> {
        beanToFactoryMap.put(method.getReturnType().getName(), config);
        beanToMethodMap.put(method.getReturnType().getName(), method);
      });
    });

    FactoryUtils.init(basePackages);

    beanToMethodMap.entrySet().stream().filter(entry -> {
      return entry.getValue().getAnnotation(Bean.class).loadType().equals(LoadType.EAGER);
    }).forEach(entry -> {
      objectMap.put(entry.getKey(), createObjectFromExistingBean(entry.getValue().getReturnType()));
    });

    consumer.accept(params);
  }

  /**
   * @param clazz Specified type
   * @param T     the class type for <i><b>clazz</b></i>
   * @return An object of the type <i><b>T</b></i>
   * @throws NoQualifyingBeanForTypeException       If there is no eligible class
   * @throws NoUniqueQualifyingBeanForTypeException If there is more than one eligible class
   */
  public static <T> T getBean(Class<T> clazz) {
    return (T) FactoryUtils.computeIfAbsent(objectMap, clazz.getName(), className -> {
      return beanToMethodMap.containsKey(clazz.getName()) ? createObjectFromExistingBean(clazz)
          : createObject(clazz, new ArrayList<>(), new ArrayList<>());
    });
  }

  /**
   * @param clazz  Specified type
   * @param args   List of parameter types of the target constructor
   * @param values List of values of the target constructor
   * @param T      the class type for <i><b>clazz</b></i>
   * @return An object of the type <i><b>clazz</b></i>
   * @throws NoQualifyingBeanForTypeException       If there is no eligible class with a constructor satisfying the list of <i><b>args</b></i>
   * @throws NoUniqueQualifyingBeanForTypeException If there is more than one eligible class with a constructor satisfying the list of <i><b>args</b></i>
   */
  public static <T> T getBean(Class<T> clazz, List<Class<?>> args, List<Object> values) {
    Object[] valuesArray = values.stream().toArray(Object[]::new);
    return (T) FactoryUtils.computeIfAbsent(objectMap, clazz.getName(), className -> {
      if (beanToMethodMap.containsKey(clazz.getName())) return createObjectFromExistingBean(clazz, valuesArray);
      else return createObject(clazz, args, values);
    });
  }

  /**
   * @param clazz Specified type
   * @param args  List of parameter types of target constructor
   * @param T     The class type for <i><b>clazz</b></i>
   * @return An object of type <i><b>T</b></i>
   * @apiNote This method creates object of type <i><b>T</b></i> from existing <b><i>@Bean</i></b> annotated method of the <b><i>@FactoryConfig</i></b> annotated class.
   */
  private static <T> T createObjectFromExistingBean(Class<T> clazz, Object... args) {
    try {
      T object = (T) beanToMethodMap.get(clazz.getName()).invoke(getFactory(beanToFactoryMap.get(clazz.getName())), args);
      return FactoryUtils.initAutowiredFields(object);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new BeanCreationException(clazz, e);
    }
  }

  public static <T> T getFactory(Class<T> clazz) {
    return (T) FactoryUtils.computeIfAbsent(factoryObjectMap, clazz.getName(),
        className -> createObject(clazz, new ArrayList<>(), new ArrayList<>()));
  }

  /**
   * @param implClass Specified type
   * @param args      List of parameter types of target constructor
   * @param values    List of parameter values of target constructor
   * @param T         The class type for <i><b>T</b></i>
   * @return An object of type <i><b>clazz</b></i>
   * @apiNote This method creates object of type <i><b>T</b></i> if there is no more than one class inheriting this base type with an annotation <i><b>@Component</b></i>
   */
  private static <T> T createObject(Class<T> baseClass, List<Class<?>> args, List<Object> values) {
    Class<?>[] argsArray = args.stream().toArray(Class<?>[]::new);
    Class<?> implClass = baseClass;
    try {
      if (implClass.isInterface() || Modifier.isAbstract(implClass.getModifiers())) {
        implClass = FactoryUtils.getImplementationClass(implClass, argsArray);
      }
      boolean lazyBean = implClass.isAnnotationPresent(Component.class) ?
          implClass.getAnnotation(Component.class).loadType().equals(LoadType.LAZY) : false;
      if (lazyBean && !baseClass.isInterface()) {
        throw new LazyInitializationException(baseClass);
      }
      T object = lazyBean ? (T) Proxy.newProxyInstance(
          implClass.getClassLoader(), new Class<?>[]{baseClass}, new LazyBeanMethodInvocationHandler<>(null, values, argsArray)
      ) : (T) implClass.getConstructor(argsArray).newInstance(values.stream().toArray(Object[]::new));
      return FactoryUtils.initAutowiredFields(object);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new BeanCreationException(implClass, e);
    }
  }

}
