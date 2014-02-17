package de.szut.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

import javax.swing.SwingUtilities;

import de.szut.weather.gui.MainWindow;
import de.szut.weather.models.*;
import de.szut.weather.parser.*;
import de.szut.weather.stats.*;

public class Main {

	/**
	 * @param args ignored
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					CSVReader reader = new CSVReader();
					LinkedList<Entry> entrys = reader.read(new BufferedReader(new FileReader(Config.CSV_PATH)));
					WeatherStats stats = new WeatherStats(entrys);

					MainWindow mainW = new MainWindow(stats);
					mainW.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
