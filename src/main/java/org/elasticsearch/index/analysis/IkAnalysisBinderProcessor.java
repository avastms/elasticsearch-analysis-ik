package org.elasticsearch.index.analysis;


public class IkAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override public void processAnalyzers(AnalyzersBindings analyzersBindings) {
        analyzersBindings.processAnalyzer("ik", IkAnalyzerProvider.class);
        analyzersBindings.processAnalyzer("ik_smart", IkSmartAnalyzerProvider.class);
        analyzersBindings.processAnalyzer("ik_max_word", IkMaxWordAnalyzerProvider.class);
//        super.processAnalyzers(analyzersBindings);
    }


    @Override
    public void processTokenizers(TokenizersBindings tokenizersBindings) {
      tokenizersBindings.processTokenizer("ik", IkTokenizerFactory.class);
      tokenizersBindings.processTokenizer("ik_smart", IkSmartTokenizerFactory.class);
      tokenizersBindings.processTokenizer("ik_max_word", IkMaxWordTokenizerFactory.class);
//      super.processTokenizers(tokenizersBindings);
    }
}
