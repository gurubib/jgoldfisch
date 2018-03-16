package skeleton.game.logic;

import java.util.ArrayList;
import java.util.List;

import skeleton.out.MethodWriter;

//Elvileg nem kell a szkeletonban figyelni, hogy vege e a jateknak

public class Game {
	
	private static final Game instance = new Game();
	private List<Worker> workers = new ArrayList<Worker>();
	private Map map = null;
	private BoxRecorder boxRecorder;
	
	private Game( ) {}
	
	public static Game getInstance(){
		instance.boxRecorder = new BoxRecorder();
		
        return instance;
    }
	
	public void setMap(Map m) {
		instance.map = m;
	}
	
	public void init(Worker w) {
		instance.workers.add(w);
	}
	
	public Worker getMainWorker() {
		return workers.get(0);
	}
	
	public void decreaseBoxes(Box b) {
		//TODO
	}
}
