package skeleton.game.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import skeleton.out.MethodWriter;

public class SimpleField extends Field {

	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("Moveable.boxEnters", "w, d");
		
		b.place(this);
		getNeighbor(d.getOpposite()).remove();
		
		Movable movable = getMovable();
		
		if (movable != null) {
			MethodWriter.printOutRet("moveable");
			return movable;
		} else {
			MethodWriter.printOutRet("null");
			return null;
		}
	}

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("SimpleField.workerEnters", "w, d");
		
		w.place(this);
		getNeighbor(d.getOpposite()).remove();
		
		MethodWriter.printOutQuestion("Is there a box on this Field? (y/n)");
		
		String answer = readFromStdin();
		
		
		switch (answer) {
			case "y" : 
				Box b = new Box();
				b.setField(this);
				this.setMovable(b);
				break;
			default:
				this.setMovable(null);
		}
		
		Movable movable = getMovable();
		
		if (movable != null) {
			MethodWriter.printOutRet("movable");
			return movable;
		} else {
			MethodWriter.printOutRet("null");
			return null;
		}
	}
	
	@Override
	public void boxArrived(Box b) {
		MethodWriter.printOutMethod("SimpleField.boxArrived", "b");
		
		
		MethodWriter.printOutRet("");
	}
	
	@Override
	public void workerArrived(Worker w) {
		MethodWriter.printOutMethod("SimpleField.workerArrived", "b");
		
		MethodWriter.printOutRet("");
	}
	
	private String readFromStdin() {
		String answer = null;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			answer = br.readLine();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return answer;
	}

}
