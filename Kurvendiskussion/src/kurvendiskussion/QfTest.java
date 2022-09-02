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

}
