package com.sun.test;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.sun.analyzer.MyStopAnalyzer;
import com.sun.analyzer.MySynonymAnalyzer;
import com.sun.context.SimpleSynonymContext;
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
	
	@Test
	public void testSynonymWord(){
		try {
			String str = "我们一起去跳舞莱芜市，人民大会堂";
			Analyzer a1 = new MySynonymAnalyzer(new SimpleSynonymContext());
			
			Directory dir = new RAMDirectory();
			IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(Version.LUCENE_35, a1));
			Document document = new Document();
			document.add(new Field("content", str, Field.Store.YES, Field.Index.ANALYZED));
			writer.addDocument(document);			
			writer.close();
			
			IndexSearcher searcher = new IndexSearcher(IndexReader.open(dir));
			TopDocs tds = searcher.search(new TermQuery(new Term("content", "家乡")), 10);
			ScoreDoc[] sds = tds.scoreDocs;
			Document doc = searcher.doc(sds[0].doc);
			System.out.println(doc.get("content"));
			
			AnalyzerUtil.displayAllTokenInfo(str, a1);
//			AnalyzerUtil.displayToken(str, a1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
