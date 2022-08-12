package kurvendiskussion;

import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author frank
 * Erlaubnis wurde über GitHub erteilt.
 */


public class Polynomdivision {
		
		
	
	  
	  private static final int grad = 2;	//float zu double	Bsp.: (3x^3-22x^2+53x-90):(x-5)=(3x^2-7x+18)+0/(x-5)
	  private static double[] divident = new double[grad + 1];	//     divident          :divisor=quotient + Rest
	  private static double[] divisor = new double[grad + 1];
	  private static double[] quotient = new double[grad + 1];
	  private static double[] rest = new double[grad + 1];

	 
	  
	 
	  
	  public static double[] division(double[] a, double[] b) {
	    // Koeffizienten und Grad für divisor ermitteln
		double koeff = 1;
	    int gradDivisor = 0;
	    int i = b.length-1;
	    while (i>=0) {
	      if (b[i] != 0) {
	        gradDivisor = i;
	        koeff = b[i];
	        break;
	      }
	      i--;
	    }
	    /*
	     * für alle Koeffizienten
	     *  1. Faktor ermitteln
	     *  2. ausmultiplizieren
	     *  3. subtrahieren
	     */
	    double faktor;
	    rest = Arrays.copyOf(a, a.length);
	    
	    for (int j = grad; j >= gradDivisor; j--) {
	      faktor = rest[j] / koeff;
	      quotient[j - gradDivisor] = faktor;
	      
	      if (faktor != 0.0) {
	        // ausmultiplizieren
	    	  double[] zwischenErgebnis = new double[grad + 1];
	        for (int k = 0; k <= gradDivisor; k++) {
	          zwischenErgebnis[k+ j - gradDivisor] = faktor * b[k];          
	        }
	        // subtrahieren
	        for (int k = 0; k <= grad; k++) {
	          rest[k] -= zwischenErgebnis[k];
	        }
	      }
	    }
	  for(double k:quotient) {
	   System.out.println(k);
	  }
	   return quotient;
	  }
}
