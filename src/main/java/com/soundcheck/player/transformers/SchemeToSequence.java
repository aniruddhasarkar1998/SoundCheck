package com.soundcheck.player.transformers;

import java.util.List;

public interface SchemeToSequence {
  List<String> getSequenceFromSimpleScheme(
      String scheme, String swar,
      List<String> ascent,
      List<String> descent
  );
}
