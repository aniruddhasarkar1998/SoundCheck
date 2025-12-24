package com.soundcheck.processor.contracts;

import com.soundcheck.raag.models.Raag;

public interface Parser {
  Raag parse(String content);
}
