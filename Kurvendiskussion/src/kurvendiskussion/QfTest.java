package kurvendiskussion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QfTest {

	@Test
	void abcFormelTest() {
	   double[] poly = {-4,0,1};
	   double[] expected= {2,-2};
	   assertArrayEquals(expected,Qf.abcFormel(poly));
	   
	}
	
	
	@Test
	void abcFormelTest1() {
		double[] pol = {0,0,1};
		double[] expected= {0,0};
		assertArrayEquals(expected,Qf.abcFormel(pol),0.0000000001);
	}
	
	@Test
	void abcFormelTest2() {
	   double[] poly = {-3,1,0};
	   double[] expected= {3};
	   assertArrayEquals(expected,Qf.abcFormel(poly));
	}
	
	@Test
	void abcFormelTest3() {
	   double[] poly = {1,0,1};
	   double[] expected= null;
	   assertArrayEquals(expected,Qf.abcFormel(poly));
	}
	
	@Test
	void exceptionTest() {
		double[] pol = {1,0,0};
		assertThrows(ArithmeticException.class,()->Qf.abcFormel(pol));
	}

}
