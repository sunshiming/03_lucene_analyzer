package com.sun.analyzer;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MaxWordSeg;
import com.chenlb.mmseg4j.analysis.MMSegTokenizer;
import com.sun.context.SynonymContext;
import com.sun.filter.MySynonymTokenFilter;

public class MySynonymAnalyzer extends Analyzer {
	
	private SynonymContext synonymContext = null;
	
	public MySynonymAnalyzer(SynonymContext synonymContext) {
		this.synonymContext = synonymContext;
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// TODO Auto-generated method stub
		Dictionary dictionary = Dictionary.getInstance("E:/jar/mmseg4j-1.8.5/data");
		return new MySynonymTokenFilter(new MMSegTokenizer(new MaxWordSeg(dictionary), reader),
				synonymContext);
	}

}
