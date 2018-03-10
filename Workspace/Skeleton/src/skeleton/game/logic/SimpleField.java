package skeleton.game.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleField extends Field {

	@Override
	public Movable boxEnters(Box b, Direction d) {
		System.out.println("SimpleField.boxEnters(w, d)");
		
		b.place(this);
		getNeighbor(d.getOpposite()).remove();
		
		Movable movable = getMovable();
		
		if (movable != null)
			return movable;
		else
			return null;
	}

	@Override
	public Movable workerEnters(Worker w, Direction d) {
		System.out.println("SimpleField.workerEnters(w, d)");
		
		w.place(this);
		getNeighbor(d.getOpposite()).remove();
		
		System.out.println("~ Is there a box on this Field? (y/n)");
		
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
		
		if (movable != null)
			return movable;
		else
			return null;
	}
	
	@Override
	public void boxArrived(Box b) {
		System.out.println("SimpleField.boxArrived(b)");
		
		
	}
	
	@Override
	public void workerArrived(Worker w) {
		System.out.println("SimpleField.workerArrived(b)");
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
