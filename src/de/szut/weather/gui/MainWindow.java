package de.szut.weather.gui;

import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import de.szut.weather.models.Entry;
import de.szut.weather.stats.WeatherStats;

/**
 * Container class for the "main"-window which shows the charts.
 */
public class MainWindow {
	private WeatherStats stats;
	private JFrame mainFrame;
	private JTabbedPane tabbedPane;
	private LinkedList<JPanel> tabs;
	
	/**
	 * Constructor.
	 * 
	 * @param stats The statistics to be visualized.
	 */
	public MainWindow(WeatherStats stats) {
		this.stats = stats;
		
		mainFrame = new JFrame("WeatherStats");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(50, 50, 800, 600);
		
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
		JFreeChart highestTemperaturesChart = ChartFactory.createPieChart3D("Highest Temperature", PieDataset);
		
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
//		 lineDataset
//		JFreeChart allTemperaturesChart = ChartFactory.createLineChart("Temperature Overview", "temperature", "date", lineDataset);
		
	}
	
	/**
	 * Shows the GUI.
	 */
	public void show() {
		mainFrame.setVisible(true);
	}
}
