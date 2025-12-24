package com.soundcheck.factory.exceptions;

public class BeanCreationException extends RuntimeException {
  public <T> BeanCreationException(Class<T> clazz, Exception e) {
    super("Error creating bean for class " + clazz.getName() + ": " + e);
  }
}
