package skeleton.game.logic;

import java.util.ArrayList;
import java.util.List;

//Elvileg nem kell a szkeletonban figyelni, hogy vege e a jateknak

public class Game {
	
	private static final Game instance = new Game();
	private List<Worker> workers = new ArrayList<Worker>();
	private List<Map> maps = new ArrayList<Map>();
	private BoxRecorder boxRecorder;
	
	private Game( ) {}
	
	public static Game getInstance(){
		instance.boxRecorder = new BoxRecorder();
		
        return instance;
    }
	
	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	public BoxRecorder getBoxRecorder() {
		return boxRecorder;
	}

	public void setBoxRecorder(BoxRecorder boxRecorder) {
		this.boxRecorder = boxRecorder;
	}

	public List<Map> getMaps() {
		return maps;
	}

	public void setMap(Map m) {
		instance.maps.add(m);
	}
	
	public void addWorker(Worker w) {
		instance.workers.add(w);
	}
	
	public Worker getWorker(int n) {
		return workers.get(n);
	}
	
	public void decreaseBoxes(Box b) {
		//TODO
	}
}
