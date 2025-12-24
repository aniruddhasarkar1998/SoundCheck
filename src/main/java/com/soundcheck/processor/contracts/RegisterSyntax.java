package com.soundcheck.processor.contracts;

import com.soundcheck.raag.models.Configurations;
import com.soundcheck.raag.models.Syntax;

public interface RegisterSyntax {
  String register(Syntax syntax, Configurations configurations,
                  String outFileName, String filePath);
}
