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


public class MyFrame extends JFrame implements ActionListener {
		//evtl Grad des Polynoms abfragen
	
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// beim schließen des Fensters wird die ausführung abgeschlossen
		
	

		ImageIcon image= new ImageIcon("React.png");	//Dateipfad des Bildes , das Bild muss im Ordner liegen
		//https://studyflix.de/mathematik/kurvendiskussion-3076
		//https://de.wikipedia.org/wiki/React
		this.setIconImage(image.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH)); 	//Neuse Icon des Frames sein Default ist Java-Icon
		button= new JButton("berechnen");
		button.addActionListener(this);
		hilfe= new JButton("Hilfe");
		hilfe.addActionListener(this);
		
		
		
		checkBox = new JCheckBox[3];
		checkBox[0] =new JCheckBox("Nullstellen",true);
		checkBox[0].setFocusable(false); //lässt die Box um die CheckBox verschwinden
		
		checkBox[1] =new JCheckBox("Extrema",true);
		checkBox[1].setFocusable(false); //lässt die Box um die CheckBox verschwinden
		
		checkBox[2] =new JCheckBox("Wendepunkte",true);
		checkBox[2].setFocusable(false); //lässt die Box um die CheckBox verschwinden
		
		
		panel = new JPanel();
		panel.setBackground(Color.gray);
		label= new JLabel("Das soll berechnet werden");
		panel.add(label);
		panel.setLayout(new GridLayout(checkBox.length+1,0));	//+1 weil label in die spalte soll, Gridlayout heißt Tabelle
		for(int i=0;i<checkBox.length;i++) {		//Hinzufügen zu Panel
			panel.add(checkBox[i]);
		}
		
		panel1= new JPanel();
		panel1.setLayout(new GridLayout(4,0));
		label1=new JLabel("Art des Polynoms");
		panel1.setBackground(Color.gray);
		eigenesPolynom= new JRadioButton("Eigenes Polynom",true);
		ganzzahligesPolynom= new JRadioButton("Ganzzahliges Zufallspolynom");
		reellesPolynom= new JRadioButton("Reelles Zufallspolynom");
		group = new ButtonGroup();
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
		
		panel2= new JPanel();
		panel2.setLayout(new GridLayout(2,0));
		panel2.add(button);
		panel2.add(hilfe);
		
		
		this.add(panel1);
		this.add(panel);	//hinzufügen zu JFrame
		this.add(panel2);

		this.add(textField);
		this.setResizable(false);	//Größe des frames ist jetzt nicht mehr änderbar, um unschönes Layout zu verhindern
		this.setLayout(new FlowLayout());	//Layout des Frames
		this.pack();		// automatische Größenanpassung des Frames
		this.setLocationRelativeTo(null); 	//Frame wird in der Mitte angezeigt
		this.setVisible(true);	// Um den Frame zu sehen
	}
	
	//wird später ausgelagert vlt in die Classe die die kurvendiskussion dann macht
	public static void main(String[] args) {
		new MyFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {//erkennt ob der button angeklickt wurde
		
		if(e.getSource()==hilfe) {
			new HilfeFenster();
		}
		
		
		if(e.getSource()==eigenesPolynom) {
			this.textField.setVisible(true);
		}else if(e.getSource()==ganzzahligesPolynom){
			this.textField.setVisible(false);
		}else if(e.getSource()==reellesPolynom) {
			this.textField.setVisible(false);
		}
		
		if(e.getSource()==button) {
			Polynom p=null;
			if(eigenesPolynom.isSelected()) {
				p = new Polynom(textField.getText());
			}else if(ganzzahligesPolynom.isSelected()) {
				p= new GanzzahligesZufallsPolynom();
				System.out.println(p);
			}else if(reellesPolynom.isSelected()) {
				p= new ZufallsPolynom();
				System.out.println(p);
			}
			
			
			
			
			this.dispose();
			double[] ns=null;
			double[][] ex=null;
			double[][] wp=null;
			
			if(checkBox[0].isSelected()) {	//Nulstellen berechnen
				ns =p.nullstellen();
			}
			if(checkBox[1].isSelected()) {	//Extrema berechnen
				ex=p.extrema();
			}
			if(checkBox[2].isSelected()) {	//Wendepunkte berechnen
				wp=p.wendepunkte();
			}
			
			//Ergebnis darstellen
			new FrameErgebnis(ns,ex,wp);
			
			
		}
		
	}
	
}
