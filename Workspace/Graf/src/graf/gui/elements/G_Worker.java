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
		
	public G_Worker(JPanel panel, Worker worker) {
		super(panel);
		gameObject = worker;
		
		try {
			ClassLoader loader = getClass().getClassLoader();
			Image texture = ImageIO.read(loader.getResource("Worker" + gameObject.getId() + ".png"));
			setTexture(texture);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	public void draw(Graphics g) {
		String[] pos = gameObject.getField().toString().split(" ");
		
		//System.out.println("X: " + pos[1] + " Y: " + pos[0]);
		
		SetPosition(Integer.parseInt(pos[0])-1, Integer.parseInt(pos[1])-1);
		super.draw(g);
	}
}
