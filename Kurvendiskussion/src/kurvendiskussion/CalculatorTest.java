package kurvendiskussion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



public class CalculatorTest {
	double[] koef= {6.0,-3.0,4.0,-5.0};
	Polynom p = new Polynom("1x^3-10x^2+32x-32");
	
	
	/**
	 * Test ob das Polynom an der Stelle xWert richtig ausgewertet wird
	 */
	@Test
	public void calculateTest() {
		
		double xWert= 0.0;
		double expected= 6;
		assertEquals(expected,Calculator.calculate(koef, xWert));
	
		double xWert1=1.0;
		double expected1=2.0;
		assertEquals(expected1,Calculator.calculate(koef, xWert1));
	
		double xWert2=-10.0;
		double expected2=5436.0;
		assertEquals(expected2,Calculator.calculate(koef, xWert2));
		
		double xWert3=0.5;
		double expected3=4.875;
		assertEquals(expected3,Calculator.calculate(koef, xWert3));
	}
	
	
	/**
	 * Wird die Art der Extrema richtig erkannt?
	 */
	@Test
	public void extremaTestMinimum() {
		double nsErsteAbleitung = 4;	//Minimum
		int expected =  -1;
		assertEquals(expected, Calculator.extrema(p.getZweiteAbleitung(), nsErsteAbleitung));
	}
	
	@Test
	public void extremaTestMaximum() {
		double nsErsteAbleitung = 8/3.;	//Minimum
		int expected =  1;
		assertEquals(expected, Calculator.extrema(p.getZweiteAbleitung(), nsErsteAbleitung));
	}
	
	@Test
	public void extremaTestSattelpunkt() {
		double nsErsteAbleitung = 0;	//Sattelpunkt von x^3 
		int expected =  0;	// 0 steht hier für Sattelpunkt
		double[] zweiteAbleitung = {0.0,6.0}; //zweite Ableitung von x^3
		assertEquals(expected, Calculator.extrema(zweiteAbleitung, nsErsteAbleitung));
	}
	
	/**
	 * Wird die Art des Wendepunktes richtig erkannt?
	 */
	@Test 
	public void wpTestRL() {
		double[] dritteAbl = {6.0}; //-> x^3
		int expected = 1;
		double xWert =0.0;
		assertEquals(expected, Calculator.wendepunkte(dritteAbl, xWert));
		
	}
	
	@Test
	public void wpTestLR() {
		double[] dritteAbl = {-30}; //-> -5x^3+4x^2-3x+6
		int expected = -1;
		double xWert = 4/15.;
		assertEquals(expected, Calculator.wendepunkte(dritteAbl, xWert));
		
	}
	
	@Test
	public void wpTestKeinWendepunkt() {
		double[] dritteAbl= {-24.0,24.0};//->(x-1)^4
		int expected =0;
		double xWert =1.0;
		assertEquals(expected, Calculator.wendepunkte(dritteAbl, xWert));
	}
	 
}




