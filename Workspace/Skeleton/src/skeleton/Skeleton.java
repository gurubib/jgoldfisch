package skeleton;

import skeleton.menu.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import skeleton.game.logic.*;

public class Skeleton {
	
	private static Menu m = new Menu();

	public static void main(String[] args) throws NumberFormatException, IOException {
		initMenu();
		
		m.drawMenu();
		
		System.out.print("\n\n Choose an option: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		m.chooseMenuItem(n);
		
	}
	
	private static void initMenu() {
		m.addMenuItem(1, "WorkerToWall");
		m.setDescForItem(1, "1. Worker steps into wall");
		m.addMenuItem(2, "WorkerToHole");
		m.setDescForItem(2, "2. Worker steps into hole");
		m.addMenuItem(3, "W_F_H");
		m.setDescForItem(3, "3. Worker steps to another field, and the third field is a hole (without switch)");
		m.addMenuItem(4, "W_F_HS");
		m.setDescForItem(4, "4. Worker steps to another field, and the third field is a hole (with switch)");
		m.addMenuItem(5, "W_F_S");
		m.setDescForItem(5, "5. Worker steps to another Field, and the third field is a switch.");
		m.addMenuItem(6, "W_F_WL");
		m.setDescForItem(6, "6. Worker steps to another Field, and the third field is Wall");
		m.addMenuItem(7, "W_F_E");
		m.setDescForItem(7, "7. Worker steps to another Field, and the third field is an endzone field");
		m.addMenuItem(8, "W_F_F");
		m.setDescForItem(8, "8. Worker pushes a box to another Field, and the third field is a regular field");
		m.addMenuItem(9, "W_F_F_F");
		m.setDescForItem(9, "9. Worker pushes a box to another Field, and the fourth field is a regular field");
		m.addMenuItem(10, "W_F_F_WL");
		m.setDescForItem(10, "10. Worker pushes a box to another Field, and the fourth field is wall");
		m.addMenuItem(11, "Exit");
		m.setDescForItem(11, "11. Exit");
	}
}
