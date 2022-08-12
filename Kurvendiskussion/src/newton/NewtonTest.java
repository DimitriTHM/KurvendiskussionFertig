package newton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NewtonTest {
	
	@Test
	public void newtonTest() {
		double[] koef= {-60,8,9,1};	//x^3+9x^2+8x-60, mit NS -6,-5,2
		double[] ableitung= {8,18,3};
		
		
		double xWert=-12;
		double expected=-6.0;
		try {
		assertEquals(expected,Newton.newton(koef,ableitung,xWert));
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
		
		
		double xWert1=-2 ;
		double expected1=-5.0;
		try {
		assertEquals(expected1,Newton.newton(koef,ableitung,xWert1));
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
		double xWert2=10 ;
		double expected2=2.0;
		try {
		assertEquals(expected2,Newton.newton(koef,ableitung,xWert2));
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
	}

}
