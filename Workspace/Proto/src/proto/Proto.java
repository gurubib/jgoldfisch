/**
 * 
 */
package proto;

import java.io.IOException;

import proto.logic.Map;

/**
 * @author jgoldfisch
 *
 */
public class Proto {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Map m = new Map();
		
		String fileName = "testmap.txt";
		
		m.loadMap(fileName);

	}

}
