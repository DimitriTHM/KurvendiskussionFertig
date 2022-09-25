package frame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * wird nach Anklicken des "Hilfe"-Buttons erzeugt und soll bei der Eingabe helfen
 *
 */
public class HilfeFrame {
	
	JDialog hilfeDialog;
	JLabel[] label= new JLabel[6];
	
	public HilfeFrame() {
		
		hilfeDialog = new JDialog();
		hilfeDialog.setTitle("Bedienungshilfe");
		hilfeDialog.setModal(true);// um das Hilfefenster sehen zu können
		hilfeDialog.setSize(430,250);
		hilfeDialog.setLocationRelativeTo(null);// Fenster erscheint in der Mitte des Bildschirms
		label[0]=new JLabel("   1. Wählen Sie aus, was für ein Polynom Sie eingeben wollen.");
		label[1]=new JLabel("   2. Wählen Sie aus, was berechnet werden soll.");
		label[2]=new JLabel("   Nur ganze, positive Zahlen für die Exponenten verwenden!");
		label[2].setForeground(Color.RED);
		label[3]=new JLabel("   Koeffizienten 0 und 1 können weggelassen werden");
		label[4]=new JLabel("   3. Button 'berechnen' anklicken.");
		label[5]=new JLabel("   Das Programm führt die Berechnungen durch. Viel Spaß!");
		hilfeDialog.setLayout(new GridLayout(6,0));
		//hinzufügen der Labels zum Dialog-Fenster
		for(int i=0;i<6;i++) {
			hilfeDialog.add(label[i]);
		}
		
		hilfeDialog.setVisible(true);//um das Fenster sehen zu können
		
	}
	
	
}
