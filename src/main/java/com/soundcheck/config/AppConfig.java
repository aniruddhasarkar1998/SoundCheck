package com.soundcheck.config;

import com.soundcheck.factory.annotations.Bean;
import com.soundcheck.factory.annotations.FactoryConfig;
import com.soundcheck.player.transformers.comb2seq.CombinatorToSequence;
import com.soundcheck.player.transformers.comb2seq.CombinatorToSequenceImpl;
import com.soundcheck.processor.RaagBuilderImpl;
import com.soundcheck.processor.contracts.RaagBuilder;

@FactoryConfig(basePackages = {"com.soundcheck"})
public class AppConfig {

  @Bean
  public CombinatorToSequence comparatorToSequence() {
    return new CombinatorToSequenceImpl();
  }

	@Bean
	public RaagBuilder raagBuilder() {
		return new RaagBuilderImpl();
	}

}
