package skeleton.game.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private static final Game instance = new Game();
	private List<Worker> workers = new ArrayList<Worker>();
	private Map map = null;
	
	
	private Game( ) {}
	
	public static Game getInstance(){
        return instance;
    }
	
	public void setMap(Map m) {
		instance.map = m;
	}
	
	public void init(List<Worker> w) {
		instance.workers = w;
	}
	
	public Worker getMainWorker() {
		return workers.get(0);
	}
}
