package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kurvendiskussion.Polynom;


public class MyFrame extends JFrame implements ActionListener {
		//evtl Grad des Polynoms abfragen
	
	JButton button;
	JTextField textField;
	JCheckBox[] checkBox;
	JPanel panel;
	JLabel label;
	
	public MyFrame() {
		
		this.setTitle("Kurvendiskussion");	//Titel des Frames
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// beim schließen des Fensters wird die ausführung abgeschlossen
		
	

		ImageIcon image= new ImageIcon("KV.png");	//Dateipfad des Bildes , das Bild muss im Ordner liegen
		this.setIconImage(image.getImage()); 	//Neuse Icon des Frames sein Default ist Java-Icon
		
		button= new JButton("berechnen");
		button.addActionListener(this);
		
		checkBox = new JCheckBox[3];
		checkBox[0] =new JCheckBox("Nullstellen berechnen",true);
		checkBox[0].setFocusable(false); //lässt die Box um die CheckBox verschwinden
		
		checkBox[1] =new JCheckBox("Extrema berechnen",true);
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
		
		textField= new JTextField("Gib hier dein Polynom 3.Grades ein.");
		textField.setPreferredSize(new Dimension(250,40));	//Größe des Textfeldes
		textField.setToolTipText("Polynom"); //Wenn man mit der Maus draufgeht erscheint Polynom
		
		
		
		
		this.add(panel);	//hinzufügen zu JFrame
		this.add(button);
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			Polynom p = new Polynom(textField.getText());
			if(checkBox[0].isSelected()) {	//Nulstellen berechnen
				System.out.println(checkBox[0].getText()+ " wurde ausgewählt");	//hier kommt dann die metode rein(nullstellenberechnen)
			}
			System.out.println(p);
			
		}
		
	}
	
}
