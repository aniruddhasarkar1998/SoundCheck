package com.soundcheck.app;

import com.soundcheck.factory.context.FactoryContext;

import java.util.function.Consumer;

public class SoundCheckApplication {

  public static void launch(String basePackage, Consumer<Object[]> player, String filePath, boolean doPlay) {
    FactoryContext.init(basePackage, player, filePath, doPlay);
  }

}
