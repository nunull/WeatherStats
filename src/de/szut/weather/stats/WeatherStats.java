package de.szut.weather.stats;

import java.util.LinkedList;

import de.szut.weather.models.*;

public class WeatherStats {
	
	private LinkedList<Entry> entrys;
	
	public WeatherStats(LinkedList<Entry> entrys) {
		this.entrys = entrys;
		
		calcStats();
	}
	
	private void calcStats() {
		// TODO calc stats; maybe outsource some parts to private methods
	}
	
	// TODO only getters here
}
