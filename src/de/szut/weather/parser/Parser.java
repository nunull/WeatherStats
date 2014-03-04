package de.szut.weather.parser;

import java.io.BufferedReader;
import java.util.LinkedList;

import de.szut.weather.models.Entry;

/**
 * An interface for all parsers. Could be used to implement different parsers.
 */
public interface Parser {
	/**
	 * Parse the given values and return them as a LinkedList.
	 * 
	 * @param br A buffered reader for getting single entrys.
	 * @return The resulting list.
	 */
	public LinkedList<Entry> parse (BufferedReader br);
}
