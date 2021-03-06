package graf.gui.elements;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import graf.gui.Drawable;
import graf.logic.EndField;

/**
 * EndField modellbeli objektum kirajzolásáért felelős osztály.
 * 
 * @author jgoldfisch
 *
 */
public class G_EndField extends Drawable {

	/**
	 * A hozzátartozó modellbeli objektum
	 */
	EndField gameObject;
	
	/**
	 * A méz képe
	 */
	Image honey;

	/**
	 * Az olaj képe
	 */
	Image oil;

	/**
	 * Konstruktor, amely meghívja az ősosztály konstruktorát, és beállítja a modellbeli objektumot,
	 * valamint betölti a megfelelő képeket
	 * 
	 * @param panel A hozzátartozó JPanel
	 * @param endField A hozzátartozó modellbeli objektum
	 */
	public G_EndField(JPanel panel, EndField endField) {
		super(panel);	
		gameObject = endField;
		
		try {
			ClassLoader loader = getClass().getClassLoader();
			Image texture = ImageIO.read(loader.getResource("Kek_Shard_Hely.png"));
			setTexture(texture);
			
			honey = ImageIO.read(loader.getResource("Mez_Placcs.png"));
			oil = ImageIO.read(loader.getResource("Olaj_Placcs.png"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
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
		if(gameObject.getSlime() != null) {
			String instance = gameObject.getSlime().toString();
			if(instance.equals("h")) {
				setTexture(honey);
			}
			if(instance.equals("o")) {
				setTexture(oil);
			}
			super.draw(g);
		}
	}

}
