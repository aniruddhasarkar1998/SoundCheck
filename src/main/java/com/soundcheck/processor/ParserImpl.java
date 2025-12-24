package com.soundcheck.processor;

import com.soundcheck.factory.annotations.Component;
import com.soundcheck.processor.contracts.Parser;
import com.soundcheck.raag.models.Configurations;
import com.soundcheck.generated.RaagParser;
import com.soundcheck.listeners.RaagCustomListener;
import com.soundcheck.listeners.RaagErrorListener;
import com.soundcheck.player.Sargam;
import com.soundcheck.raag.models.Raag;
import com.soundcheck.raag.models.Syntax;
import com.soundcheck.utils.ParserUtils;

import java.util.*;

@Component
public class ParserImpl implements Parser {

  @Override
  public Raag parse(String content) {
    RaagParser parser = ParserUtils.createParser(content);
    RaagCustomListener listener = new RaagCustomListener();
    RaagErrorListener errorListener = new RaagErrorListener();
    parser.addErrorListener(errorListener);
    parser.addParseListener(listener);
    parser.syntax();

    List<String> ascent = listener.getAscent();
    List<String> descent = listener.getDescent();
    Map<String, Distribution> schemes = listener.getSchemes();
    Map<String, Distribution> derivations = listener.getDerivations();
    Configurations configurations = listener.getConfigurations();

    Syntax syntax = Syntax.builder().setAscent(ascent).setDescent(descent).setSchemes(schemes)
        .setDerivations(derivations).setStart(configurations.getStart()).build();

    return Raag.builder().setConfigurations(configurations).setSyntax(syntax).build();
  }

}
