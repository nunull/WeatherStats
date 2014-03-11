package de.szut.weather.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;


import de.szut.weather.models.Entry;
import de.szut.weather.stats.WeatherStats;

/**
 * Container class for the "main"-window which shows the charts.
 */
public class MainWindow {
	private WeatherStats stats;
	private LinkedList<Entry> entrys;
	private final Iterator<Entry> entryIterator;
	
	private JFrame mainFrame;
	private JTabbedPane tabbedPane;
	private LinkedList<JPanel> tabs;
	
	/**
	 * Constructor.
	 * 
	 * @param stats The statistics to be visualized.
	 */
	public MainWindow(WeatherStats stats, LinkedList<Entry> entrys) {
		this.stats = stats;
		this.entrys = entrys;
		this.entryIterator = entrys.iterator();
		
		mainFrame = new JFrame("WeatherStats");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(50, 50, 800, 600);
		mainFrame.setLocationRelativeTo(null);
		
		buildTabs();
		buildTabbedPane();
	}
	
	/**
	 * Builds the tab-pane.
	 */
	private void buildTabbedPane() {
		tabbedPane = new JTabbedPane();
		
		for(JPanel tab : tabs) {
			tabbedPane.addTab(tab.getName(), tab);
		}
		
		mainFrame.add(tabbedPane);
	}
	
	/**
	 * Builds the single tabs.
	 */
	@SuppressWarnings("deprecation")
	private void buildTabs() {
		tabs = new LinkedList<JPanel>();
		
		//highest windspeed
		JPanel highestWindSpeedTab = new JPanel();
		highestWindSpeedTab.setName("Highest Windspeed");
		DefaultCategoryDataset BarDataset = new DefaultCategoryDataset();
		JFreeChart highestWindSpeedChart = ChartFactory.createBarChart3D("Highest Windspeed", "date", "windspeed", BarDataset);
		LinkedList<Entry> fx = stats.getFx();
		for(Entry entry : fx){
			BarDataset.addValue(entry.getValueAsDouble("FX"), entry.getValueAsDouble("FX"), (entry.getValueAsGregorianCalendar("Datum").getTime().toLocaleString() ) );
		}
		ChartPanel Barcp = new ChartPanel(highestWindSpeedChart); 
		highestWindSpeedTab.add(Barcp);
		tabs.add(highestWindSpeedTab);
		
		//highest temperatures
		JPanel highestTemperaturesTab = new JPanel();
		highestTemperaturesTab.setName("Highest Temperatures");
		DefaultPieDataset PieDataset = new DefaultPieDataset();
		JFreeChart highestTemperaturesChart = ChartFactory.createPieChart3D("Highest Temperatures", PieDataset);
		
		LinkedList<java.util.Map.Entry<String, Double>> tx = stats.getTx();
		for(java.util.Map.Entry<String, Double> entry : tx){
			PieDataset.setValue(entry.getValue() + " degree\n" + entry.getKey(), entry.getValue());
		}
		ChartPanel Piecp = new ChartPanel(highestTemperaturesChart);
		highestTemperaturesTab.add(Piecp);
		tabs.add(highestTemperaturesTab);
		
		//all temperatures, dynamic
		JPanel allTemperaturesTab = new JPanel();
		allTemperaturesTab.setName("Temperature Overview");
		
		final DefaultXYDataset dataset = new DefaultXYDataset();
//		for(Entry entry : entrys) {
//			dataset.addValue(entry.getValueAsDouble("TX"), "T", entry.getValueAsString("Datum"));	
//		}
		dataset.addValue(entrys.get(0).getValueAsDouble("TX"), entrys.get(0).getValueAsDouble("TX"), (entrys.get(0).getValueAsGregorianCalendar("Datum").getTime().toLocaleString() ) );
		dataset.ad
		
		JFreeChart allTemperaturesChart = ChartFactory.createTimeSeriesChart("Temperature Overview", "Dates", "Temperatures (C)", dataset);
		
		ChartPanel allTemperaturesChartPanel = new ChartPanel(allTemperaturesChart);
		allTemperaturesTab.add(allTemperaturesChartPanel);
		tabs.add(allTemperaturesTab);
		
		
//		JFreeChart allTemperaturesChart = ChartFactory.createLineChart("Temperature Overview", "temperature", "date", lineDataset);
		Timer t = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Entry entry = entrys.get(0);
				if(entryIterator.hasNext()) entry = entryIterator.next();
				
				dataset.addValue(entry.getValueAsDouble("TX"), entry.getValueAsDouble("TX"), (entry.getValueAsGregorianCalendar("Datum").getTime().toLocaleString() ) );
			}
		});
		t.start();
	}
	
	/**
	 * Shows the GUI.
	 */
	public void show() {
		mainFrame.setVisible(true);
	}
}
