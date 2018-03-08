package skeleton.game.logic;

public class Worker extends Movable {

	private int points = 0;
	
	public Worker(Field field) {
		super(field);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushBack(Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushByBox(Box b, Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushByWorker(Worker w, Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scorePoint(Direction d) {
		// TODO Auto-generated method stub
		
	}
	
	void control(Direction d) {
		//TODO
	}
	
	void goBack(Direction d) {
		//TODO
	}
	
	void increasePoints() {
		//TODO
	}

}
