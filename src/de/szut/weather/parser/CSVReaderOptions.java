package de.szut.weather.parser;

import java.util.TreeMap;

public class CSVReaderOptions {
	private TreeMap<String, Class> options;
	
	public CSVReaderOptions() {
		 options = new TreeMap();
	}
	
	public void put(String key, Class value) {
		options.put(key, value);
	}
	
	public Class get(String key) {
		return options.get(key);
	}
}
