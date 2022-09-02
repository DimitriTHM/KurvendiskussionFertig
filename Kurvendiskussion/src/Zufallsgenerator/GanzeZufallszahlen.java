package Zufallsgenerator;

/**
 * 
 * gibt ein Array von Koeffizienten mit ganzen Zahlen zurück
 *
 */
public class GanzeZufallszahlen {
	double[] koeffizienten;
	
	public GanzeZufallszahlen() {
		double[] coef= new double[4];
		for(int i=0;i<4;i++) {
			if(Math.random()<0.5) {
				coef[i]=Math.floor(-Math.random()*100);
			}else {
				coef[i]=Math.floor(Math.random()*100);
			}
		}
		this.koeffizienten=coef;
	}

}
