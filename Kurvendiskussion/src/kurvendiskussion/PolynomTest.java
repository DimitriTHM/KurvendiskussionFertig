package kurvendiskussion;

import static org.junit.jupiter.api.Assertions.*;

//import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Timeout;


class PolynomTest {
	Polynom a= new Polynom("-5x^3+3x^2-4x+6");
	@Test
	void testGetKoeffizienten() {
		
		double[] expected= {6.0,-4.0,3.0,-5.0};
		
		assertArrayEquals(expected,a.getKoeffizienten());
	}
	
	@Test
	//@Timeout(value =1 ,unit = TimeUnit.MILLISECONDS)
	void testToString() {
		
		String expected ="6.0 -4.0 3.0 -5.0 ";
		
		assertEquals(expected,a.toString());
	}
	
	/*	//calculate ist im moment keine methode
	void testCalculate() {
		double x=0.5;
		double expected = 33/8.0;	//		33/8 ist integer rechnung -> =4 ; 33/8f ist aber auch möglich
		assertEquals(expected,a.calculate(x));
	}
	*/
	@Test 
	void TestAbleitung() {
		double[] pol= {6.0,-4.0,3.0,-5.0};
		double[] expected= {-4.0,6.0,-15.0};
		assertArrayEquals(expected,Polynom.ableitung(pol));
		
		this.testToString();
		
	}
	

	
	
}
