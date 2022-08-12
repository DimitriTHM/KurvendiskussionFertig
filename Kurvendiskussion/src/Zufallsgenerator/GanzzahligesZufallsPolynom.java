package Zufallsgenerator;

import kurvendiskussion.Polynom;

public class GanzzahligesZufallsPolynom extends Polynom {
	
	public GanzzahligesZufallsPolynom() {
		super(new GanzeZufallszahlen().koeffizienten);
	}
	
}
