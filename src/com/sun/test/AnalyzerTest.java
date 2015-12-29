package com.sun.test;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.sun.analyzer.MyStopAnalyzer;
import com.sun.utils.AnalyzerUtil;

public class AnalyzerTest {

	@Test
	public void test01(){
//		Analyzer a1 = new StandardAnalyzer(Version.LUCENE_35);
//		Analyzer a2 = new StopAnalyzer(Version.LUCENE_35);
//		Analyzer a3 = new SimpleAnalyzer(Version.LUCENE_35);
//		Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_35);
		Analyzer a5 = new MMSegAnalyzer(new File("E:/jar/mmseg4j-1.8.5/data"));
		
//		String txt = "This is my house, and I came from shandong laiwu,my email is sunshiming0634@qq.com";
		String str = "我们一起去跳舞莱芜市，人民大会堂";
//		AnalyzerUtil.displayToken(txt, a1);
//		AnalyzerUtil.displayToken(txt, a2);
//		AnalyzerUtil.displayToken(txt, a3);
//		AnalyzerUtil.displayToken(txt, a4);
		AnalyzerUtil.displayToken(str, a5);
	}
	
	@Test
	public void test02(){
		Analyzer a1 = new StandardAnalyzer(Version.LUCENE_35);
		Analyzer a2 = new StopAnalyzer(Version.LUCENE_35);
		Analyzer a3 = new SimpleAnalyzer(Version.LUCENE_35);
		Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_35);
		
		String txt = "This is my house and I came from shandong";
		AnalyzerUtil.displayAllTokenInfo(txt, a1);
		AnalyzerUtil.displayAllTokenInfo(txt, a2);
		AnalyzerUtil.displayAllTokenInfo(txt, a3);
		AnalyzerUtil.displayAllTokenInfo(txt, a4);
	}
	
	@Test
	public void test03(){
		String[] stops = {"I", "am", "teacher"};
		String str = "I am a teacher and I like swimming";
		Analyzer a1 = new MyStopAnalyzer(stops);
		AnalyzerUtil.displayToken(str, a1);
	}
}
