package com.soundcheck.processor.contracts;

import java.io.IOException;

public interface ReadFile {
  String readFile(String filePath) throws IOException;
}
