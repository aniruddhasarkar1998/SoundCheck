package com.soundcheck.processor;

import com.soundcheck.factory.annotations.Autowired;
import com.soundcheck.factory.annotations.Component;
import com.soundcheck.factory.enums.LoadType;
import com.soundcheck.processor.contracts.*;
import com.soundcheck.raag.models.Configurations;
import com.soundcheck.listeners.error.ErrorHandler;
import com.soundcheck.raag.models.Raag;
import com.soundcheck.raag.models.Syntax;
import com.soundcheck.utils.ParserUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Component(loadType = LoadType.LAZY)
public class RaagBuilderImpl implements RaagBuilder {

  @Autowired
  private ReadFile reader;

  @Autowired
  private Parser parser;

  @Autowired
  private CheckSyntaxImpl checkSyntax;

  @Autowired
  private TransformationImpl transformation;

  @Autowired
  private Flusher flusher;

  @Autowired
  private RegisterSyntax registerSyntax;

  @Override
  public Raag buildRaag(String filePath) {
    Raag raag = Raag.builder().build();
    try {
      String content = reader.readFile(filePath);
      raag = buildRaag(content, filePath);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
    return raag;
  }

  @Override
  public Raag buildRaag(String content, String filePath) throws IOException {
    Raag raag = parser.parse(content);
    if (ErrorHandler.hasErrorOccurred()) {
      return Raag.builder().build();
    }

    Syntax syntax = raag.getSyntax();
    Configurations configurations = raag.getConfigurations();

    System.out.println(ParserUtils.getStringForSyntax(syntax.getDerivations(), "Start"));
    System.out.println("=======================================================================================================");

    String low = configurations.getLow();
    String high = configurations.getHigh();
    String outFileName = configurations.getOutFileName();
    String start = configurations.getStart();

    Map<String, Distribution> derivations = syntax.getDerivations();

    if (!derivations.containsKey(start)) {
      String errMsg = "mentioned start symbol - \"start: " + start + "\" does not have a rule";
      if (ErrorHandler.isCallFromClient()) {
        ErrorHandler.setErrorOccurred(true);
        ErrorHandler.setMessages(
            ErrorHandler.getMessages()
                + "\n" + errMsg
        );
        return Raag.builder().build();
      } else {
        System.err.println(errMsg);
        System.exit(-1);
      }
    }

    checkSyntax.check(content, derivations);
    transform(low, high, syntax);

    System.out.println(ParserUtils.getStringForSyntax(syntax.getDerivations(), start));
    System.out.println("=======================================================================================================");

    if (ErrorHandler.hasErrorOccurred()) {
      return Raag.builder().build();
    }

    if (filePath != null) registerSyntax.register(syntax, configurations, outFileName, filePath);
    checkSyntax.check(content, derivations);

    if (ErrorHandler.hasErrorOccurred()) {
      return Raag.builder().build();
    }

    return Raag.builder().setConfigurations(configurations).setSyntax(syntax).build();
  }

  private void transform(String low, String high, Syntax syntax) {
    Set<String> flushSet = transformation.transform(syntax, low, high);
    flusher.flush(syntax.getDerivations(), flushSet);
  }
}