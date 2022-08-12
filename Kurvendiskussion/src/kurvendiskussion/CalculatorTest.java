package kurvendiskussion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



public class CalculatorTest {
	
	
	double[] koef= {6.0,-3.0,4.0,-5.0};
	//Wenn ein Test falsch ist wird nicht angezeigt welcher falsch ist
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
	 
}
