package kurvendiskussion;

import frame.HilfeFrame;
import frame.MyFrame;
import newton.KeineNullstelleGefundenException;
import newton.Newton;

/**
 * 	Die Klasse setzt die Koeffizienten des Polynoms und führt die nötigen 
 * 	Berechnungen für die Bestimmung der Ableitungen, Wendepunkte und Nulllstellen durch.
 *
 */
public class Polynom{	

	
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
		String[] peaces0= a.split("[+]");			//Splittet den String bei +, peaces0[0]="" ist möglich, wird gleich geprüft
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
	
	public void setNullstellen() {	
		this.nullstellen=berechneNullstellen(koeffizienten);
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
	 * Berechnet die Extrema und speichert x-Wert, y-Wert und Art des Extremums in dieser Reihenfolge
	 */
	public void setExtrema() {
		if(koeffizienten.length>2) {//Polynom mind. quadratisch?
			this.extrema=new double[koeffizienten.length-2][3];	
			double[] nsErsteAbleitung=berechneNullstellen(ersteAbleitung);
			for(int i =0;i<koeffizienten.length-2;i++) {
				extrema[i][0]=nsErsteAbleitung[i];
				extrema[i][1]=Calculator.calculate(koeffizienten, nsErsteAbleitung[i]);
				extrema[i][2]=Calculator.extrema(zweiteAbleitung, extrema[i][0]);
			}
		}	
	}
	
	/**
	 * Berechnet den Wendepunkt und speichert x-Wert, y-Wert und Art des Wendepunkts
	 */
	public void setWendepunkte() {
		if(koeffizienten.length>3) {//Polynom mind. qubisch?
			this.wendepunkte=new double[koeffizienten.length-3][3];
			double[] nsZweiteAbleitung=berechneNullstellen(zweiteAbleitung);
			for(int i = 0;i<koeffizienten.length-3;i++) {
				wendepunkte[i][0]=nsZweiteAbleitung[i];
				wendepunkte[i][1]=Calculator.calculate(koeffizienten, nsZweiteAbleitung[i]);
				wendepunkte[i][2]=Calculator.wendepunkte(dritteAbleitung, nsZweiteAbleitung[i]);
			}
		}
		
	}

	/**
	 * setzt dei Ableitungen und Koeffizienten des Polynoms.
	 * @param polynom
	 */
	public Polynom(String polynom) { 
		this.setKoeffizienten(polynom);
		this.setErsteAbleitung();
		this.setZweiteAbleitung();
		this.setDritteAbleitung();
		/**
		 * this.nullstellen=new double[koeffizienten.length-1];
		this.extrema=new double[koeffizienten.length-2][2];
		this.wendepunkte= new double[koeffizienten.length-3][2];
		 */
		
	}
	
	/**
	 * wie Zeile 67, diesmal werden die Koeffizienten übergeben
	 */
	public Polynom(double[] coef) {	
		this.koeffizienten=coef;
		this.setErsteAbleitung();
		this.setZweiteAbleitung();
		this.setDritteAbleitung();
		/**
		 * this.nullstellen=new double[koeffizienten.length-1];
		this.extrema=new double[koeffizienten.length-2][2];
		this.wendepunkte= new double[koeffizienten.length-3][2];
		 */
	}
	
	/**
	 * gibt das Polynom in form a+bx+cx^2 zurück
	 */
	@Override
	public String toString() {
		String str="" + koeffizienten[0];
		for(int i = 1; i<koeffizienten.length;i++) {
			if(koeffizienten[i]>=0) {
				str += "+" + koeffizienten[i] + "x^" + i;
			}else {
				str +=  koeffizienten[i] + "x^" + i;
			}
		}
		return str;
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
		
		//ermittelt den Grad des Polynoms um ein Array anzulegen
		int grad=0;
		for(int i =0;i<str.length;i++) {
			if(grad(str[i])>grad) {
				grad=grad(str[i]);
			}
		}
		
		if(grad==0) {
			System.out.println("Keine Kurvendiskussion für Konstatnten!");
			new HilfeFrame();
			System.exit(0);
		}
		
		
		double[]koef= new double[grad+1];
		for(int i=0;i<str.length;i++){
			int index = grad(str[i]);// An welche stelle im Array "koef" kommt der Koeffizient? Z.B. 7x^2, koef[3]=7
			try { //fängt ab wenn Koeffizienten falsch sind
				if(index>=2) {
					if(str[i].indexOf("x^")==0) {//bsp. x^2
						koef[index]=1;
					}else {
						if(str[i].substring(0,str[i].indexOf("x^")).equals("-")){
							koef[index]=-1;
						}else {
							koef[index]=Double.parseDouble(str[i].substring(0,str[i].indexOf("x^")));
						}
					}
				}else if(index==1) {
					if(str[i].indexOf("x")==0) {
						koef[index]=1;
					}else {
						if(str[i].substring(0,str[i].indexOf("x")).equals("-")) {
							koef[index]=-1;
						}else {
							koef[index]=Double.parseDouble(str[i].substring(0,str[i].indexOf("x")));
						}
					}
				}else if(index==0) {//Normalerweise Konstante, kann aber auch x^0 sein
					if(str[i].indexOf("x")!=-1) {
						if(str[i].substring(0,str[i].indexOf("x")).equals("-")) {
							koef[index]=-1;
						}else {
							koef[index]=Double.parseDouble(str[i].substring(0,str[i].indexOf("x")));
						}
					}else {
						koef[index]=Double.parseDouble(str[i].substring(0,str[i].length()));
					}
				}
			}catch(NumberFormatException e) {
				System.out.println("Falsche Eingabe der Koeffizienten!");
				new HilfeFrame();
				System.exit(0);
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
			if(str.indexOf("x^")>=0) {	
				try {
					return Integer.parseInt(str.substring(str.indexOf("x^")+2,str.length()));
				}catch(NumberFormatException e) {
					new HilfeFrame();
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
		if(coef.length>1) {//Wenn coef.length = 1 dann ist da sPolynom eine Konstante
			double[] abl = new double[coef.length-1];
			for(int i=0;i<abl.length;i++) {
				abl[i]=coef[i+1]*(i+1);
			}
			return abl;
		}else {
			double[] zwischen= {0.0};
			return zwischen;
		}
	}
	
	
	
	
	/**
	 * berechnet die Nullstellen des übergebenen Polynoms
	 * @param koef, Koeffizienten des Polynoms
	 * @return berechnete Nullstellen des Polynomms
	 */
	public double[] berechneNullstellen(double[] koef) {	
		double[] ns;
		double[] koefPolGrad2;
		if(koef.length>3) {
			ns=new double[3];
			try {		
				ns[0]= Newton.newton(koef, ableitung(koef), koef[0]);
			}catch(KeineNullstelleGefundenException e1) {
				e1.printStackTrace();
			}
			double[] divisor={-ns[0],1}; //Division mit Nullstelle ns: (x-ns)
			koefPolGrad2=Polynomdivision.division(koef,divisor);
			double[] zwischen=Qf.abcFormel(koefPolGrad2);
			ns[1]=zwischen[0];
			ns[2]=zwischen[1];
			
		}else if(koef.length==3){
			koefPolGrad2=koef;
			double[] zwischen=Qf.abcFormel(koefPolGrad2);
			ns=zwischen;
		}else if(koef.length==2){
			double[] zwischen= {-koef[0]/koef[1]};
			ns=zwischen;
		}else {//koef ist konstante
			ns=null;
		}
		return ns;
	
	}
	
	
	
}
  
