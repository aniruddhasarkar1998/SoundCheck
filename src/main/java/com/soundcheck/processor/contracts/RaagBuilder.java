package com.soundcheck.processor.contracts;

import com.soundcheck.raag.models.Raag;

import java.io.IOException;

public interface RaagBuilder {

  Raag buildRaag(String filePath);

  Raag buildRaag(String content, String filePath) throws IOException;

}
