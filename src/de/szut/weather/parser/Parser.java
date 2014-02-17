package de.szut.weather.parser;

import java.io.BufferedReader;
import java.util.LinkedList;

import de.szut.weather.models.Entry;

public interface Parser {
	public LinkedList<Entry> parse (BufferedReader br);
}
