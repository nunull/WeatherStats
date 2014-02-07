package de.szut.weather.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

import de.szut.weather.models.Entry;

public class CSVReader {
	CSVReaderOptions options;
	public LinkedList<Entry> read (BufferedReader br, CSVReaderOptions options){
		try {
			LinkedList<Entry> entrys = new LinkedList<Entry>();
			this.options = options;
			
			String[] keys = new String[0];
			if (br.ready()) keys = br.readLine().split(";");
			
			while (br.ready()) {
				Entry entry = new Entry();
				String[] values = br.readLine().split(";");
				
				for(int i = 0; i < keys.length; i++) {
					if(keys[i] != null) {						
						Class type = options.get(keys[i]); //TODO saved as object, cast unnötig
						
						if(values[i] != null) {
							entry.put(keys[i], values[i]);
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
	
	public CSVReaderOptions getOptions (){
		return options;
	}
}
