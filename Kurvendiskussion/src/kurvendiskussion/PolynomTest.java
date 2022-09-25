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
	void testSetNullstellenFürPolynomZweitenGrades() {
		Polynom b = new Polynom("x^2-4x-5");
		b.setNullstellen();
		double[] expected= {5.0,-1.0};
		assertArrayEquals(expected, b.getNullstellen());
	}
	
	@Test
	void testSetNullstellenFürPolynomerstenGrades() {
		Polynom b = new Polynom("x-3");
		b.setNullstellen();
		double[] expected= {3.0};
		assertArrayEquals(expected, b.getNullstellen());
	}
	
		
	@Test 
	void TestAbleitung() {
		double[] pol= {6.0,-4.0,3.0,-5.0};
		double[] expected= {-4.0,6.0,-15.0};
		assertArrayEquals(expected,Polynom.ableitung(pol));
	}
	
	@Test 
	void TestAbleitungMitNull() {
		double[] pol= {0.0,0.0,0.0,-5.0};
		double[] expected= {0.0,0.0,-15.0};
		assertArrayEquals(expected,Polynom.ableitung(pol));	
	}
	
	@Test
	void TestAbleitungMitKonstante() {
		double[] pol= {3.0};
		double[] expected = {0.0};
		assertArrayEquals(expected,Polynom.ableitung(pol));
	}
	
	@Test
	void TestAbleitungNullfunktion() {
		double[] pol= {0.0};
		double[] expected = {0.0};
		assertArrayEquals(expected,Polynom.ableitung(pol));
	}
	
	/**
	 * Kann wegen System.exit nicht getestet werden
	@Test
	void TestGradNegativ() {
		String s="-15x^-18.5";
		assertThrows(NumberFormatException.class,()-> Polynom.grad("-5x^-2.5"));
	} 
	 */
	
	
	@Test
	void TestGrad() {
		String s="-15x^18";
		int expected=18;
		assertEquals(expected,Polynom.grad(s));
	} 
	
	@Test
	void TestGrad_1() {
		String s="15";
		int expected=0;
		assertEquals(expected,Polynom.grad(s));
	}
	
	
	@Test
	void TestCoeffizientsAsDouble() {
		String[] s= {"5x^5","-19"};
		double[] expected= {-19.,0.,0.,0.,0.,5.};
		assertArrayEquals(expected, Polynom.coefficientsAsDouble(s));
	}
	
	@Test
	void TestSetExtremaPolZweitenGrades() {
		Polynom pol= new Polynom("x^2-1");
		pol.setExtrema();
		double[] expected= {0.0,-1.0,-1};
		assertArrayEquals(expected,pol.getExtrema()[0],1.0E-15);
	}
	
}
