package de.szut.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream.GetField;
import java.util.LinkedList;
import java.util.TreeMap;

import de.szut.weather.models.Entry;
import de.szut.weather.parser.CSVReader;
import de.szut.weather.stats.*;

public class Main {

	/**
	 * @param args ignored
	 */
	public static void main(String[] args) {
		
		/* testing parser */
		CSVReader reader = new CSVReader();
		
		try {
			LinkedList<Entry> entrys = reader.read(new BufferedReader(new FileReader(Config.CSV_PATH)));
			WeatherStats stats = new WeatherStats(entrys);
			System.out.println( stats.getTm() );
			System.out.println( stats.getFx().get(3).getValueAsString("FX"));
			System.out.println( stats.getTx().get(4).getValueAsDouble("TX"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
