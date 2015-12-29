package com.sun.analyzer;

import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;

public class MyStopAnalyzer extends Analyzer{
	private Set stops;
	
	public MyStopAnalyzer(String[] sws){
		//会自动将字符串数组转换为Set
		stops = StopFilter.makeStopSet(Version.LUCENE_35, sws, true);
		//将原有的停用词加到现有的停用词集合中
		stops.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// TODO Auto-generated method stub
		return new StopFilter(Version.LUCENE_35, 
				new LowerCaseFilter(Version.LUCENE_35, 
						new LetterTokenizer(Version.LUCENE_35, reader)), stops);
	}

}
