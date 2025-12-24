package com.soundcheck.processor.contracts;

import com.soundcheck.raag.models.Syntax;

import java.util.Set;

public interface Transformation {
  Set<String> transform(Syntax syntax, String low, String high);
}
