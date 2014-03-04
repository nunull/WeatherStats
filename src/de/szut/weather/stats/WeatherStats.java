package de.szut.weather.stats;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import de.szut.weather.models.Entry;

public class WeatherStats implements Stats {

	private LinkedList<Entry> entrys;

	private double tm; //average temperature
	private Entry shk; //day with highest snow
	private LinkedList<Entry> fx; //five days, highest wind speed
	private LinkedList<Map.Entry<String, Double>> tx; //five hottest months

	public WeatherStats(LinkedList<Entry> entrys) {
		this.entrys = entrys;
		calcStats();
	}

	private void calcStats() {
		fx = new LinkedList<Entry>();
		tx = new LinkedList<Map.Entry<String, Double>>();
		shk = entrys.getFirst();

		fx.add(entrys.getFirst());
		
		// Calculate monthly TX
		TreeMap<String, Double> monthlyTX = new TreeMap<>();
		TreeMap<String, Integer> monthlyTXCount = new TreeMap<>();
		for(Entry entry : entrys) {
			GregorianCalendar date = entry.getValueAsGregorianCalendar("Datum");
			DateFormat dateFormat = new SimpleDateFormat("MMM yyyy");
			String key = dateFormat.format(date.getTime());
			
			if(monthlyTX.get(key) == null) {
				monthlyTX.put(key, 0.d);
				monthlyTXCount.put(key, 0);
			}
			
			monthlyTX.put(key, monthlyTX.get(key) + entry.getValueAsDouble("TX"));
			monthlyTXCount.put(key, monthlyTXCount.get(key)+1);
		}
		for(String key : monthlyTX.keySet()) {
			monthlyTX.put(key, monthlyTX.get(key)/monthlyTXCount.get(key));
		}
		
		// Calculate hottest five months
		for(int i = 0; i < 5; i++) {
			tx.add(i, monthlyTX.firstEntry());
			
			for(Map.Entry<String, Double> entry : monthlyTX.entrySet()) {
				if(entry.getValue() > tx.get(i).getValue() && !tx.contains(entry)) {
					tx.remove(i);
					tx.add(i, entry);
				}
			}
		}

		for(Entry entry : entrys) { //split up first loop for better performance (tm & shk)
			// average temperature ----- highest snow height
			tm += entry.getValueAsDouble("TM");
			if (entry.getValueAsDouble("SHK") > shk.getValueAsDouble("SHK") ) shk = entry;

			//highest wind speed
			try {
				if (entry.getValueAsDouble("FX") > fx.get(0).getValueAsDouble("FX") && !fx.contains(entry)) {
					fx.remove(0);
					fx.add(0, entry);
				}
			} catch(NullPointerException e) {
				// TODO
			}
		}

		for (int i = 1; i < 5; i++){
			fx.add(entrys.getFirst());

			for(Entry entry : entrys) {
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
		}
		
		tm = tm / entrys.size();
	}

	@Override
	public LinkedList<Entry> getEntrys() {
		return entrys;
	}

	public double getTm() {
		return tm;
	}

	public LinkedList<Entry> getFx() {
		return fx;
	}

	public LinkedList<java.util.Map.Entry<String, Double>> getTx() {
		return tx;
	}

	public Entry getShk() {
		return shk;
	}
}
