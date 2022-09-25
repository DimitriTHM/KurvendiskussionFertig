package kurvendiskussion;


/**
 * berechnet Funktionswert 
 * und um welche Art von Extrema und Wendepunkt es sich handelt 
 */
public class Calculator {
	
	/**
	 * berechnet den Funktionswert
	 * @param koef, Koeffizienten des Polynoms
	 * @param x, an dieser Stelle soll das Polynom ausgewertet werden
	 * @return gibt den Wert des Polynoms an der Stelle x zurück
	 */
	public static double calculate(double[] koef,double x) {	
		double result=0.;
		for(int i=0;i<koef.length;i++) {
			result += (koef[i] * Math.pow(x, i));
		}
		return result;
	}
	
	/**
	 * ermittelt Art des Extremums
	 * @param zweiteAbleitung, Koeffizienten der zweiten Ableitung
	 * @param nullstelleErsteAbleitung, eine Nullstelle der ersten Ableitung
	 * @return -1 bedeutet Minimum
	 * 			1 bedeutet Maximum
	 * 			0 bedeutet Sattelpunkt
	 */
	public static int extrema(double[] zweiteAbleitung ,double nullstelleErsteAbleitung){	
																							
			double ergebnis =calculate(zweiteAbleitung,nullstelleErsteAbleitung);
			
			if(ergebnis>0) {
			return -1;
			}else if(ergebnis<0){
			return 1;
			}else {
			return 0;
			
		}
	}
	
	/**
	 * ermittelt Art des Wendepunktes
	 * @param dritteAbleitung, Koeffizienten der 3. Ableitung
	 * @param nullstelleZweiteAbleitung, eine Nullstelle der 2. Ableitung
	 * @return -1 bedeutet LR-Wendepunkt
	 * 			1 bedeutet RL-Wendepunkt
	 * 			0 bedeutet kein Wendepunkt
	 */
	public static int wendepunkte(double[] dritteAbleitung, double nullstelleZweiteAbleitung) {	
		
		double ergebnis=calculate(dritteAbleitung, nullstelleZweiteAbleitung);
		if(ergebnis>0) {
			return 1; 
		}else if(ergebnis<0) {
			return -1;
		}else {
			return 0;
			
		}
		
		
	}
	
}
