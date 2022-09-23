package kurvendiskussion;

import frame.HilfeFenster;
import frame.MyFrame;
import newton.KeineNullstelleGefundenException;
import newton.Newton;

/**
 * 	Die Klasse setzt die Koeffizienten des Polynoms und führt die nötigen 
 * 	Berechnungen für die Bestimmung der Ableitungen, Wendepunkte und Nulllstellen durch.
 *
 */
public class Polynom{	

	private int grad;
	private double[] koeffizienten;	//private damit es von uns (oder anderen Entwicklern) nicht versehentlich geändert wird 
	private double[] ersteAbleitung;
	private double[] zweiteAbleitung;
	private double[] dritteAbleitung;
	private double[] nullstellen;
	private double[][] extrema;
	private double[][] wendepunkte;
	
	
	
	public double[] getKoeffizienten() {
		return koeffizienten;
	}
	
	private void setKoeffizienten(String polynom) {	
		String a= polynom.replaceAll("-", "+-");	//ersetzt - mit +- , damit das negative Vorzeichen nicht verloren geht
		String[] peaces0= a.split("[+]");			//Splittet den String bei +
		String[] peaces;
		double[] koef;						
		
		peaces=checkIfFirstEmpty(peaces0);
		koef= coefficientsAsDouble(peaces);
		
		koeffizienten =koef;
	}
	
	public void setErsteAbleitung() {
		this.ersteAbleitung=ableitung(koeffizienten);
	}
	
	public double[] getErsteAbleitung() {
		return ersteAbleitung;
	}
	
	public void setZweiteAbleitung() {
		this.zweiteAbleitung=ableitung(ersteAbleitung);
	}
	
	public double[] getZweiteAbleitung() {
		return zweiteAbleitung;
	}
	
	public void setDritteAbleitung() {
		this.dritteAbleitung=ableitung(zweiteAbleitung);
	}
	
	public double[] getdritteAbleitung() {
		return dritteAbleitung;
	}
	
	
	public double[] getNullstellen() {
		return nullstellen;
	}

	public double[][] getExtrema() {
		return extrema;
	}

	public double[][] getWendepunkte() {
		return wendepunkte;
	}

	/**
	 * setzt dei Ableitungen und Koeffizienten des Polynoms.
	 * @param polynom
	 */
	public Polynom(String polynom) { //koef ermitteln dann Polynom(koef) aufrufen
		this.setKoeffizienten(polynom);
		this.setErsteAbleitung();
		this.setZweiteAbleitung();
		this.setDritteAbleitung();
		this.nullstellen=new double[koeffizienten.length-1];
		this.extrema=new double[koeffizienten.length-2][2];
		this.wendepunkte= new double[koeffizienten.length-3][2];
		
	}
	
	/**
	 * wie Zeile 67, diesmal werden die Koeffizienten übergeben
	 */
	public Polynom(double[] coef) {	
		this.koeffizienten=coef;
		this.setErsteAbleitung();
		this.setZweiteAbleitung();
		this.setDritteAbleitung();
		this.nullstellen=new double[koeffizienten.length-1];
		this.extrema=new double[koeffizienten.length-2][2];
		this.wendepunkte= new double[koeffizienten.length-3][2];
	}
	

	/**
	 * Überprüft ob der erste Eintarg im Array leer ist
	 * und gibt in diesem Fall ein Array ohne den ersten Platz zurück
	 * @param peaces0
	 * @return String[]
	 */
	private String[] checkIfFirstEmpty(String[] peaces0) {	
		String[] peaces;
		
		if(peaces0[0]==""){			//Falls erste Zahl negativ -> (+  ->   +-) -> wegen split erster Platz =""
			// braucht man nicht peaces= new String[peaces0.length-1];
			String[] helper=new String[peaces0.length-1];
			for(int i=0;i<peaces0.length-1;i++) {
				helper[i]=peaces0[i+1];
			}
			peaces=helper;
			return peaces;
		}else {
			return peaces0;
		}
	}

	/**
	 * konvertiert die Strings zu double Koeffizienten
	 * @param str
	 * @return gibt die konvertierten Strings als double zurück
	 */
	public static double[] coefficientsAsDouble(String[] str) {	
		
		//ermittelt den Grad des Polynoms
		int grad=0;
		for(int i =0;i<str.length;i++) {
			if(grad(str[i])>grad) {
				grad=grad(str[i]);
			}
		}
		
		
		double[]koef= new double[grad+1];
		for(int i=0;i<str.length;i++){
			int index = grad(str[i]);// An welche stelle im Array "koef" kommt der Koeffizient? Z.B. 7x^2, koef[3]=7
			if(index>=2) {
				if(str[i].indexOf("x^")==0) {
					koef[index]=1;
				}else {
					koef[index]=Double.parseDouble(str[i].substring(0,str[i].indexOf("x^")));
				}
			}else if(index==1) {
				if(str[i].indexOf("x")==0) {
					koef[index]=1;
				}else {
					koef[index]=Double.parseDouble(str[i].substring(0,str[i].indexOf("x")));
				}
			}else if(index==0) {
				if(str[i].indexOf("x")!=-1) {
					koef[index]=Double.parseDouble(str[i].substring(0,str[i].indexOf("x")));
				}else {
					koef[index]=Double.parseDouble(str[i].substring(0,str[i].length()));
				}
			}
			
		}
		return koef;
	}
	 
	/**
	 * Ermittelt den Grad des übergebenen Strings
	 * @param str
	 * @return Grad des Strings
	 */
	public static int grad(String str) {
		if(str.indexOf("x")>=0) {
			if(str.indexOf("x^")>=0) {	//>0 abragen
				try {
					int grad = Integer.parseInt(str.substring(str.indexOf("x^")+2,str.length()));
					return grad;
				}catch(NumberFormatException e) {
					new HilfeFenster();
					System.exit(0);
				}
			}else {
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * leitet die übergebenen Koeffizienten ab
	 * @param coef
	 * @return Ableitung des "Polynoms", 
	 */
	public static double[] ableitung(double[] coef) {
		double[] abl = new double[coef.length-1];
		for(int i=0;i<abl.length;i++) {
			abl[i]=coef[i+1]*(i+1);
		}
		return abl;
	}
	
	/**
	 * Ruft die Berechnung der Nullstellen auf und setzt diese in das Array this.nullstellen
	 */
	public void setNullstellen() {	
	
	if(koeffizienten.length>2) {
		try {		//hier könntre man eine for schleife einbauenn 
			nullstellen[0]= Newton.newton(koeffizienten, ersteAbleitung, koeffizienten[0]);
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
		double[] eingabe={-nullstellen[0],1}; //Division mit Nullstelle ns: (x-ns)
		double[] neuesPolynom=Polynomdivision.division(koeffizienten,eingabe);
		double[] zwischen=Qf.abcFormel(neuesPolynom);
		nullstellen[1]=zwischen[0];
		nullstellen[2]=zwischen[1];
	}else {
		double[] zwischen=Qf.abcFormel(koeffizienten);
		nullstellen[1]=zwischen[0];
		nullstellen[2]=zwischen[1];
	}
	
	}
	
	/**
	 * Berechnet die Extrema und speichert x-Wert, y-Wert und Art des Extremums in dieser Reihenfolge
	 */
	public void setExtrema() {
		this.extrema=new double[2][3];	
		double[] zwischen=Qf.abcFormel(ersteAbleitung);
		extrema[0][0]=zwischen[0];
		extrema[0][1]=Calculator.calculate(koeffizienten, zwischen[0]);
		extrema[0][2]=Calculator.extrema(zweiteAbleitung, extrema[0][0]);
		extrema[1][0]=zwischen[1];
		extrema[1][1]=Calculator.calculate(koeffizienten, zwischen[1]);
		extrema[1][2]=Calculator.extrema(zweiteAbleitung, extrema[1][0]);
		
		
	}
	
	/**
	 * Berechnet den Wendepunkt und speichert x-Wert, y-Wert und Art des Wendepunkts
	 */
	public void setWendepunkte() {
		this.wendepunkte=new double[1][3];
		wendepunkte[0][0]=-zweiteAbleitung[0]/zweiteAbleitung[1];
		wendepunkte[0][1]=Calculator.calculate(koeffizienten, wendepunkte[0][0]);
		wendepunkte[0][2]=Calculator.wendepunkte(dritteAbleitung, wendepunkte[0][0]);
		
		
	}
	
}
  
