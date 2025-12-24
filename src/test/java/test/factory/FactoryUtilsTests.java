package test.factory;

import com.soundcheck.factory.annotations.Autowired;
import com.soundcheck.factory.context.FactoryContext;
import com.soundcheck.processor.contracts.RaagBuilder;

import java.util.Arrays;

public class FactoryUtilsTests {

  public static <T> void testBeanCreation(Class<T> clazz, boolean checkInit) {
    FactoryContext.init("com.soundcheck", args -> {
      Object object = FactoryContext.getBean(clazz);
      boolean isInitialized = isProperlyInitialized(object);
      if (!checkInit) {
        System.out.println(clazz.getName() + " is initialized properly");
        return;
      }
      if (isInitialized) System.out.println(clazz.getName() + " is initialized properly");
      else System.out.println(clazz.getName() + " is not initialized properly");
    }, new Object[]{});
  }

  private static boolean isProperlyInitialized(Object object) {
    if (object != null) {
      return Arrays.stream(object.getClass().getDeclaredFields())
          .filter(field -> field.isAnnotationPresent(Autowired.class))
          .noneMatch(field -> {
            field.setAccessible(true);
            try {
              return field.get(object) == null;
            } catch (IllegalAccessException e) {
              throw new RuntimeException(e);
            }
          });
    }
    return false;
  }

  public static void main(String[] args) {
    testBeanCreation(RaagBuilder.class, false);
  }

}
