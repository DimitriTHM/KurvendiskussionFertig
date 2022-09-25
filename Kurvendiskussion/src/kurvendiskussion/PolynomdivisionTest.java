package kurvendiskussion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PolynomdivisionTest {
	
	
	double[] polA= {-4,0,1};
	double[] polB={-2,1};
	
	@Test
	void divisionTest(){
		double[] expected= {2,1};
		double[] ergebnis= Polynomdivision.division(polA, polB);
		assertArrayEquals(expected,ergebnis);
	}

	@ Test
	void divisionTestDivisor0() {
		double[] polyA = {0,-5,1};//x^2-5x
		double[] polyB = {0.0,1};//x
		double[] expected = {-5.0,1.0};//x-5
		assertArrayEquals(expected,Polynomdivision.division(polyA, polyB));
	}
}
