package skeleton.game.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import skeleton.out.MethodWriter;

public class SimpleField extends Field {

	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("SimpleField.boxEnters",  b.toString() + ", " + d.toString());
		
		Movable movable = getMovable();
		
		this.setMovable(b);
		b.place(this);
		this.getNeighbor(d.getOpposite()).remove(b);
		
		
		
		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("SimpleField.workerEnters", w.toString() + ", " + d.toString());
		
		MethodWriter.printOutQuestion("Is there a box or a worker on the field? B/W/X");
		
		String answer = MethodWriter.readFromStdin();
		
		
		switch (answer) {
			case "b" : 
				Box b = new Box();
				b.setField(this);
				this.setMovable(b);
				break;
			case "w" :
				Worker w2 = new Worker();
				w2.setField(this);
				this.setMovable(w2);
				break;
			default:
				this.setMovable(null);
		}
		
		Movable movable = getMovable();
		
		this.setMovable(w);
		w.place(this);
		getNeighbor(d.getOpposite()).remove(w);
		
	
		

		
		if (movable == null)
			MethodWriter.printOutRet("null");
		else
			MethodWriter.printOutRet(movable.toString());
		return movable;
	}
	
	

}
