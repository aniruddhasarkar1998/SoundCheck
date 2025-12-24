package com.soundcheck.listeners.error;

public class ErrorHandler {
  private static boolean callFromClient = false;
  private static String messages = "";
  private static boolean errorOccurred = false;

  public static boolean isCallFromClient() {
    return callFromClient;
  }

  public static void setCallFromClient(boolean callFromClient) {
    ErrorHandler.callFromClient = callFromClient;
  }

  public static String getMessages() {
    return messages;
  }

  public static void setMessages(String messages) {
    ErrorHandler.messages = messages;
  }

  public static boolean hasErrorOccurred() {
    return errorOccurred;
  }

  public static void setErrorOccurred(boolean errorOccurred) {
    ErrorHandler.errorOccurred = errorOccurred;
  }
}
