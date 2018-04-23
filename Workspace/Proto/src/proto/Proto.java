package proto;

import java.io.IOException;

import proto.command.*;

/**
 * Ez a program belépési pontjául szolgáló osztály, ami létrehoz egy parancskezelőt,
 * és beolvassa a parancsokat, majd elindítja a parancsok kezelelését.
 * 
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
