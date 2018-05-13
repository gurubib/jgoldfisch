package graf.gui.elements;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import graf.gui.Drawable;
import graf.logic.Worker;

/**
 * Worker modellbeli objektum kirajzolásáért felelős osztály.
 * 
 * @author jgoldfisch
 *
 */
public class G_Worker extends Drawable {

	/**
	 * A hozzátartozó modellbeli objektum
	 */
	Worker gameObject;
	
	/**
	 * Konstruktor, amely meghívja az ősosztály konstruktorát, és beállítja a modellbeli objektumot,
	 * valamint betölti a megfelelő képeket
	 * 
	 * @param panel A hozzátartozó JPanel
	 * @param worker A hozzátartozó modellbeli objektum
	 */
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
	
	/**
	 * A pozíció kiderítéséhez a space-ek mentén feldaraboljuk a string-et, majd az
	 * első elem lesz az X, a második pedig az Y koordináta.
	 */
	@Override
	public void draw(Graphics g) {
		if (gameObject.getField() != null) {
			String[] pos = gameObject.getField().toString().split(" ");
			
			SetPosition(Integer.parseInt(pos[0])-1, Integer.parseInt(pos[1])-1);
			super.draw(g);
		}
	}
}
