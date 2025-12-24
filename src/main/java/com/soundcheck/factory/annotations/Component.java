package com.soundcheck.factory.annotations;

import com.soundcheck.factory.enums.LoadType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
  public LoadType loadType() default LoadType.EAGER;
}
