package frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * Ausgabe der berechneten Ergebnisse
 *
 */
	public class FrameErgebnis extends JFrame {
	
		
		private static final long serialVersionUID = 1L;
		
		/**
		 * erzeugt ein Frame auf dem die Ergebnisse dargestellt werden
		 * @param ns, berechnete Nullstellen des vorher �bergebenen Polynoms
		 * @param extrema
		 * @param wendepunkte
		 */
		public FrameErgebnis(double[] ns,double[][] extrema, double[][] wendepunkte){
			
			this.setTitle("Kurvendiskussion Ergebnis");	//Titel des Frames
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// beim Schlie�en des Fensters wird die Ausf�hrung abgeschlossen
			
			ImageIcon image= new ImageIcon("React.png");	//Dateipfad des Bildes , das Bild muss im Ordner liegen
			this.setIconImage(image.getImage()); 	//Neuse Icon des Frames, Default ist Java-Icon
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3,0));
			panel.setBackground(Color.LIGHT_GRAY);
			
			//Hinzuf�gen der Nullstellen zum Frame
			if(ns!=null) {
				String NsAlsString="";
				for(double k:ns) {
					NsAlsString += " "+k +",		";
				}
				JLabel labelns = new JLabel("Die Nullstellen sind"+ NsAlsString );
				panel.add(labelns);
				
			}
			
			//Hinzuf�gen der Extrempunkte zum Frame
			if(extrema!=null) {	//pr�ft ob Extrema vorliegen, bzw berechnet werden sollten
				String str ="";
				for(int i=0;i<extrema.length;i++) {//Abfrage der Art des Extrempunkts
					if(extrema[i][2]>0) {
						str += " Maximum bei ( " + extrema[i][0] +" , " + extrema[i][1] +" )";//Koordinaten des Extrempunkts
					}else if(extrema[i][2]<0){
						str += " Minimum bei ( " + extrema[i][0] +" , " + extrema[i][1] +" )";
					}else {
						str += " Sattelpunkt bei ( " + extrema[i][0] +" , " + extrema[i][1] +" )";
					}
				}
				JLabel labelep= new JLabel("Extrempunkte: "+str);
				panel.add(labelep);//hinzuf�gen zum Frame
			}
			
			
			//Hinzuf�gen der Wendepunkte zum Frame
			if(wendepunkte!=null) {//pr�ft ob Wendepunkte vorliegen, bzw berechnet werden sollten
				String wps="";
				for(int i=0;i<wendepunkte.length;i++) {
					if(wendepunkte[i][2]>0) {//Abfrage der Art des Wendepunkts
						wps += " RL-Wendepunkt bei ( " + wendepunkte[i][0] + " , " + wendepunkte[i][1] + " )";//Koordinaten des Wendepunkts
					}else if(wendepunkte[i][2]<0) {
						wps += " LR-Wendepunkt bei ( " + wendepunkte[i][0] + " , " + wendepunkte[i][1] + " )";
					}else {
						wps += " Kein WP bei ( " + wendepunkte[i][0] + " , " + wendepunkte[i][1] + " )";
					}
				}
				JLabel labelwp= new JLabel("Wendepunkte: "+wps);
				panel.add(labelwp);//hinzuf�gen zum Frame
			}
			
			
			
			this.add(panel);	//hinzuf�gen zu JFrame
			this.setResizable(false);	//Gr��e des Frames ist jetzt nicht mehr �nderbar, um unsch�nes Layout zu verhindern
			this.setLayout(new FlowLayout());	//Layout des Frames
			this.pack();		// automatische Gr��enanpassung des Frames
			this.setLocationRelativeTo(null); 	//Frame wird in der Mitte angezeigt
			this.setVisible(true);	// Um den Frame zu sehen
		
		}
		
		
	


}
