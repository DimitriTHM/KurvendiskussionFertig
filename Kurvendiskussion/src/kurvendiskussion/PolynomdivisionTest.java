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

}
