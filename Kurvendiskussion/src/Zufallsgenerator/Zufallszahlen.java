package Zufallsgenerator;

public class Zufallszahlen {
	double[] koeffizienten;
	
	public Zufallszahlen() {
		double[] coef= new double[4];
		for(int i=0;i<4;i++) {
			if(Math.random()<0.5) {
				coef[i]=-Math.random()*100;
			}else {
				coef[i]=Math.random()*100;
			}
		}
		this.koeffizienten=coef;
	}
}
