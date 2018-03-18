package skeleton;

import skeleton.menu.*;
import skeleton.out.MethodWriter;

import java.io.IOException;

/**
 * A szekelton program maga, itt található a main függvény, ez irányítja a
 * futást. Tartalmazza a programhoz tartozó menüt, és azt irányítja.
 * 
 * @author jgoldfisch
 *
 */
public class Skeleton {

	/**
	 * A programhoz tartozó menü.
	 */
	private static Menu m = new Menu();

	/**
	 * A main függvény.
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		initMenu();

		boolean exit = false;

		m.initAllMenuItems();
		while (!exit) {
			m.drawMenu();

			System.out.print("\n\nChoose an option: ");

			String answer = MethodWriter.readFromStdin();
			System.out.print("\n\n");

			int n = 0;

			if (answer.matches("(\\d)+"))
				n = Integer.parseInt(answer);

			if (0 < n && n < 11) {
				m.chooseMenuItem(n);
				m.resetMenuItem(n);
				MethodWriter.resetCounters();

				System.out.print("\n\nPress enter to go back to the Menu... ");
				MethodWriter.readFromStdin();
				System.out.println();
			} else if (n == 11) {
				exit = true;
				System.out.println("Bye!");
			}
		}

	}

	/**
	 * A menüpontok elnevezi és beállítja azok leírását.
	 */
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
		m.setDescForItem(5, "5. Worker steps to another field, and the third field is a switch.");
		m.addMenuItem(6, "W_F_WL");
		m.setDescForItem(6, "6. Worker steps to another field, and the third field is Wall");
		m.addMenuItem(7, "W_F_E");
		m.setDescForItem(7, "7. Worker steps to another field, and the third field is an endzone field");
		m.addMenuItem(8, "W_F_F");
		m.setDescForItem(8,
				"8. Worker pushes a box to another field, and the third field is a regular field, the fourth is wall");
		m.addMenuItem(9, "W_F_F_F");
		m.setDescForItem(9, "9. Worker pushes a box to another field, and the fourth field is a regular field");
		m.addMenuItem(10, "W_F_F_WL");
		m.setDescForItem(10, "10. Worker steps to endzone field");
		m.addMenuItem(11, "Exit");
		m.setDescForItem(11, "11. Exit");
	}
}
