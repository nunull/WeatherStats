package de.szut.weather.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

import de.szut.weather.general.Entry;

public class ReaderCSV {
	public LinkedList read (BufferedReader br){
		String line;
		String[] strings;
		Entry entry;
		
		//TODO evtl erste zeile auslesen und als keys benutzen und dann den parser dynamis gestalten
		// oder halt einfach statis über konstanten (mache ich wohl)
		try {
			if (br.ready()) br.readLine(); //ignores first line
			
			while (br.ready()) {
				entry = new Entry();
				line = br.readLine();
				strings = line.split(";");
				entry.setDatum( Double.parseDouble( strings[ParserConstants.Datum] ) );
				System.out.println(entry.getDatum());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
