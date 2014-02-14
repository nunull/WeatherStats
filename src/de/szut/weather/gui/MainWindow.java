package de.szut.weather.gui;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IPointPainter;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePainter;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
import info.monitorenter.util.Range;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import de.szut.weather.models.Entry;
import de.szut.weather.stats.WeatherStats;

public class MainWindow {
	private WeatherStats stats;
	private JFrame mainFrame;
	private JTabbedPane tabbedPane;
	private LinkedList<JPanel> tabs;
	
	public MainWindow(WeatherStats stats) {
		this.stats = stats;
		
		mainFrame = new JFrame("WeatherStats");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(50, 50, 800, 600);
		
		buildTabs();
		buildTabbedPane();
	}
	
	private void buildTabbedPane() {
		tabbedPane = new JTabbedPane();
		
		for(JPanel tab : tabs) {
			tabbedPane.addTab(tab.getName(), tab);
		}
		
		mainFrame.add(tabbedPane);
	}
	
	private void buildTabs() {
		tabs = new LinkedList<JPanel>();
		
		JPanel highestWindSpeedTab = new JPanel();
		highestWindSpeedTab.setName("Highest Windspeed");
		
		Chart2D highestWindSpeedChart = new Chart2D();
		highestWindSpeedChart.setPreferredSize(new Dimension( 300, 300) );
		ITrace2D highestWindSpeedTrace = new Trace2DSimple();
		highestWindSpeedChart.addTrace(highestWindSpeedTrace);
		LinkedList<Entry> fx = stats.getFx();
		
		
		for(Entry entry: fx){ //TODO calc bft and set as y, remove lines betweeen points, set date as date
			TracePoint2D point = new TracePoint2D( entry.getValueAsDouble("Datum"), entry.getValueAsDouble("FX") );
			highestWindSpeedTrace.addPoint(point);
		}
		highestWindSpeedTab.add(highestWindSpeedChart);
		tabs.add(highestWindSpeedTab);
		
	}
	
	public void show() {
		mainFrame.setVisible(true);
	}
}
