package com.soundcheck.factory.exceptions;

public class NoUniqueQualifyingBeanForTypeException extends RuntimeException {
  public <T> NoUniqueQualifyingBeanForTypeException(Class<T> clazz) {
    super("No unique qualifying bean of type: " + clazz.getName());
  }
}
