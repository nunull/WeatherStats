package de.szut.weather.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import de.szut.weather.models.Entry;

public class CSVReader {
	public LinkedList<Entry> read (BufferedReader br, CSVReaderOptions options){
		try {
			LinkedList<Entry> entrys = new LinkedList<Entry>();
			
			String[] keys = new String[0];
			if (br.ready()) keys = br.readLine().split(";"); //ignores first line
			
			while (br.ready()) {
				Entry entry = new Entry();
//				line.replaceAll(" ", "");
				String[] values = br.readLine().split(";");
				
				for(int i = 0; i < keys.length; i++) {
					if(keys[i] != null) {						
						Class type = options.get(keys[i]);
						
						if(values[i] != null) {
//							// TODO dynamic parsing
							Object o = null;
							if(type == null) {
								o = Double.parseDouble(values[i]);
							} else {
								o = type.cast(values[i]);
							}
							entry.put(keys[i], o);
						}
						
					}
				}
				
				entrys.add(entry);
			}
			
			return entrys;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
