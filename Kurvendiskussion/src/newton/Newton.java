package newton;

import kurvendiskussion.Calculator;

public class Newton {
	
	private final static double abweichung=1.0E-15;	//= -1*10^-15
	private final static int maxIterationen=100;
	
	/**
	 * Die Methode macht mindestens 3 Iterationsschritte. D.h. wenn xWert schon die NS wäre,
	 * wird unnötig iterriert. 
	 * @throws KeineNullstelleGefundenException 
	 */
	
	public static double newton(double[] koef,double[] ableitung,double xWert) throws KeineNullstelleGefundenException {
		double xn;	// aktueller schritt x(n)
		double xn1;	// nächster schritt x(n+1)
		double xn2;	// x(n+2)
		int iterationen=0;
		
		//Anlaufrechnung
		xn=xWert;
		
		if(Calculator.calculate(ableitung, xn)==0) {
			xn+=1;
		}
		
		xn1=xn-Calculator.calculate(koef, xn)/Calculator.calculate(ableitung, xn);
		
		if(xn1==xn) {
			System.out.println("Iteration hat nach " + iterationen + " iterationen die Nullstelle "+ xn+" gefunden");
			return xn;
		}
		
		xn2=xn1-Calculator.calculate(koef, xn1)/Calculator.calculate(ableitung, xn1);
		iterationen+=2;
		
		do{
		xn=xn1;
		xn1=xn2;
		xn2=xn1-Calculator.calculate(koef, xn1)/Calculator.calculate(ableitung, xn1);
		iterationen++;
		
		}while(Math.abs(xn2-xn1)>abweichung && iterationen<maxIterationen && xn!=xn2);
		
		
		if(iterationen>=maxIterationen) {
			throw new KeineNullstelleGefundenException("Iteration kommt nach " + iterationen+ " iterationen zu keiner Nullstelle!");
		}else if(xn==xn2) {
			throw new KeineNullstelleGefundenException("Iteration hängt zwischen zwei Werten fest!");
		}else{//vlt mit floor und ceal ns prüfen
			System.out.println("Iteration hat nach " + iterationen + " iterationen die Nullstelle "+ xn2+" gefunden");
			return xn2;
		}
	}
}
