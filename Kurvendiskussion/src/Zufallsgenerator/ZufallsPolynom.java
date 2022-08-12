package Zufallsgenerator;

import kurvendiskussion.Polynom;

public class ZufallsPolynom extends Polynom {
	
	public ZufallsPolynom() {
		super(new Zufallszahlen().koeffizienten);
	}
}