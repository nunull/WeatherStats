package de.szut.weather.stats;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;

import de.szut.weather.models.*;
import de.szut.weather.parser.CSVReaderOptions;

public class WeatherStats {

	private LinkedList<Entry> entrys;

	private double tm; //average temperature
	private LinkedList<Entry> fx; //five days, highest wind speed
	private LinkedList<Entry> tx; //five hottest days
	private Entry shk; //day with highest snow

	public WeatherStats(LinkedList<Entry> entrys) {
		this.entrys = entrys;

		calcStats();
	}

	private void calcStats() {
		fx = new LinkedList<Entry>();
		
		for (int i = 0; i < 5; i++){
			fx.add(entrys.get(0));
			
			for(Entry entry : entrys) {
				
				// average temperatur
				if(i == 0) {
					tm += entry.getValueAsDouble("TM");
				}

				//highest wind speed
				try {
					if (entry.getValueAsDouble("FX") > fx.get(i).getValueAsDouble("FX") && !fx.contains(entry)) {
						fx.remove(i);
						fx.add(i, entry);
					}
				} catch(NullPointerException e) {
					// TODO
				}
			}

			//hotest month

			//highest snow height

		}
		
		tm = tm / entrys.size();
	}

	public LinkedList<Entry> getEntrys() {
		return entrys;
	}

	public double getTm() {
		return tm;
	}

	public LinkedList<Entry> getFx() {
		return fx;
	}

	public LinkedList<Entry> getTx() {
		return tx;
	}

	public Entry getShk() {
		return shk;
	}
}
