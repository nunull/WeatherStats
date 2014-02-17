package de.szut.weather.gui;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.IPointPainter;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePainter;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
import info.monitorenter.gui.chart.traces.painters.TracePainterVerticalBar;
import info.monitorenter.util.Range;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import de.szut.weather.models.*;
import de.szut.weather.stats.*;

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
		
		// TODO barchart
		Chart2D highestWindSpeedChart = new Chart2D();
		
		highestWindSpeedChart.setPreferredSize(new Dimension(800, 520));
		highestWindSpeedChart.setBackground(Color.getColor("#EEEEEE"));
		ITrace2D highestWindSpeedTrace = new Trace2DSimple();
		highestWindSpeedChart.addTrace(highestWindSpeedTrace);
		highestWindSpeedTrace.setTracePainter(new TracePainterVerticalBar(20, highestWindSpeedChart));
		
		IAxis xAxis = highestWindSpeedChart.getAxisX();
		xAxis.setTitle("");
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
