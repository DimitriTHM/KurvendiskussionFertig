package Zufallsgenerator;

import kurvendiskussion.Polynom;
/**
 * 
 * erbt von der Klasse Polynom, um Polynom direkt zu erzeugen
 *
 */
public class GanzzahligesZufallsPolynom extends Polynom {
	
	public GanzzahligesZufallsPolynom() {
		super(new GanzeZufallszahlen().koeffizienten);
	}
	
}
