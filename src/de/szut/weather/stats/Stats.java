package de.szut.weather.stats;

import java.util.LinkedList;

import de.szut.weather.models.Entry;

/**
 * Wrapper class for statistic functionality.
 */
public interface Stats {
	public LinkedList<Entry> getEntrys();
}
