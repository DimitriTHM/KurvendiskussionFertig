package newton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NewtonTest {
	
	double[] koef= {-60,8,9,1};	//x^3+9x^2+8x-60, mit NS -6,-5,2
	double[] ableitung= {8,18,3};
	
	
	@Test
	public void newtonTest() {
		
		double xWert=-12;
		double expected=-6.0;
		try {
		assertEquals(expected,Newton.newton(koef,ableitung,xWert));
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
	}
		
	@Test
	public void newtonTest1() {
		double xWert1=-2 ;	//-2 und -4 wirft Exception 
		double expected1=-5.0;
		try {
		assertEquals(expected1,Newton.newton(koef,ableitung,xWert1));
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
	}
		
	@Test
	public void newtonTestException() {
		double xWert1=-4 ;	//-4 wirft Exception 
		assertThrows(KeineNullstelleGefundenException.class,() -> Newton.newton(koef, ableitung, xWert1));
	}
	
	@Test
	public void newtonTest2() {
		double xWert2=10 ;
		double expected2=2.0;
		try {
		assertEquals(expected2,Newton.newton(koef,ableitung,xWert2));
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void newtonTest3() {
		double xWert3=3.;
		double expected3=3;
		double[] koef1= {0,0,-3,1};
		double[] ableitung1= {0,-6,3};
		
		try {
			assertEquals(expected3,Newton.newton(koef1,ableitung1,xWert3));
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void newtonTestGerade() {
		double xWert2=-3 ;
		double expected2=3.0;
		double[] koef= {-3.0,1.0};
		double[] ableitung = {1.0};
		try {
		assertEquals(expected2,Newton.newton(koef,ableitung,xWert2));
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void newtonTestQuadrat() {
		double xWert2=-17.5 ;
		double expected2=-3.5;
		double[] koef= {-17.5,-1.5,1};
		double[] ableitung = {-1.5,2};
		try {
		assertEquals(expected2,Newton.newton(koef,ableitung,xWert2));
		}catch(KeineNullstelleGefundenException e1) {
			e1.printStackTrace();
		}
	}

}
