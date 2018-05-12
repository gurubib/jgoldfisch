package graf.gui.elements;

import java.awt.Graphics;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import graf.gui.Drawable;
import graf.logic.Box;

public class G_Box extends Drawable {

	Box gameObject;

	protected G_Box(JPanel panel) {
		super(panel);
		try {
			ClassLoader loader = getClass().getClassLoader();
			texture = ImageIO.read(loader.getResource("Kek_Shard.png"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * A pozíció kiderítéséhez a space-ek mentén feldaraboljuk a string-et, majd az
	 * első elem lesz az X, a második pedig az Y koordináta.
	 */
	@Override
	public void draw(Graphics g) {
		String position = gameObject.toString();
		// Ugyanaz elm., mintha split(" ")-t írnánk (regexp)
		String[] splitted = position.split("\\s+");
		
		int x = Integer.parseInt(splitted[0]) - 1;
		int y = Integer.parseInt(splitted[1]) - 1;
		SetPosition(x, y);
		super.draw(g);
		
	}

}
