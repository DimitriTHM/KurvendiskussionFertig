package kurvendiskussion;

import java.util.Arrays;
/**
 *
 * @author frank
 * Erlaubnis wurde über GitHub erteilt.
 * MartinNiemllerSchule/lk1314
 */
public class Polynomdivision {
	
	/**
	 * übergeben werden zwei "Polynome"
	 * @param divident
	 * @param divisor
	 * @return "Polynom" aus der Division
	 */
	  public static double[] division(double[] divident, double[] divisor) {	
	    // Koeffizienten und Grad für divisor ermitteln
		//Bsp.: (3x^3-22x^2+53x-90):(x-5)	=(3x^2-7x+18)	+0/(x-5)
		//       Divident          :Divisor	=Quotient 		+ Rest
			
		int grad=divident.length-1;
		double[] quotient = new double[grad];	
		double[] rest = new double[grad + 1];
	    int i = divisor.length-1; //	i = Grad den der Quotient höchstens annehmen kann
	    int gradDivisor = i;
	    double koeff = divisor[i];
	    /*
	     * für alle Koeffizienten
	     *  1. Faktor ermitteln
	     *  2. ausmultiplizieren
	     *  3. subtrahieren
	     */
	    double faktor;
	    rest = Arrays.copyOf(divident, divident.length);
	    
	    for (int j = grad; j >= gradDivisor; j--) {
	      faktor = rest[j] / koeff;
	      quotient[j - gradDivisor] = faktor;
	      
	      if (faktor != 0.0) {
	        // ausmultiplizieren
	    	  double[] zwischenErgebnis = new double[grad + 1];	
	        for (int k = 0; k <= gradDivisor; k++) {
	          zwischenErgebnis[k+ j - gradDivisor] = faktor * divisor[k];       
	        }
	        // subtrahieren
	        for (int k = 0; k <= grad; k++) {
	          rest[k] -= zwischenErgebnis[k];
	        }
	      }
	    }
	   return quotient;
	   
	  }
	  
}
