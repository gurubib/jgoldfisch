package proto;

import java.io.IOException;

import proto.command.*;
import proto.logic.Map;

/**
 * @author jgoldfisch
 *
 */
public class Proto {

	public static void main(String[] args) throws IOException {
		CommandHandler ch = new CommandHandler();
		
		ch.readInput();
		
		//Map map = new Map();
		
		//map.loadMap("testmap.txt");
	}

}
