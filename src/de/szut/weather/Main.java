package de.szut.weather;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.szut.weather.gui.MainWindow;
import de.szut.weather.models.Entry;
import de.szut.weather.parser.CSVParser;
import de.szut.weather.stats.WeatherStats;

/**
 * Main-class.
 */
public class Main {

	/**
	 * Applications entry point.
	 * Shows a 
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFileChooser fileChooser = new JFileChooser(".");

				fileChooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
				fileChooser.setAcceptAllFileFilterUsed(false);

				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					final File file = fileChooser.getSelectedFile();

					JLabel waitLabel = new JLabel(Messages.getString("Lang.PleaseWait"));
					waitLabel.setHorizontalAlignment(SwingConstants.CENTER);

					final JDialog waitDialog = new JDialog();
					waitDialog.setSize(200, 100);
					waitDialog.setLocationRelativeTo(null);
					waitDialog.setResizable(false);
					waitDialog.setUndecorated(true);
					waitDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					waitDialog.getContentPane().add(waitLabel);
					waitDialog.setVisible(true);
	

					SwingWorker<WeatherStats, Object> swingWorker = new SwingWorker<WeatherStats, Object>() {

						@Override
						protected WeatherStats doInBackground()
								throws Exception {
							CSVParser reader = new CSVParser();
							LinkedList<Entry> entrys = reader.parse(new BufferedReader(new FileReader(file)));
							return new WeatherStats(entrys);
						}

						@Override
						protected void done() {
							MainWindow mainW;
							try {
								waitDialog.setVisible(false);
								mainW = new MainWindow(get());
								mainW.show();
							} catch (InterruptedException e) {
							} catch (ExecutionException e) {
							}

							super.done();
						}
					};

					swingWorker.execute();
				} else {
					System.exit(0);
				}
			}
		});
	}
}
