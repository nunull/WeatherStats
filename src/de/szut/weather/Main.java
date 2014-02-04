package de.szut.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import de.szut.weather.parser.ReaderCSV;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/* testing parser */
		ReaderCSV reader = new ReaderCSV();
		try {
			reader.read(new BufferedReader(new FileReader("trunk/ressources/Klima_Tageswerte_1890_2013.csv")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
