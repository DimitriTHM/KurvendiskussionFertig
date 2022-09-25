package newton;

import kurvendiskussion.Calculator;
/**
 * Klasse zur Berechnung der Nullstellen durch das Newtonverfahren
 * 
 *
 */
public class Newton {
	
	private final static double abweichung=1.0E-15;	//= 1*10^-15
	private final static int maxIterationen=200;
	

	/**
	 * Die Methode macht mindestens 3 Iterationsschritte. D.h. wenn xWert schon die Nullstelle ist,
	 * wird trotzdem iteriert. 
	 * @throws KeineNullstelleGefundenException 
	 * @param koef, Koeffizienten des Polynoms
	 * @param ableitung
	 * @param xWert, Startpunkt der Iteration
	 * @return	eine Nullstelle
	 * @throws KeineNullstelleGefundenException
	 */
	public static double newton(double[] koef,double[] ableitung,double xWert) throws KeineNullstelleGefundenException {
		double xn;	// aktueller Schritt x(n)
		double xn1;	// nächster Schritt x(n+1)
		double xn2;	// x(n+2)
		int iterationen=0;
		
		//Anlaufrechnung
		xn=xWert;
		
		if(Calculator.calculate(ableitung, xn)==0) {	//Fängt ab, dass durch 0 geteilt wird, indem der Startwert um 1 erhöht wird
			xn+=1;
		}
		
		xn1=xn-Calculator.calculate(koef, xn)/Calculator.calculate(ableitung, xn);
		iterationen+=1;
		
		if(xn1==xn) {
			System.out.println("Iteration hat nach " + iterationen + " iterationen die Nullstelle "+ xn+" gefunden");
			return xn;
		}
		
		xn2=xn1-Calculator.calculate(koef, xn1)/Calculator.calculate(ableitung, xn1);
		iterationen+=1;
		
		do{
		xn=xn1;
		xn1=xn2;
		xn2=xn1-Calculator.calculate(koef, xn1)/Calculator.calculate(ableitung, xn1);
		iterationen++;
		
		}while(Math.abs(xn2-xn1)>abweichung && iterationen<maxIterationen && xn!=xn2);
		//xn!=xn2, damit der Rechner nicht in eine "Endlosschleife" zwischen zwei Werten kommt
		
		
		
		if(iterationen>=maxIterationen){
			throw new KeineNullstelleGefundenException("Iteration kommt nach " + iterationen+ " iterationen zu keiner Nullstelle!");
		}else if(xn==xn2 && xn!=xn1) { //wenn xn = xn2 und xn1 dann Wert gefunden
			throw new KeineNullstelleGefundenException("Iteration hängt zwischen zwei Werten fest!");
		}else{
			System.out.println("Iteration hat nach " + iterationen + " iterationen die Nullstelle "+ xn2+" gefunden");
			return xn2;
		}
	}
}
