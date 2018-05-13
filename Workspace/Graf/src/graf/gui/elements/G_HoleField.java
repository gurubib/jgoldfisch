package graf.gui.elements;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import graf.gui.Drawable;
import graf.logic.HoleField;

/**
 * HoleField modellbeli objektum kirajzolásáért felelős osztály.
 * 
 * @author jgoldfisch
 *
 */
public class G_HoleField extends Drawable{

	/**
	 * A hozzátartozó modellbeli objektum
	 */
	HoleField gameObject;
	
	/**
	 * A megfelelő képek
	 */
	protected Image texture1, texture2, honey, oil;
	
	/**
	 * Konstruktor, amely meghívja az ősosztály konstruktorát, és beállítja a modellbeli objektumot,
	 * valamint betölti a megfelelő képeket
	 * 
	 * @param panel A hozzátartozó JPanel
	 * @param holeField A hozzátartozó modellbeli objektum
	 */
	public G_HoleField(JPanel panel, HoleField holeField) {
		super(panel);
		gameObject = holeField;
		
		try {
			ClassLoader loader = getClass().getClassLoader();
			texture1 = ImageIO.read(loader.getResource("Light_Csapda_Lyuk.png"));
			texture2 = ImageIO.read(loader.getResource("Light_Csapoajto.png"));
			honey = ImageIO.read(loader.getResource("Mez_Placcs.png"));
			oil = ImageIO.read(loader.getResource("Olaj_Placcs.png"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * A pozíció kiderítéséhez a space-ek mentén feldaraboljuk a string-et, majd az
	 * első elem lesz az X, a második pedig az Y koordináta.
	 * A kapcsoló állapotának megfelelően beállítja a lyuk textúráját.
	 * Amennyiben nem tartozik hozzá kapcsoló, akkor a nyitott lyuk a textúrája.
	 */
	@Override
	public void draw(Graphics g) {
		boolean active = gameObject.getSwitchStatus();
		if(active) {
			setTexture(texture1);
		} else {
			setTexture(texture2);
		}
		String position = gameObject.toString();
		// Ugyanaz elm., mintha split(" ")-t írnánk (regexp)
		String[] splitted = position.split("\\s+");
		
		int x = Integer.parseInt(splitted[0]) - 1;
		int y = Integer.parseInt(splitted[1]) - 1;
		SetPosition(x, y);
		super.draw(g);
		
		if(!active) {
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
	
	
}
