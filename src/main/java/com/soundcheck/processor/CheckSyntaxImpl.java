package com.soundcheck.processor;

import com.soundcheck.factory.annotations.Component;
import com.soundcheck.generated.RaagParser;
import com.soundcheck.listeners.CheckDerivationsListener;
import com.soundcheck.processor.contracts.CheckSyntax;
import com.soundcheck.utils.ParserUtils;

import java.util.Map;

@Component
public class CheckSyntaxImpl implements CheckSyntax {

  private CheckDerivationsListener listener;

  @Override
  public void check(String content, Map<String, Distribution> derivations) {
    listener = new CheckDerivationsListener();
    RaagParser parser = ParserUtils.createParser(content);
    listener.setDerivations(derivations);
    parser.addParseListener(listener);
    parser.syntax();
  }

}
