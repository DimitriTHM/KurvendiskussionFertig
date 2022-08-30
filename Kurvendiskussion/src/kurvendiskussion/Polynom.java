package kurvendiskussion;

import newton.KeineNullstelleGefundenException;
import newton.Newton;

//Funktioniert bis jetzt nur für Polynome mit ax^3+bx^2+cx+d ; a,b,c,d !=0
public class Polynom{	//x^4 könnte ein problem bei extrema haben f(0)=0 ist tp;
	
	private double[] koeffizienten;
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
		String a= polynom.replaceAll("-", "+-");	//damit das negative Vorzeichen nicht verloren geht
		String[] peaces0= a.split("[+]");
		String[] peaces;
		double[] koef;
		
		peaces=checkIfFirstNegativ(peaces0);
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
	
	public Polynom(String polynom) {
		this.setKoeffizienten(polynom);
		this.setErsteAbleitung();
		this.setZweiteAbleitung();
		this.setDritteAbleitung();
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
		this.setErsteAbleitung();
		this.setZweiteAbleitung();
		this.setDritteAbleitung();
		this.nullstellen=new double[koeffizienten.length-1];
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
			return peaces;
		}else {
			return peaces0;
		}
	}

	/**
	 * 
	 private double[] coefficientsAsDouble1(String[] s) {		//konvertiert die Strings zu double Koeffizienten
		double[]koef= new double[grad(s[0])+1];
		for(int i=3;i>=2;i--){
			koef[i]=Double.parseDouble(s[3-i].substring(0,s[3-i].indexOf("x^"+i)));// vlt nochmal  in einzelne schritte
		}
		koef[1]=Double.parseDouble(s[2].substring(0,s[2].indexOf("x")));
		koef[0]=Double.parseDouble(s[3]);
		
		return koef;
	}
	 */
	
	public static double[] coefficientsAsDouble(String[] s) {//konvertiert die Strings zu double Koeffizienten
		int grad=0;
		for(int i =0;i<s.length;i++) {
			if(grad(s[i])>grad) {
				grad=grad(s[i]);
			}
		}
		double[]koef= new double[grad+1];
		for(int i=0;i<s.length;i++){
			int index = grad(s[i]);
			if(index>=2) {
				if(s[i].indexOf("x^")==0) {
					koef[index]=1;
				}else {
					koef[index]=Double.parseDouble(s[i].substring(0,s[i].indexOf("x^")));
				}
			}else if(index==1) {
				if(s[i].indexOf("x")==0) {
					koef[index]=1;
				}else {
					koef[index]=Double.parseDouble(s[i].substring(0,s[i].indexOf("x")));
				}
			}else if(index==0) {
				koef[index]=Double.parseDouble(s[i].substring(0,s[i].length()));
			}
			
		}
		return koef;
	}
	 
	
	public static int grad(String s) {	// gubt den Grad des Polynoms zurück
		if(s.indexOf("x")>=0) {
			if(s.indexOf("x^")>=0) {
				return Integer.parseInt(s.substring(s.indexOf("x^")+2,s.length()));
			}else {
				return 1;
			}
		}
		return 0;
	}
	
	
	public static double[] ableitung(double[] coef) {
		double[] abl = new double[coef.length-1];
		for(int i=0;i<abl.length;i++) {
			abl[i]=coef[i+1]*(i+1);
		}
		return abl;
	}
	
	public double[] nullstellen() {	//void , und einfach ns setzen extrema und wp auch so
	
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
	
	return nullstellen;
	}
	
	public double[][] extrema() {
		this.extrema=new double[2][3];	
		double[] zwischen=Qf.abcFormel(ersteAbleitung);
		extrema[0][0]=zwischen[0];
		extrema[0][1]=Calculator.calculate(koeffizienten, zwischen[0]);
		extrema[0][2]=Calculator.extrema(zweiteAbleitung, extrema[0][0]);
		extrema[1][0]=zwischen[1];
		extrema[1][1]=Calculator.calculate(koeffizienten, zwischen[1]);
		extrema[1][2]=Calculator.extrema(zweiteAbleitung, extrema[1][0]);
		
		return extrema;
	}
	
	public double[][] wendepunkte() {
		this.wendepunkte=new double[1][3];
		wendepunkte[0][0]=-zweiteAbleitung[0]/zweiteAbleitung[1];
		wendepunkte[0][1]=Calculator.calculate(koeffizienten, wendepunkte[0][0]);
		wendepunkte[0][2]=Calculator.wendepunkte(dritteAbleitung, wendepunkte[0][0]);
		
		return wendepunkte;
	}
	
}
  
