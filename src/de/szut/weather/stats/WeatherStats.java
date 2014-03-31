package de.szut.weather.stats;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import de.szut.weather.models.Entry;

/**
 * Wrapper class for statistic functionality. 
 */
public class WeatherStats implements Stats {
	private LinkedList<Entry> entrys;

	// The average temperature.
	private double tm;
	// The day with the highest snow-height.
	private Entry shk;
	// The five days with the highest wind speed.
	private LinkedList<Entry> fx;
	// The five hottest months.
	private LinkedList<Map.Entry<String, Double>> tx;

	private Iterator<Entry> itr;
	private LinkedList<Entry> dynamicList;
	private static int DYNAMICLISTSIZEBUFFER = 500;

	/**
	 * Constructor.
	 * 
	 * @param entrys The entrys.
	 */
	public WeatherStats(LinkedList<Entry> entrys) {
		this.entrys = entrys;
		calcStats();
	}

	/**
	 * Calculate the statistics.
	 */
	private void calcStats() {
		fx = new LinkedList<Entry>();
		tx = new LinkedList<Map.Entry<String, Double>>();
		shk = entrys.getFirst();
		dynamicList = new LinkedList<Entry>();

		fx.add(entrys.getFirst());

		// Calculate monthly TX
		TreeMap<String, Double> monthlyTX = new TreeMap<String, Double>();
		TreeMap<String, Integer> monthlyTXCount = new TreeMap<String, Integer>();
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
					double value = Math.round( entry.getValue()*100 );
					entry.setValue(value/100);
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
				}
			}
		}

		tm = tm / entrys.size();

		//dynamic list
		dynamicList.add(entrys.getFirst());
		itr = entrys.listIterator(1);
		for (int i = 0; i<DYNAMICLISTSIZEBUFFER; i++){
			if (itr.hasNext()) dynamicList.add(itr.next());
			else dynamicList.add(entrys.getFirst());
		}
	}

	public void dynamicChartRefresh (){
		dynamicList.remove();
		if (itr.hasNext()) dynamicList.add(itr.next());
		else dynamicList.add(entrys.getFirst());

	}

	public LinkedList<Entry> getDynamicList(){
		return dynamicList;
	}

	/**
	 * Returns the given entrys.
	 */
	@Override
	public LinkedList<Entry> getEntrys() {
		return entrys;
	}

	/**
	 * Returns the average temperature.
	 * 
	 * @return The temperature.
	 */
	public double getTm() {
		return tm;
	}

	/**
	 * Returns the five days with the highest wind speeds.
	 * 
	 * @return A list containing the entrys.
	 */
	public LinkedList<Entry> getFx() {
		return fx;
	}

	/**
	 * Returns the five hottest months.
	 * 
	 * @return A list containing map-entrys representing the five months.
	 */
	public LinkedList<java.util.Map.Entry<String, Double>> getTx() {
		return tx;
	}

	/**
	 * Returns the highest snow-height.
	 * 
	 * @return The entry with the highest snow-height.
	 */
	public Entry getShk() {
		return shk;
	}
}
