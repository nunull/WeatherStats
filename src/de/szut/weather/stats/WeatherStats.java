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
	public static int getBft(double fx){
		int bft = -1;
		if (fx>=0 && fx<=0.2) bft = 0;
		else if (fx>0.2 && fx<=1.5) bft = 1;
		else if (fx>1.5 && fx<=3.3) bft = 2;
		else if (fx>3.3 && fx<=5.4) bft = 3;
		else if (fx>5.4 && fx<=7.9) bft = 4;
		else if (fx>7.9 && fx<=10.7) bft = 5;
		else if (fx>10.7 && fx<=13.8) bft = 6;
		else if (fx>13.8 && fx<=17.1) bft = 7;
		else if (fx>17.1 && fx<=20.7) bft = 8;
		else if (fx>20.7 && fx<=24.4) bft = 9;
		else if (fx>24.4 && fx<=28.4) bft = 10;
		else if (fx>28.4 && fx<=32.6) bft = 11;
		else if (fx>32.6) bft = 12;
		return bft;
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
