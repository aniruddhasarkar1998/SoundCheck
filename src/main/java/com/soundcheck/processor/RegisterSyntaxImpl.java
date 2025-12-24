package com.soundcheck.processor;

import com.soundcheck.factory.annotations.Component;
import com.soundcheck.processor.contracts.RegisterSyntax;
import com.soundcheck.raag.models.Configurations;
import com.soundcheck.raag.models.Syntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
public class RegisterSyntaxImpl implements RegisterSyntax {

  @Override
  public String register(Syntax syntax, Configurations configurations,
                         String outFileName, String filePath) {
    String str = configurations.toString() + syntax.toString();
    String folderPath = new File(filePath).getParent();
    String fileName = outFileName == "" || outFileName == null ? "final" : outFileName;
    System.out.println(fileName + " " + outFileName);
    String newFilePath = folderPath + "\\" + fileName + ".syntax";

    try {
      Files.write(
          Paths.get(newFilePath),
          str.getBytes(),
          StandardOpenOption.CREATE,
          StandardOpenOption.TRUNCATE_EXISTING
      );
    } catch (IOException e) {
      e.printStackTrace();
    }

    return str;
  }
}
