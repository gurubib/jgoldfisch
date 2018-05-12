package graf.gui.elements;

import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import graf.gui.Drawable;
import graf.logic.Box;

public class G_Box extends Drawable {
	Box gameObject;
	
	public G_Box(JPanel panel) {
		super(panel);
		try {
			ClassLoader loader = getClass().getClassLoader();
			texture = ImageIO.read(loader.getResource("Worker.png"));
		} catch (Exception e) {
			// Amennyiben hiba a betöltésben, lépjen ki.
			System.exit(1);
		}	
	}
	
	@Override
	public void draw(Graphics g) {
		SetPosition(5, 5);
		super.draw(g);
	}
}
