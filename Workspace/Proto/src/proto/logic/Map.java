package proto.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * A pálya betöltéséért és tárolásáért felelős osztály.
 */
public class Map {
	
	private List<Field> fields = new ArrayList<Field>();
	/**
	 * A pálya fájlból való betöltésére szolgáló függvény.
	 * @param n
	 * @throws IOException 
	 */
	public void loadMap(String fileName) throws IOException {
		
		
		List<String> mapLines = readMapFile(fileName);
		
		
		
		for (String s : mapLines) {
			String objects = s.substring(1, s.length() - 1);
			
			for (String obj : objects.split(" ")) {
				
				switch (obj) {
					case "W":
						break;
					case "E":
						break;
					case "H":
						break;
					case "S":
						break;
					case "X":
						break;
					case "B":
						break;
					default :
						break;
					
				}
				
			}
			
			
			
		}
			
	}
	
	private void load_map() {
		
	}
	
	/**
	 * Getter függvény
	 * @param n A listában az n-edik mező.
	 * @return A kért mező.
	 */
	/*public Field getField(int n) {
		return fields.get(n);
	} */
	
	private List<String> readMapFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readAllLines(path);
	}
}
