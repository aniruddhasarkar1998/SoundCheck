package com.soundcheck;

import com.soundcheck.app.SoundCheckApplication;
import com.soundcheck.launcher.Launcher;

public class Play {
  public static void main(String[] args) {
//    SoundCheckApplication.launch("com.soundcheck", Launcher::play,
//        "src/test/raag_files/miyan_ki_malhar/test3/mkm.raag", true);
		SoundCheckApplication.launch("com.soundcheck", Launcher::play, args[0], Boolean.parseBoolean(args[1]));
  }
}
