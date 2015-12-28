package com.sun.utils;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

public class AnalyzerUtil {

	public static void displayToken(String str, Analyzer a){
		try{
			TokenStream stream = a.tokenStream("content", new StringReader(str));
			CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
			while(stream.incrementToken()){
				System.out.print("[" + cta + "]");
			}
			System.out.println();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void displayAllTokenInfo(String str, Analyzer a){
		try{
			TokenStream stream = a.tokenStream("content", new StringReader(str));
			PositionIncrementAttribute pia = stream.addAttribute(PositionIncrementAttribute.class);
			OffsetAttribute oa = stream.addAttribute(OffsetAttribute.class);
			CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
			
			while(stream.incrementToken()){
				System.out.print(pia.getPositionIncrement()+ ":" + cta + "["+oa.startOffset()+"--"+oa.endOffset()+"]\n");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
