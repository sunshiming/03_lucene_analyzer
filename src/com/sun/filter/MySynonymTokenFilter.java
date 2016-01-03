package com.sun.filter;

import java.io.IOException;
import java.util.Stack;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;

import com.sun.context.SynonymContext;

public class MySynonymTokenFilter extends TokenFilter{

	private CharTermAttribute cta = null;
	private PositionIncrementAttribute pia = null;
	private Stack<String> sames = null;
	private AttributeSource.State current = null;
	private SynonymContext synonymContext = null;
	
	public MySynonymTokenFilter(TokenStream input, SynonymContext synonymContext) {
		super(input);
		cta = this.addAttribute(CharTermAttribute.class);
		pia = this.addAttribute(PositionIncrementAttribute.class);
		sames = new Stack<String>();
		this.synonymContext = synonymContext;
	}

	@Override
	public boolean incrementToken() throws IOException {
		
		while(sames.size() > 0){
			String name = sames.pop();
			restoreState(current);
			cta.setEmpty();
			cta.append(name);
			pia.setPositionIncrement(0);
			return true;
		}
		
		if(!input.incrementToken()){
			return false;
		}
		
		if(getSynonym(cta.toString())){
			current = captureState();
		}
		
		return true;
	}
	
	public boolean getSynonym(String name){
		String[] sws = synonymContext.getSynonym(name);
		if(null != sws){
			for(String str : sws){
				sames.push(str);
			}
			return true;
		}
		
		return false;
	}

}
