package skeleton;

import skeleton.menu.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import skeleton.game.logic.*;

public class Skeleton {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Menu m = new Menu();
		
		Box b = new Box();
		
		//System.out.println(b.toString());
		
		m.addMenuItem(1, "First");
		m.setDescForItem(1, "Worker -> Box -> Empty");
		
		initMenuItemMaps(m);
		
		m.drawMenu();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		m.chooseMenuItem(n);
		
	}
	
	private static void initMenuItemMaps(Menu m) {
		List<Field> fl1 = new ArrayList<Field>();
		
		Field f1 = new SimpleField();
		Field f2 = new SimpleField();
		Field f3 = new SimpleField();
		Field wf = new WallField();
		
		f1.setSkeletonName("f1");
		f2.setSkeletonName("f2");
		f3.setSkeletonName("f3");
		wf.setSkeletonName("wf");
		
		f1.setNeighbor(Direction.RIGHT, f2);
		f2.setNeighbor(Direction.LEFT, f1);
		f2.setNeighbor(Direction.RIGHT, f3);
		f3.setNeighbor(Direction.LEFT, f2);
		f3.setNeighbor(Direction.RIGHT, wf);
		wf.setNeighbor(Direction.LEFT, f3);
		
		fl1.add(f1);
		fl1.add(f2);
		fl1.add(f3);
		fl1.add(wf);
		
		List<Worker> wl1 = new ArrayList<Worker>();
		
		Worker w = new Worker();
		w.setField(f1);
		w.setSkeletonName("w");
		
		wl1.add(w);
		
		fl1.get(0).setMovable(w);
		
		m.getMenuItem(1).init(fl1, wl1);
	}

}
