package com.soundcheck.factory.exceptions;

public class NoQualifyingBeanForTypeException extends RuntimeException {
  public <T> NoQualifyingBeanForTypeException(Class<T> clazz) {
    super("No qualifying bean of type: " + clazz.getName());
  }
}
