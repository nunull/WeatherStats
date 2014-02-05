package de.szut.weather.stats;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import de.szut.weather.models.*;

public class WeatherStats {
	
	private LinkedList<Entry> entrys;
	
	private double averageTemperature;
	private GregorianCalendar[] highestWindSpeed;
	private GregorianCalendar[] hotestMonths;
	private GregorianCalendar[] highestSnowHeight;
	
	public WeatherStats(LinkedList<Entry> entrys) {
		this.entrys = entrys;
		
		calcStats();
	}
	
	private void calcStats() {
		for(int i = 0, j = entrys.size(); i < j; i++) {
			
		}
	}
	
	// TODO only getters here
}
