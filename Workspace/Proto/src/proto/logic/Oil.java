package proto.logic;

public class Oil implements Slime{

	@Override
	public int interact(int f) {
		return f-1;
	}

}
