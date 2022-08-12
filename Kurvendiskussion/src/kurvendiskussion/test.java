package kurvendiskussion;

public class test {
	public static void main(String[] args) {
		
	
	
	
		Polynom pol= new Polynom("1x^3+9x^2+8x-60");
		double[] ns=pol.nullstellen();
		System.out.println("_______");
		for(double k:ns) {
			System.out.println(k);
		}
	 
	}
}
