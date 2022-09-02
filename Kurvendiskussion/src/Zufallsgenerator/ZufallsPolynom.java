package Zufallsgenerator;

import kurvendiskussion.Polynom;

/**
 * erbt von der Klasse Polynom, um Polynom direkt zu erzeugen
 *
 */
public class ZufallsPolynom extends Polynom {
	
	public ZufallsPolynom() {
		super(new Zufallszahlen().koeffizienten);
	}
}