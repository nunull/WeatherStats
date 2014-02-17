package de.szut.weather.stats;

import java.util.LinkedList;

import de.szut.weather.models.*;

public class WeatherStats implements Stats {

	private LinkedList<Entry> entrys;

	private double tm; //average temperature
	private Entry shk; //day with highest snow
	private LinkedList<Entry> fx; //five days, highest wind speed
	private LinkedList<Entry> tx; //five hottest days

	public WeatherStats(LinkedList<Entry> entrys) {
		this.entrys = entrys;
		calcStats();
	}

	private void calcStats() {
		fx = new LinkedList<Entry>();
		tx = new LinkedList<Entry>();
		shk = entrys.getFirst();
		
		
		fx.add(entrys.getFirst());
		tx.add(entrys.getFirst());

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
			//hottest five days
			try {
				if (entry.getValueAsDouble("TX") > tx.get(0).getValueAsDouble("TX") && !tx.contains(entry)) {
					tx.remove(0);
					tx.add(0, entry);
				}
			} catch(NullPointerException e) {
				//TODO
			}
		}

		for (int i = 1; i < 5; i++){
			fx.add(entrys.getFirst());
			tx.add(entrys.getFirst());

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
				//hotest five days
				try {
					if (entry.getValueAsDouble("TX") > tx.get(i).getValueAsDouble("TX") && !tx.contains(entry)) {
						tx.remove(i);
						tx.add(i, entry);
					}
				} catch(NullPointerException e) {
					//TODO
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

	public LinkedList<Entry> getTx() {
		return tx;
	}

	public Entry getShk() {
		return shk;
	}
}
