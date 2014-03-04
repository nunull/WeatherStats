package de.szut.weather.models;

import java.util.GregorianCalendar;
import java.util.TreeMap;

/**
 * Container for a statistic-entry.
 */
public class Entry {
	private TreeMap<String, String> values;
	
	/**
	 * Constructor.
	 */
	public Entry() {
		values = new TreeMap<String, String>();
	}
	
	/**
	 * Puts a key-value-pair.
	 * 
	 * @param key The key to save the value at.
	 * @param value The value to save.
	 * @return true, if the key is new in the map, false otherwise.
	 */
	public boolean put(String key, String value) {
		if(values.put(key, value) == null) return true;
		else return false;
	}
	
	/**
	 * Returns the value as a string.
	 * 
	 * @param key The key.
	 * @return the value mapped to the key or null.
	 */
	public String getValueAsString(String key) {
		return values.get(key);
	}
	
	/**
	 * Returns the value as a double or null if there is no value at the specific key or it can not be parsed.
	 * 
	 * @param key The key.
	 * @return the value mapped to the key or null.
	 */
	public Double getValueAsDouble(String key) {
		try {
			return Double.valueOf(values.get(key));
		} catch(NullPointerException e) {
			return null;
		} catch(NumberFormatException e) {
			return null;
		}
	}
	
	/**
	 * Returns the value as an GregorianCalendar.
	 * 
	 * @param key The key.
	 * @return the value mapped to the key.
	 */
	public GregorianCalendar getValueAsGregorianCalendar(String key) {
		String value = getValueAsString(key);
		int year = Integer.valueOf(value.substring(0, 4));
		int month = Integer.valueOf(value.substring(4, 6)) - 1; // Decrement value, because month of GregorianCalendar is zero-based.
		int dayOfMonth = Integer.valueOf(value.substring(6, 8));
		
		return new GregorianCalendar(year, month, dayOfMonth);
	}
}
