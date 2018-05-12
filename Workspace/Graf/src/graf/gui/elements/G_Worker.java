package graf.gui.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import graf.gui.Drawable;
import graf.logic.Field;
import graf.logic.Game;
import graf.logic.Worker;

public class G_Worker extends Drawable {

	Worker gameObject;
		
	public G_Worker(JPanel panel) {
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
