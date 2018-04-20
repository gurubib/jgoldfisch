package proto.logic;

import proto.out.MethodWriter;

public class Honey implements Slime{

	@Override
	public int interact(int f) {
		MethodWriter.printOutMethod("Honey.interact",Integer.toString(f));
		MethodWriter.printOutRet(Integer.toString(f-3));
		return f-3;
	}
	
	@Override
	public String toString() {
		return "h";
	}

}
