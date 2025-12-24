package com.soundcheck.logger;

import com.soundcheck.factory.annotations.Component;

import java.util.logging.Logger;

@Component
public class CustomLoggerImpl implements CustomLogger {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  @Override
  public void addLog(String msg) {
    LOGGER.info(msg);
  }
}
