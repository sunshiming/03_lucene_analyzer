package com.sun.utils;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class AnalyzerUtil {

	public static void displayToken(String str, Analyzer a){
		try{
			TokenStream stream = a.tokenStream("content", new StringReader(str));
			CharTermAttribute cta = stream.getAttribute(CharTermAttribute.class);
			while(stream.incrementToken()){
				System.out.print("[" + cta + "]");
			}
			System.out.println();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
