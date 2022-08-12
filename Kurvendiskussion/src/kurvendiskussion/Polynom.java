package kurvendiskussion;

import newton.KeineNullstelleGefundenException;
import newton.Newton;

//Funktioniert bis jetzt nur für Polynome mit ax^3+bx^2+cx+d ; a,b,c,d !=0
public class Polynom{
	
	private double[] koeffizienten;
	private double[] ersteAbleitung;
	private double[] nullstellen;
	
	
	
	public double[] getKoeffizienten() {
		return koeffizienten;
	}
	
	private void setKoeffizienten(String polynom) {	
		String a= polynom.replaceAll("-", "+-");
		String[] peaces0= a.split("[+]");
		String[] peaces;
		double[] koef;
		
		peaces=checkIfFirstNegativ(peaces0);
		//order peaces=order(peaces)
		koef= coefficientsAsDouble(peaces);
		
		koeffizienten =koef;
	}
	
	public void setErsteAbleitung() {
		this.ersteAbleitung=ableitung(koeffizienten);
	}
	
	public double[] getErsteAbleitung() {
		return ersteAbleitung;
	}
	
	public Polynom(String polynom) {
		this.setKoeffizienten(polynom);
		this.setErsteAbleitung();
		this.nullstellen=new double[koeffizienten.length-1];
	}
	
	/**
	 * Die Koeffizienten müssen in aufsteigender form übergeben werden.
	 * D.h. der erste Koeffizient besitzt keinen x-Teil und der letzte koeffizient 
	 * ist derjenige vor x^n, wobei n der Grad des Polynoms ist.
	 * Bsp.: 3x^2+4x+6 wird so übergeben, {6,4,3}.
	 */
	public Polynom(double[] coef) {	
		this.koeffizienten=coef;
	}
	
	@Override
	public String toString() {
		String string="";
		for(double k:koeffizienten) {
			string+=k + " ";
		}
		return string;
		
	}

	
	private String[] checkIfFirstNegativ(String[] peaces0) {	//checkt ob der koef. vor x^3 negativ ist
		String[] peaces;
		
		if(peaces0[0]==""){			//Falls erste Zahl negativ -> (+  ->   +-) -> wegen split erster Platz =""
			peaces= new String[peaces0.length-1];
			String[] helper=new String[peaces0.length-1];
			for(int i=0;i<peaces0.length-1;i++) {
				helper[i]=peaces0[i+1];
			}
			peaces=helper;
		}else {
			peaces=new String[peaces0.length];
			peaces=peaces0;
		}
		return peaces;
	}

	private double[] coefficientsAsDouble(String[] s) {		//konvertiert die Strings zu double Koeffizienten
		double[]koef= new double[s.length];
		for(int i=3;i>=2;i--){
			koef[i]=Double.parseDouble(s[3-i].substring(0,s[3-i].indexOf("x^"+i)));// vlt nochmal  in einzelne schritte
		}
		koef[1]=Double.parseDouble(s[2].substring(0,s[2].indexOf("x")));
		koef[0]=Double.parseDouble(s[3]);
		
		return koef;
	}

	/*		braucht man hier nicht. Klasse Calculator erstellt.
	public double calculate(double x) {
		double result=0.;
		for(int i=0;i<4;i++) {
			result += (this.koeffizienten[i] * Math.pow(x, i));
		}
		return result;
	}
	*/
	public static double[] ableitung(double[] coef) {
		double[] abl = new double[coef.length-1];
		for(int i=0;i<abl.length;i++) {
			abl[i]=coef[i+1]*(i+1);
		}
		return abl;
	}
	
	public double[] nullstellen() {
	try {	
		 nullstellen[0]= Newton.newton(koeffizienten, ersteAbleitung, koeffizienten[0]);
	}catch(KeineNullstelleGefundenException e1) {
		e1.printStackTrace();
	}
	double[] eingabe={-nullstellen[0],1};
	double[] neuesPolynom=Polynomdivision.division(koeffizienten,eingabe);
	double[] zwischen=Qf.abcFormel(neuesPolynom);
	nullstellen[1]=zwischen[0];
	nullstellen[2]=zwischen[1];
	
	return nullstellen;
	}
	
}
		
	
  
