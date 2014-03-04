package de.szut.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

import javax.swing.JFileChooser;
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
					CSVParser reader = new CSVParser();
					LinkedList<Entry> entrys = reader.parse(new BufferedReader(new FileReader(Config.CSVPATH)));
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
