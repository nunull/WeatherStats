package de.szut.weather.models;

import java.util.TreeMap;

public class Entry {
	private TreeMap<String, Object> values;
	
	public Entry() {
		values = new TreeMap<String, Object>();
	}
	
	/**
	 * Puts a key-value-pair.
	 * 
	 * @param key The key to save the value at.
	 * @param value The value to save.
	 * @return true, if the key is new in the map, false otherwise.
	 */
	public boolean put(String key, Object value) {
		if(values.put(key, value) == null) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param key The key.
	 * @return the value mapped to the key or null.
	 */
	public Object get(String key) {
		return values.get(key);
	}
}
