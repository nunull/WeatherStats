package de.szut.weather.gui;

import java.text.MessageFormat;

import javax.swing.JOptionPane;

public final class DialogViewer {
	private static final String STRTM = "Aufgabe 1.1;Durchschnittstemperatur des Mittels der Temperatur in 2 m über dem Erdboden (TM): {0}";
	private static final String STRFX = "Aufgabe 1.2;Stärkste Winde von 1 - 5 \n " +
			"Wind 1 am {0}: {1} \n" +
			"Wind 2 am {2}: {3} \n" +
			"Wind 3 am {4}: {5} \n" +
			"Wind 4 am {6}: {7} \n" +
			"Wind 5 am {8}: {9}";
	private static final String STRTX = "Aufgabe 1.3;Wärmste Monate von 1 – 5 (1 am wärmsten) \n" +
			"Monat 1 am {0} mit {1} Grad Durchschnittstemperatur \n" +
			"Monat 2 am {2} mit {3} Grad Durchschnittstemperatur \n" +
			"Monat 3 am {4} mit {5} Grad Durchschnittstemperatur \n" +
			"Monat 4 am {6} mit {7} Grad Durchschnittstemperatur \n" +
			"Monat 5 am {8} mit {9} Grad Durchschnittstemperatur";
	private static final String STRSHK = "Aufgabe 1.4;Die höchste Schneehöhe seit der Wetteraufzeichnung in Bremen betrug {0} cm" +
			"und wurde gemessen am {1}";
	
	
	public static void showTm(Object[] values){
		if (values.length == 1) showMessage(STRTM, values); 
			else {
			//TODO 
		}
	}
	public static void showFx(Object[] values){
		if (values.length == 10) showMessage(STRFX, values); 
			else {
			//TODO 
		}
	}
	public static void showTx(Object[] values){
		if (values.length == 10) showMessage(STRTX, values); 
			else {
			//TODO 
		}
	}
	public static void showShk(Object[] values){
		if (values.length == 2) showMessage(STRSHK, values); 
			else {
			//TODO 
		}
	}
	
	private static void showMessage(String str, Object[] values){
		String strText = MessageFormat.format(str, values);
		JOptionPane.showMessageDialog(null, strText.split(";")[1], strText.split(":", 1)[0], JOptionPane.INFORMATION_MESSAGE);
	}	
}