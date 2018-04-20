package proto;

import java.io.IOException;

import proto.command.*;

/**
 * @author jgoldfisch
 *
 */
public class Proto {

	public static void main(String[] args) throws IOException {
		CommandHandler ch = new CommandHandler();
		
		ch.readInput();
	}

}
