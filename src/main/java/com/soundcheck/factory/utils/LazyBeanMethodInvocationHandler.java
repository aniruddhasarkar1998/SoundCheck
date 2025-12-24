package com.soundcheck.factory.utils;

import com.soundcheck.factory.context.FactoryContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class LazyBeanMethodInvocationHandler<T> implements InvocationHandler {

  private T target;
  private final List<Object> values;
  private final Class<?>[] argsArray;

  public LazyBeanMethodInvocationHandler(T target, List<Object> values, Class<?>... argsArray) {
    this.target = target;
    this.values = values;
    this.argsArray = argsArray;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (target == null) target = (T) method.getDeclaringClass().getConstructor(argsArray).newInstance(values);
    return method.invoke(target, args);
  }

}
