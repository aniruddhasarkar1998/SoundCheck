package com.soundcheck.factory.exceptions;

public class LazyInitializationException extends RuntimeException {
  public <T> LazyInitializationException(Class<T> clazz) {
    super("Lazy initialization not allowed for beans not implementing interfaces: " + clazz.getName());
  }
}
