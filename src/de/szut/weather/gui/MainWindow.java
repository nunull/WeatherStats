package de.szut.weather.gui;

import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;

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
		
		JPanel highestWindTab = new JPanel();
		highestWindTab.setName("Highest Windspeed");
		
		tabs.add(highestWindTab);
	}
	
	public void show() {
		mainFrame.setVisible(true);
	}
}
