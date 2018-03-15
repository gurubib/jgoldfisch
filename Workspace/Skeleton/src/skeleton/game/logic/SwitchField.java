package skeleton.game.logic;

public class SwitchField extends Field {

	private boolean active = false;
	private HoleField hole;
	
	@Override
	public Movable boxEnters(Box b, Direction d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void boxArrived(Box b) {
		//TODO
	}
	
	@Override
	public void remove(Movable m) {
		//TODO
	}
	
	public void acitvate() {
		//TODO
	}
	
	public void deactivate() {
		//TODO
	}
	
	public void holeInteracted(Movable m) {
		//TODO
	}

}
