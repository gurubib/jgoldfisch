package graf.gui.elements;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import graf.gui.Drawable;
import graf.logic.Box;

public class G_Box extends Drawable {

	Box gameObject;

	public G_Box(JPanel panel, Box box) {
		super(panel);		
		gameObject = box;
		
		try {
			ClassLoader loader = getClass().getClassLoader();
			Image texture = ImageIO.read(loader.getResource("Kek_Shard.png"));
			setTexture(texture);
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
		if (gameObject.getField() != null) {
			String[] pos = gameObject.getField().toString().split(" ");
			
			int x = Integer.parseInt(pos[0]) - 1;
			int y = Integer.parseInt(pos[1]) - 1;
			SetPosition(x, y);
			super.draw(g);
		}
		
	}

}
