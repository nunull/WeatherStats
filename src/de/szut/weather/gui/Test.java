package de.szut.weather.gui;

import javax.swing.SwingUtilities;

public class Test {

	/**
	 * @param args ignored
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainWindow mainW = new MainWindow();
				mainW.show();
			}
		});

	}

}
