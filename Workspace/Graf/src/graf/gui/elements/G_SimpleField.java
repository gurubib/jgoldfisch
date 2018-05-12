package graf.gui.elements;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import graf.gui.Drawable;
import graf.logic.SimpleField;

public class G_SimpleField extends Drawable{

	SimpleField gameObject;
	Image honey, oil;
	
	protected G_SimpleField(JPanel panel) {
		super(panel);
		try {
			ClassLoader loader = getClass().getClassLoader();
			honey = ImageIO.read(loader.getResource("Mez_Placcs.png"));
			oil = ImageIO.read(loader.getResource("Olaj_Placcs.png"));
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
		
		if(gameObject.getSlime() != null) {
			String instance = gameObject.getSlime().toString();
			String position = gameObject.toString();
			// Ugyanaz elm., mintha split(" ")-t írnánk (regexp)
			String[] splitted = position.split("\\s+");
			
			int x = Integer.parseInt(splitted[0]) - 1;
			int y = Integer.parseInt(splitted[1]) - 1;
			SetPosition(x, y);
			
			if(instance.equals("h")) {
				texture = honey;
			}
			if(instance.equals("o")) {
				texture = oil;
			}
			super.draw(g);
		}
	}
	
}