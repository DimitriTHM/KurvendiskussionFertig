package kurvendiskussion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Zufallsgenerator.GanzzahligesZufallsPolynom;
import frame.FrameErgebnis;
import newton.KeineNullstelleGefundenException;
import newton.Newton;

public class test {
	public static void main(String[] args) {
		
	
		Polynom p =new GanzzahligesZufallsPolynom();
		System.out.println(p);
	
		
		 Polynom pol= new Polynom("4x^3+6x^2-15x-9");//3x^3-5x^2-6x+7	,
		 pol.setExtrema();
		 pol.setWendepunkte();
		 //double[] ns=pol.nullstellen();
		// for(double k:ns) {
		//	System.out.println(k);
		// }	
		double[][] ex= pol.getExtrema();
		//for(int i=0;i<ex.length;i++) {
		//	for(int j=0;j<ex[i].length;j++) {
		//		System.out.print(" " +ex[i][j]);
		//	}
		//	System.out.println(" ");
		//}
		
		double[][] wp=pol.getWendepunkte();
		
		
		//double[] a= {1,0,1}; //Imaginäre Ns
		//Qf.abcFormel(a);
		//System.out.println(Polynom.grad("-15x^-18.5"));
		double[] m = null;
		double[] f= {5.0,6.0};
		m=f;
		double[] abl= {3.0,4.0};
		double[] k=Polynom.ableitung(abl);
		System.out.println(k[0]);
		System.out.println(m[0]);
	}
}
