package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Zufallsgenerator.GanzzahligesZufallsPolynom;
import Zufallsgenerator.ZufallsPolynom;
import kurvendiskussion.Polynom;

/**
 * Benutzeroberfläche
 * Quelle: 
 * 
 *
 */
public class MyFrame extends JFrame implements ActionListener {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;// nachschauen
	//erstellen der verwendeten Tools
	JButton button;
	JButton hilfe;
	JTextField textField;
	JCheckBox[] checkBox;
	JPanel panel;
	JPanel panel1;
	JPanel panel2;
	JLabel label;
	JLabel label1;
	JRadioButton eigenesPolynom;
	JRadioButton ganzzahligesPolynom;
	JRadioButton reellesPolynom;
	ButtonGroup group;
	
	public MyFrame() {
		
		this.setTitle("Kurvendiskussion");	//Titel des Frames
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// beim Schließen des Fensters wird die Ausführung abgeschlossen
		
	

		ImageIcon image= new ImageIcon("React.png");	//Dateipfad des Bildes , das Bild muss im Ordner liegen
		//https://studyflix.de/mathematik/kurvendiskussion-3076
		//https://de.wikipedia.org/wiki/React
		this.setIconImage(image.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH)); 	//Neues Icon des Frames, Default ist Java-Icon
		button= new JButton("berechnen");
		button.addActionListener(this);	//prüft ob der Button angeklickt  wurde
		hilfe= new JButton("Hilfe");
		hilfe.addActionListener(this);
		
		
		//Einfügen der Funktionen die ausgewählt werden können
		checkBox = new JCheckBox[3];
		checkBox[0] =new JCheckBox("Nullstellen",true);
		checkBox[0].setFocusable(false); //lässt die Box um die CheckBox verschwinden
		
		checkBox[1] =new JCheckBox("Extrema",true);
		checkBox[1].setFocusable(false); 
		
		checkBox[2] =new JCheckBox("Wendepunkte",true);
		checkBox[2].setFocusable(false);
		
		
		panel = new JPanel();
		panel.setBackground(Color.gray);
		label= new JLabel("Das soll berechnet werden");
		panel.add(label); 	//label wird auf das Panel eingefügt
		panel.setLayout(new GridLayout(checkBox.length+1,0));	//+1 weil label in die Spalte soll, Gridlayout heißt Tabelle
		for(int i=0;i<checkBox.length;i++) {		//Hinzufügen der Checkboxen zum Panel
			panel.add(checkBox[i]);
		}
		
		//Art des Polynoms abfragen
		panel1= new JPanel();
		panel1.setLayout(new GridLayout(4,0));
		label1=new JLabel("Art des Polynoms");
		panel1.setBackground(Color.gray);
		eigenesPolynom= new JRadioButton("Eigenes Polynom",true);	//Radiobutton, damit nur eins ausgewählt werden kann
		ganzzahligesPolynom= new JRadioButton("Ganzzahliges Zufallspolynom");
		reellesPolynom= new JRadioButton("Reelles Zufallspolynom");
		group = new ButtonGroup();//zu einer Gruppe machen, damit nur einer ausgewählt werden kann
		group.add(eigenesPolynom);
		group.add(ganzzahligesPolynom);
		group.add(reellesPolynom);
		panel1.add(label1);
		panel1.add(eigenesPolynom);
		panel1.add(ganzzahligesPolynom);
		panel1.add(reellesPolynom);
		eigenesPolynom.addActionListener(this);
		ganzzahligesPolynom.addActionListener(this);
		reellesPolynom.addActionListener(this);
		
		textField= new JTextField("Gib hier dein Polynom 3.Grades ein.");
		textField.setPreferredSize(new Dimension(250,40));	//Größe des Textfeldes
		textField.setToolTipText("Polynom"); //Wenn man mit der Maus draufgeht erscheint Polynom
		
		//Buttons hinzufügen
		panel2= new JPanel();
		panel2.setLayout(new GridLayout(2,0));
		panel2.add(button);
		panel2.add(hilfe);
		
		//hinzufügen zu JFrame
		this.add(panel1);
		this.add(panel);	
		this.add(panel2);
		this.add(textField);
		
		this.setResizable(false);	//Größe des frames ist jetzt nicht mehr änderbar, um unschönes Layout zu verhindern
		this.setLayout(new FlowLayout());	//Layout des Frames
		this.pack();		// automatische Größenanpassung des Frames
		this.setLocationRelativeTo(null); 	//Frame wird in der Mitte des Bilschirms angezeigt
		this.setVisible(true);	// Um den Frame zu sehen
	}
	
	
	//erkennt ob der Button angeklickt wurde
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource()==hilfe) {
			new HilfeFenster();
		}
		
		//welcher Radiobutton wurde gewählt
		if(e.getSource()==eigenesPolynom) {
			this.textField.setVisible(true);
		}else if(e.getSource()==ganzzahligesPolynom){
			this.textField.setVisible(false);
		}else if(e.getSource()==reellesPolynom) {
			this.textField.setVisible(false);
		}
		
		//dementsprechend ein neues Polynom erzeugen/eingeben
		if(e.getSource()==button) {
			Polynom p=null;
			if(eigenesPolynom.isSelected()) {
				p = new Polynom(textField.getText());
			}else if(ganzzahligesPolynom.isSelected()) {
				p= new GanzzahligesZufallsPolynom();
				System.out.println(p);	//Polynomausgabe, da man das Polynom nicht kennt
			}else if(reellesPolynom.isSelected()) {
				p= new ZufallsPolynom();
				System.out.println(p);	//Polynomausgabe, da man das Polynom nicht kennt
			}
			
			
			
			
			this.dispose();//sorgt dafür, dass nach Anklicken von "berechnen" MyFrame verschwindet/geschlossen wird
			double[] ns=null;
			double[][] ex=null;
			double[][] wp=null;
			
			//Abfrage, welche Aktion ausgeführt werden soll
			if(checkBox[0].isSelected()) {	//Nulstellen berechnen
				p.setNullstellen();
				ns = p.getNullstellen();
				
			}
			if(checkBox[1].isSelected()) {	//Extrema berechnen
				p.setExtrema();
				ex=p.getExtrema();
			}
			if(checkBox[2].isSelected()) {	//Wendepunkte berechnen
				p.setWendepunkte();
				wp=p.getWendepunkte();
					
			}
			
			//Ergebnis darstellen
			new FrameErgebnis(ns,ex,wp);
			
			
		}
		
	}
	
}
