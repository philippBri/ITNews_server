package com.itnews.DAO;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.LowerCaseTokenizer;

/**
 * @author f.brishtan
 * @since 19.11.18.
 */
public class CustomAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String s) {

        return new TokenStreamComponents(new LowerCaseTokenizer());
    }
}
