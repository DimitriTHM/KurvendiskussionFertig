package kurvendiskussion;

public class Calculator {
	
	public static double calculate(double[] koef,double x) {	//x=stelle an der das Polynom(koef) ausgewertet werden soll
		double result=0.;
		for(int i=0;i<koef.length;i++) {
			result += (koef[i] * Math.pow(x, i));
		}
		return result;
	}
	
	
public String extrema(double[] zweiteAbleitung ,double[] nullstellenErsteAbleitung){	//Nulstellen der Ableitungen
	double ergebnis;
	String a="";
		for(double k : nullstellenErsteAbleitung) {
			ergebnis =calculate(zweiteAbleitung,k);
			
			if(ergebnis>0) {
			a	+=	 " Minimum an der Stelle "+ k;
			}else if(ergebnis<0){
			a	+= 	 " Maximum an der Stelle "+k;
			}else {
			a	+= 	 " Wendepunkt an der Stelle "+k;
			}
		}
		return a;
	}
	
	
}
