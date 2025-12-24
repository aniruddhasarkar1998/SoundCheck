package com.soundcheck.player.transformers;

import com.soundcheck.processor.Distribution;

import java.util.List;

public interface SchemeToDerivation {
  public Distribution<String> transform(
      Distribution<String> schemeDist,
      String swar,
      List<String> ascent,
      List<String> descent
  );
}
