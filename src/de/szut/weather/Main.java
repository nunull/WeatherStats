package de.szut.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.TreeMap;

import de.szut.weather.models.Entry;
import de.szut.weather.parser.CSVReader;
import de.szut.weather.parser.CSVReaderOptions;

public class Main {

	/**
	 * @param args ignored
	 */
	public static void main(String[] args) {
		
		/* testing parser */
		CSVReader reader = new CSVReader();
		CSVReaderOptions options = new CSVReaderOptions();
		options.put("Datum", String.class);
		
		try {
			LinkedList<Entry> entrys = reader.read(new BufferedReader(new FileReader(Config.CSV_PATH)), options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
