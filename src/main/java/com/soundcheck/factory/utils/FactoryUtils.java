package com.soundcheck.factory.utils;

import com.soundcheck.factory.annotations.Autowired;
import com.soundcheck.factory.annotations.Component;
import com.soundcheck.factory.annotations.Primary;
import com.soundcheck.factory.context.FactoryContext;
import com.soundcheck.factory.enums.LoadType;
import com.soundcheck.factory.exceptions.BeanCreationException;
import com.soundcheck.factory.exceptions.NoQualifyingBeanForTypeException;
import com.soundcheck.factory.exceptions.NoUniqueQualifyingBeanForTypeException;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FactoryUtils {

  private static Reflections reflections;

  private static List<String> basePackages;

  public static void init(List<String> basePackages1) {
    basePackages = basePackages1;
    reflections = getReflections();
  }

  /**
   * @return An Object of Reflections type
   * @apiNote Singleton initializer of Reflections object
   */
  public static Reflections getReflections() {
    if (reflections == null) {
      reflections = new Reflections(new ConfigurationBuilder().forPackages(basePackages.stream().toArray(String[]::new))
          .setScanners(new SubTypesScanner()).setExecutorService(Executors.newFixedThreadPool(4)));
    }
    return reflections;
  }

  /**
   * @param object An object of type <b><i>T</i></b>
   * @param T      The type of <i><b>object</b></i>
   * @return The same <i><b>object</b></i> with all its <i><b>@Autowired</b></i> fields initialized
   */
  public static <T> T initAutowiredFields(T object) {
    Map<String, Object> autowiredFieldNamesToObjects = Arrays.stream(object.getClass().getDeclaredFields())
        .filter(field -> field.isAnnotationPresent(Autowired.class))
        .collect(Collectors.toMap(field -> field.getName(), field -> FactoryContext.getBean(field.getType())));

    // Initializing the @Autowired fields with the above objects.
    Arrays.stream(object.getClass().getDeclaredFields()).filter(
        field -> autowiredFieldNamesToObjects.containsKey(field.getName())
    ).forEach(field -> {
      field.setAccessible(true);
      try {
        field.set(object, autowiredFieldNamesToObjects.get(field.getName()));
      } catch (IllegalAccessException e) {
        throw new BeanCreationException(field.getType(), e);
      }
    });
    return object;
  }

  /**
   * @param clazz
   * @param argsArray The list of types of parameters of the target constructor that is a subclass of <b><i>clazz</i></b>
   * @param T         the type of <i><b>clazz</b></i>
   * @return An object of a subclass of <i><b>clazz</b></i> having the constructor with the parameter types same as <b><i>argsArray</i></b>
   */
  public static <T> Class<? extends T> getImplementationClass(Class<T> clazz, Class<?>[] argsArray) {
    List<Class<? extends T>> subTypes = reflections.getSubTypesOf(clazz).stream()
        .filter(type -> !Modifier.isAbstract(type.getModifiers()))
        .filter(type -> Stream.of(type.getConstructors()).anyMatch(c -> Arrays.equals(c.getParameterTypes(), argsArray)))
        .filter(type -> type.isAnnotationPresent(Component.class))
        .collect(Collectors.toList());

    if (subTypes.stream().anyMatch(type -> type.isAnnotationPresent(Primary.class))) {
      subTypes = subTypes.stream().filter(subType -> subType.isAnnotationPresent(Primary.class))
          .collect(Collectors.toList());
    }

    if (subTypes.size() < 1) {
      throw new NoQualifyingBeanForTypeException(clazz);
    } else if (subTypes.size() > 1) {
      throw new NoUniqueQualifyingBeanForTypeException(clazz);
    }

    return subTypes.get(0);
  }

  /**
   * @param map      A java.util.Map type variable on which the operation will be done.
   * @param key      The key of the <i><b>map</b></i>
   * @param computer A method reference which computes the value of a new <i><b>key</b></i>.
   * @param V        The value type of <i><b>map</b></i>
   * @param K        The key type of <i><b>map</b></i>
   * @return If the <i><b>key</b></i> already exists then the value is returned directly, otherwise the value computed with the <i><b>computer</b></i> is put into the map and then returned.
   */
  public static <V, K> V computeIfAbsent(Map<K, V> map, K key, Function<K, V> computer) {
    if (!map.containsKey(key)) {
      V object = computer.apply(key);
      map.put(key, object);
    }
    return map.get(key);
  }

}
