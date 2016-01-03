package com.sun.context;

import java.util.HashMap;
import java.util.Map;

public class SimpleSynonymContext implements SynonymContext{

	private Map<String, String[]> maps = null;
	
	public SimpleSynonymContext(){
		maps = new HashMap<String, String[]>();
		maps.put("莱芜", new String[]{"家乡", "老家"});
	}
	
	@Override
	public String[] getSynonym(String name) {
		
		return maps.get(name);
	}

}
