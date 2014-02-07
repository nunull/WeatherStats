package de.szut.weather.stats;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;

import de.szut.weather.models.*;
import de.szut.weather.parser.CSVReaderOptions;

public class WeatherStats {
	
	private LinkedList<Entry> entrys;
	
	private double averageTemperature; //TODO should be called TM, like in the tasksheet??
	private GregorianCalendar[] highestWindSpeed;
	private GregorianCalendar[] hotestMonths;
	private GregorianCalendar[] highestSnowHeight;
	
	public WeatherStats(LinkedList<Entry> entrys) {
		this.entrys = entrys;
		
		calcStats();
	}
	
	private void calcStats() {
		for(Entry entry : entrys) {
			
			// average temperatur
			averageTemperature += (Double) entry.get("TM");
			
			//highest wind speed
			
			//hotest month
			
			//highest snow height
			
		}
		averageTemperature = averageTemperature / entrys.size();
	}

	public LinkedList<Entry> getEntrys() {
		return entrys;
	}

	public double getAverageTemperature() {
		return averageTemperature;
	}

	public GregorianCalendar[] getHighestWindSpeed() {
		return highestWindSpeed;
	}

	public GregorianCalendar[] getHotestMonths() {
		return hotestMonths;
	}

	public GregorianCalendar[] getHighestSnowHeight() {
		return highestSnowHeight;
	}
}
