
// Beispiel: drei Grafiken mit JChart2D
// M.Martens 17.02.2014

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Charts2D extends JFrame 
{
	public Charts2D() {
		super("2D Charts"); //Fenster�berschrift
		setSize(720, 280); //Fenstergr��e
		getContentPane().setLayout(new GridLayout(1, 3, 10, 0)); //Layoutmanager Grid mit Parametern
		//int rows, int cols, int hgap, int vgap = eine Reihe, drei Spalten,horiz.und,vert. Abstand 
		getContentPane().setBackground(Color.white); //Hintergrundfarbe des Fensters: wei�

		//Beispieldaten f�r die Grafikdemo in zwei Array-Variablen gef�llt
		int nData = 8; //Hilfsvariable mit dem Integer-Wert 8
		int[] xData = new int[nData]; //eindimensionales Array-Objekt mit 8 Feldern
		int[] yData = new int[nData]; //einmal f�r die X-Achse und einmal f�r die Y-Achse
		for (int k=0; k<nData; k++) { //Z�hlschleife von 0 bis 7
			xData[k] = k; //X-Achse-Felder werden mit dem Wert der Z�hlvariable belegt	
			yData[k] = (int)(Math.random()*100); //Y-Achse-Felder werden mit Zufallszahlen belegt 
			if (k > 0) //ab dem 2. Array-Feld erfolgt die Berechnung:
				yData[k] = (yData[k-1] + yData[k])/2;
		}
		//1. Grafik: Liniendiagramm
		//Objektvariable vom Typ JChart2D und damit nutzen aller
		JChart2D chart = new JChart2D( //seiner Methoden mit dem Punkt-Operator
				JChart2D.CHART_LINE, nData, xData, //Liniendiagramm-Objekt mit Parameter�bergabe und
				yData, "Line Chart"); //Titelbezeichnung
		chart.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, //Grafikdesigneinstellungen
				BasicStroke.JOIN_MITER)); //width, cap, join
		chart.setLineColor(new Color(0, 128, 128)); //Teal/T�rkies
		getContentPane().add(chart); //Hinzuf�gen der Grafik zum Content Pane des JFrames
		//2. Grafik: Balkendiagramm
		chart = new JChart2D(JChart2D.CHART_COLUMN, //Balkendiagramm-Objekt mit Parameter�bergabe
				nData, xData, yData, "Column Chart"); //und Titelbezeichnung
		GradientPaint gp = new GradientPaint(0, 100, //Designeinstellungen
				Color.white, 0, 300, Color.blue, true); //Balkenfarbe wei� und blau
		chart.setGradient(gp); //�bergabe der Designeinstellungen
		chart.setEffectIndex(JChart2D.EFFECT_GRADIENT); //Effekt: Balkenfarbe wei� und blau �berblendend
		chart.setDrawShadow(true); //Effekt: Balkenschatten
		getContentPane().add(chart); //Hinzuf�gen der Grafik zum Content Pane des JFrames
		//3. Grafik: Kreis(Torten)diagramm    
		chart = new JChart2D(JChart2D.CHART_PIE, nData, xData, //Kreisdiagrammobjekt mit Parameter
				yData, "Pie Chart"); // und Titel
		chart.setDrawShadow(true); //Kreis mit Schatten der einzelnen Kreissegmente
		getContentPane().add(chart); //Hinzuf�gen der Grafik zum Content Pane des JFrames

		//
		WindowListener wndCloser = new WindowAdapter() { //Fensterhorcher/Listener implementieren
			public void windowClosing(WindowEvent e) { //Ereignis: Der Benutzer schlie�t das Fenster
				System.exit(0); //und das Programm wird beendet
			}
		};
		addWindowListener(wndCloser); //Listener bei dem Ereignisausl�ser anmelden
		setVisible(true); //Grafik anzeigen
	}
	public static void main(String argv[]) {
		new Charts2D(); //Charts2D-Objekt erstellen
	}
}