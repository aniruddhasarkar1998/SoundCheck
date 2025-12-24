// Generated from C:/Users/Aniruddha Sarkar/Documents/github/SoundCheck/src/main/resources\Raag.g4 by ANTLR 4.8
package com.soundcheck.generated;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RaagParser}.
 */
public interface RaagListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link RaagParser#syntax}.
   *
   * @param ctx the parse tree
   */
  void enterSyntax(RaagParser.SyntaxContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#syntax}.
   *
   * @param ctx the parse tree
   */
  void exitSyntax(RaagParser.SyntaxContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#declarations}.
   *
   * @param ctx the parse tree
   */
  void enterDeclarations(RaagParser.DeclarationsContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#declarations}.
   *
   * @param ctx the parse tree
   */
  void exitDeclarations(RaagParser.DeclarationsContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#optionalDeclarations}.
   *
   * @param ctx the parse tree
   */
  void enterOptionalDeclarations(RaagParser.OptionalDeclarationsContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#optionalDeclarations}.
   *
   * @param ctx the parse tree
   */
  void exitOptionalDeclarations(RaagParser.OptionalDeclarationsContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#outFileName}.
   *
   * @param ctx the parse tree
   */
  void enterOutFileName(RaagParser.OutFileNameContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#outFileName}.
   *
   * @param ctx the parse tree
   */
  void exitOutFileName(RaagParser.OutFileNameContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#start}.
   *
   * @param ctx the parse tree
   */
  void enterStart(RaagParser.StartContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#start}.
   *
   * @param ctx the parse tree
   */
  void exitStart(RaagParser.StartContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#baseFreq}.
   *
   * @param ctx the parse tree
   */
  void enterBaseFreq(RaagParser.BaseFreqContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#baseFreq}.
   *
   * @param ctx the parse tree
   */
  void exitBaseFreq(RaagParser.BaseFreqContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#beatsPerCycle}.
   *
   * @param ctx the parse tree
   */
  void enterBeatsPerCycle(RaagParser.BeatsPerCycleContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#beatsPerCycle}.
   *
   * @param ctx the parse tree
   */
  void exitBeatsPerCycle(RaagParser.BeatsPerCycleContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#numCycles}.
   *
   * @param ctx the parse tree
   */
  void enterNumCycles(RaagParser.NumCyclesContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#numCycles}.
   *
   * @param ctx the parse tree
   */
  void exitNumCycles(RaagParser.NumCyclesContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#low}.
   *
   * @param ctx the parse tree
   */
  void enterLow(RaagParser.LowContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#low}.
   *
   * @param ctx the parse tree
   */
  void exitLow(RaagParser.LowContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#high}.
   *
   * @param ctx the parse tree
   */
  void enterHigh(RaagParser.HighContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#high}.
   *
   * @param ctx the parse tree
   */
  void exitHigh(RaagParser.HighContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#msec}.
   *
   * @param ctx the parse tree
   */
  void enterMsec(RaagParser.MsecContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#msec}.
   *
   * @param ctx the parse tree
   */
  void exitMsec(RaagParser.MsecContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#volume}.
   *
   * @param ctx the parse tree
   */
  void enterVolume(RaagParser.VolumeContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#volume}.
   *
   * @param ctx the parse tree
   */
  void exitVolume(RaagParser.VolumeContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#playFileName}.
   *
   * @param ctx the parse tree
   */
  void enterPlayFileName(RaagParser.PlayFileNameContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#playFileName}.
   *
   * @param ctx the parse tree
   */
  void exitPlayFileName(RaagParser.PlayFileNameContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#wavFileName}.
   *
   * @param ctx the parse tree
   */
  void enterWavFileName(RaagParser.WavFileNameContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#wavFileName}.
   *
   * @param ctx the parse tree
   */
  void exitWavFileName(RaagParser.WavFileNameContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#unit}.
   *
   * @param ctx the parse tree
   */
  void enterUnit(RaagParser.UnitContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#unit}.
   *
   * @param ctx the parse tree
   */
  void exitUnit(RaagParser.UnitContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#rule}.
   *
   * @param ctx the parse tree
   */
  void enterRule(RaagParser.RuleContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#rule}.
   *
   * @param ctx the parse tree
   */
  void exitRule(RaagParser.RuleContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#derivation}.
   *
   * @param ctx the parse tree
   */
  void enterDerivation(RaagParser.DerivationContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#derivation}.
   *
   * @param ctx the parse tree
   */
  void exitDerivation(RaagParser.DerivationContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterExpression(RaagParser.ExpressionContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitExpression(RaagParser.ExpressionContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#combinator}.
   *
   * @param ctx the parse tree
   */
  void enterCombinator(RaagParser.CombinatorContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#combinator}.
   *
   * @param ctx the parse tree
   */
  void exitCombinator(RaagParser.CombinatorContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#sequence}.
   *
   * @param ctx the parse tree
   */
  void enterSequence(RaagParser.SequenceContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#sequence}.
   *
   * @param ctx the parse tree
   */
  void exitSequence(RaagParser.SequenceContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#option}.
   *
   * @param ctx the parse tree
   */
  void enterOption(RaagParser.OptionContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#option}.
   *
   * @param ctx the parse tree
   */
  void exitOption(RaagParser.OptionContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#schemeCall}.
   *
   * @param ctx the parse tree
   */
  void enterSchemeCall(RaagParser.SchemeCallContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#schemeCall}.
   *
   * @param ctx the parse tree
   */
  void exitSchemeCall(RaagParser.SchemeCallContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#schemeNameCalled}.
   *
   * @param ctx the parse tree
   */
  void enterSchemeNameCalled(RaagParser.SchemeNameCalledContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#schemeNameCalled}.
   *
   * @param ctx the parse tree
   */
  void exitSchemeNameCalled(RaagParser.SchemeNameCalledContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#variable}.
   *
   * @param ctx the parse tree
   */
  void enterVariable(RaagParser.VariableContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#variable}.
   *
   * @param ctx the parse tree
   */
  void exitVariable(RaagParser.VariableContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#probability}.
   *
   * @param ctx the parse tree
   */
  void enterProbability(RaagParser.ProbabilityContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#probability}.
   *
   * @param ctx the parse tree
   */
  void exitProbability(RaagParser.ProbabilityContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#schemeBlock}.
   *
   * @param ctx the parse tree
   */
  void enterSchemeBlock(RaagParser.SchemeBlockContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#schemeBlock}.
   *
   * @param ctx the parse tree
   */
  void exitSchemeBlock(RaagParser.SchemeBlockContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#octave}.
   *
   * @param ctx the parse tree
   */
  void enterOctave(RaagParser.OctaveContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#octave}.
   *
   * @param ctx the parse tree
   */
  void exitOctave(RaagParser.OctaveContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#octaveOption}.
   *
   * @param ctx the parse tree
   */
  void enterOctaveOption(RaagParser.OctaveOptionContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#octaveOption}.
   *
   * @param ctx the parse tree
   */
  void exitOctaveOption(RaagParser.OctaveOptionContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#ascent}.
   *
   * @param ctx the parse tree
   */
  void enterAscent(RaagParser.AscentContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#ascent}.
   *
   * @param ctx the parse tree
   */
  void exitAscent(RaagParser.AscentContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#descent}.
   *
   * @param ctx the parse tree
   */
  void enterDescent(RaagParser.DescentContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#descent}.
   *
   * @param ctx the parse tree
   */
  void exitDescent(RaagParser.DescentContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#arohan}.
   *
   * @param ctx the parse tree
   */
  void enterArohan(RaagParser.ArohanContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#arohan}.
   *
   * @param ctx the parse tree
   */
  void exitArohan(RaagParser.ArohanContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#avarohan}.
   *
   * @param ctx the parse tree
   */
  void enterAvarohan(RaagParser.AvarohanContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#avarohan}.
   *
   * @param ctx the parse tree
   */
  void exitAvarohan(RaagParser.AvarohanContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#schemes}.
   *
   * @param ctx the parse tree
   */
  void enterSchemes(RaagParser.SchemesContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#schemes}.
   *
   * @param ctx the parse tree
   */
  void exitSchemes(RaagParser.SchemesContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#scheme}.
   *
   * @param ctx the parse tree
   */
  void enterScheme(RaagParser.SchemeContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#scheme}.
   *
   * @param ctx the parse tree
   */
  void exitScheme(RaagParser.SchemeContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#schemeName}.
   *
   * @param ctx the parse tree
   */
  void enterSchemeName(RaagParser.SchemeNameContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#schemeName}.
   *
   * @param ctx the parse tree
   */
  void exitSchemeName(RaagParser.SchemeNameContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#schemeDerivation}.
   *
   * @param ctx the parse tree
   */
  void enterSchemeDerivation(RaagParser.SchemeDerivationContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#schemeDerivation}.
   *
   * @param ctx the parse tree
   */
  void exitSchemeDerivation(RaagParser.SchemeDerivationContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#schemeExpression}.
   *
   * @param ctx the parse tree
   */
  void enterSchemeExpression(RaagParser.SchemeExpressionContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#schemeExpression}.
   *
   * @param ctx the parse tree
   */
  void exitSchemeExpression(RaagParser.SchemeExpressionContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#schemeProbability}.
   *
   * @param ctx the parse tree
   */
  void enterSchemeProbability(RaagParser.SchemeProbabilityContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#schemeProbability}.
   *
   * @param ctx the parse tree
   */
  void exitSchemeProbability(RaagParser.SchemeProbabilityContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#schemeOption}.
   *
   * @param ctx the parse tree
   */
  void enterSchemeOption(RaagParser.SchemeOptionContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#schemeOption}.
   *
   * @param ctx the parse tree
   */
  void exitSchemeOption(RaagParser.SchemeOptionContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#combinatorScheme}.
   *
   * @param ctx the parse tree
   */
  void enterCombinatorScheme(RaagParser.CombinatorSchemeContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#combinatorScheme}.
   *
   * @param ctx the parse tree
   */
  void exitCombinatorScheme(RaagParser.CombinatorSchemeContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#simpleScheme}.
   *
   * @param ctx the parse tree
   */
  void enterSimpleScheme(RaagParser.SimpleSchemeContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#simpleScheme}.
   *
   * @param ctx the parse tree
   */
  void exitSimpleScheme(RaagParser.SimpleSchemeContext ctx);

  /**
   * Enter a parse tree produced by {@link RaagParser#swar}.
   *
   * @param ctx the parse tree
   */
  void enterSwar(RaagParser.SwarContext ctx);

  /**
   * Exit a parse tree produced by {@link RaagParser#swar}.
   *
   * @param ctx the parse tree
   */
  void exitSwar(RaagParser.SwarContext ctx);
}