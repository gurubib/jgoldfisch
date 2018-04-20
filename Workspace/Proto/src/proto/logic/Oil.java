package proto.logic;

import proto.out.MethodWriter;

public class Oil implements Slime{

	@Override
	public int interact(int f) {
		MethodWriter.printOutMethod("Oil.interact",Integer.toString(f));
		MethodWriter.printOutRet(Integer.toString(f-1));
		return f-1;
	}
	
	@Override
	public String toString() {
		return "o";
	}

}
