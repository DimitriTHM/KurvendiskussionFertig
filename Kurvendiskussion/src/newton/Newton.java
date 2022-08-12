package newton;

import kurvendiskussion.Calculator;

public class Newton {
	
	private final static double abweichung=1.0E-12;	//= -1*10^-12
	private final static int maxIterationen=100;
	
	
	public static double newton(double[] koef,double[] ableitung,double xWert) throws KeineNullstelleGefundenException{
		double xn1;	// nächster schritt x(n+1)
		double xn;	// aktueller schritt x(n)
		int iterationen=0;
		xn1=xWert;	//xn1 verwendet damit die do-Schleife schöner wird
		
		do{
		xn=xn1;
		xn1=xn-Calculator.calculate(koef, xn)/Calculator.calculate(ableitung, xn);
		iterationen++;
		}while(Math.abs(xn1-xn)>abweichung && iterationen<maxIterationen);
		
		
		if(iterationen>=maxIterationen) {
			throw new KeineNullstelleGefundenException("Iteration kommt nach " + iterationen+ " iterationen zu keiner Nullstelle!");
		}else {
			System.out.println("Iteration hat nach " + iterationen + " iterationen die Nullstelle "+ xn1+" gefunden");
			return xn1;
		}
		
	}
	

}
