package kurvendiskussion;

//Dimitri Brunz,Sara Musiol, Version:2021-06 (4.20.0) Datum:.20.01.2022


/**
 * Klasse zur Berechnung quadratischer Funktionen
 *
 *
 */
public class Qf { 
	
	/**
	 * Nutzt ABC-Formel zur Berechnung der Nullstellen quadratischer Polynome
	 * @param koef, Koeffizienten des Polynoms
	 * @return Nullstellen des Polynoms
	 */
	public static double[]  abcFormel(double[] koef) { //f(x)=ax^2+bx+c
		double[] nullstellen=null;
		double c=koef[0];	//c
		double b=koef[1];	//bx
		double a=koef[2];	//ax^2
		if(a==0 && b!=0) {
			nullstellen = new double[1];
			nullstellen[0]=-c/b;
		}else if(a==0 && b==0) {
			throw new ArithmeticException("Polynom ist Konstante");
		}else if(a!=0) {
			if(Math.pow(b, 2)<(4*a*c)) {
				//throw new ArithmeticException("Imagin�re Nullstellen"); ausgeklammert , weil Zufallspolynome zu oft imagin�re Nullstellen haben
				//Ausgabe soll daran erinnern, dass die zwei Nullstellen 0.0 keine Nullstellen sind!!!
				System.out.println("Das Polynom hat nur 1 Nullstelle in den reellen Zahlen.");//�berarbeiten
			}else {
			nullstellen = new double[2];
			nullstellen[0]=(-b+Math.sqrt(Math.pow(b,2)-4*a*c))/(2*a);
			nullstellen[1]=(-b-Math.sqrt(Math.pow(b,2)-4*a*c))/(2*a);
			}
		}
		return nullstellen;
		
	}
		
}
