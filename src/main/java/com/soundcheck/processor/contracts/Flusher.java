package com.soundcheck.processor.contracts;

import com.soundcheck.processor.Distribution;

import java.util.Map;
import java.util.Set;

public interface Flusher {
  void flush(Map<String, Distribution> derivations, Set<String> flushSet);
}
