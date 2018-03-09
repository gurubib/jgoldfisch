package skeleton.game.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private static final Game instance = new Game();
	
	private static List<Worker> workers = new ArrayList<Worker>();
	
	private Game( ) {}
	
	public static Game getInstance(){
        return instance;
    }
	
	public static void init(List<Worker> w) {
		workers = w;
	}
}
