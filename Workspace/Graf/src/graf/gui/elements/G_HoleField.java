package graf.gui.elements;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import graf.gui.Drawable;
import graf.logic.HoleField;

public class G_HoleField extends Drawable{

	HoleField gameObject;
	protected Image texture1, texture2, honey, oil;
	
	
	protected G_HoleField(JPanel panel) {
		super(panel);
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
			texture = texture1;
		} else {
			texture = texture2;
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
					texture = honey;
				}
				if(instance.equals("o")) {
					texture = oil;
				}
				super.draw(g);
			}
		}
	}
	
	
}
