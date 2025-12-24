package com.soundcheck.processor.contracts;

import com.soundcheck.processor.Distribution;

import java.util.Map;

public interface CheckSyntax {
  void check(String content, Map<String, Distribution> derivations);
}
