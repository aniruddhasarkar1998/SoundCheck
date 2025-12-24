package com.soundcheck.listeners;

import com.soundcheck.listeners.error.ErrorHandler;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class RaagErrorListener extends ConsoleErrorListener {
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                          int charPositionInLine, String msg, RecognitionException e) {
//        line -= 2;
    if (!ErrorHandler.isCallFromClient()) {
      System.err.println("line " + line + ":" + " " + msg);
      System.exit(-1);
    } else {
      ErrorHandler.setMessages(
          ErrorHandler.getMessages()
              + "line " + line + ":" + " " + msg + "\n"
      );
      ErrorHandler.setErrorOccurred(true);
    }
  }
}