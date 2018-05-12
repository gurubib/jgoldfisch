<<<<<<< HEAD
package graf.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Absztakt Å‘sosztÃ¡lya minden a pÃ¡lyÃ¡ra rajzolhatÃ³ jÃ¡tÃ©kelemnek. Tartalmaz egy
 * alapvetÅ‘ textÃºrÃ¡t Ã©s a panelt, melyre a kirajzolÃ¡s tÃ¶rtÃ©nni fog.
 * 
 * @author jgoldfisch
 *
 */
abstract public class Drawable {
	/**
	 * A jÃ¡tÃ©kelemhez tartozÃ³ textÃºra
	 */
	private Image texture;

	/**
	 * A panel, melyre a kirajzolÃ¡st vÃ©gezzÃ¼k
	 */
	protected JPanel panel;

	/**
	 * Az kirajzolÃ¡s x Ã©s y pozÃ­ciÃ³ja
	 */
	protected int posX = -1, posY = -1;

	/**
	 * A kÃ©pernyÅ‘ Ã¡tmÃ©retezÃ©se esetÃ©n a vÃ¡ltozÃ¡s nagysÃ¡ga (FHD-hoz kÃ©pest)
	 */
	protected float sizeMod;

	/**
	 * Kostruktor egy kirajzolhatÃ³ jÃ¡tÃ©kelemhez
	 * 
	 * @param panel
	 *            referencia a panelra, melyre a rajzolÃ¡st szeretnÃ©nk vÃ©gezni
	 */
	protected Drawable(JPanel panel) {
		this.panel = panel;
		sizeMod = (float) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1920.0f;
	}

	/**
	 * Kirajzolja a textÃºrÃ¡t egy megadott koordinÃ¡tÃ¡ba
	 * 
	 * @param g
	 *            referencia egy Graphics osztÃ¡lyra
	 */
	public void draw(Graphics g) {
		if (posX < 0 || posY < 0) {
			System.err.println("Position not declared in " + this.getClass());
			return;
		}

		g.drawImage(texture, posX, posY, panel);
	}
		
	public void setTexture(Image image) {		
		texture = image.getScaledInstance(Math.round(image.getWidth(panel) * sizeMod), -1, Image.SCALE_SMOOTH);
	}

	/**
	 * BeÃ¡llÃ­tja, hogy hova kell kirajzolni a pÃ¡lyÃ¡n a kÃ©pet.
	 * 
	 * @param x
	 *            a sor szÃ¡ma (0-val kezdÅ‘dÅ‘en)
	 * @param y
	 *            az oszlop szÃ¡ma (0-val kezdÅ‘dÅ‘en)
	 */
	public void SetPosition(int x, int y) {
		posX = Math.round((x * 64 + 32) * sizeMod);
		posY = Math.round((y * 64 - 4) * sizeMod);
	}
}
=======
package graf.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Absztakt Å‘sosztÃ¡lya minden a pÃ¡lyÃ¡ra rajzolhatÃ³ jÃ¡tÃ©kelemnek. Tartalmaz egy
 * alapvetÅ‘ textÃºrÃ¡t Ã©s a panelt, melyre a kirajzolÃ¡s tÃ¶rtÃ©nni fog.
 * 
 * @author jgoldfisch
 *
 */
abstract public class Drawable {
	/**
	 * A jÃ¡tÃ©kelemhez tartozÃ³ textÃºra
	 */
	private Image texture;

	/**
	 * A panel, melyre a kirajzolÃ¡st vÃ©gezzÃ¼k
	 */
	protected JPanel panel;

	/**
	 * Az kirajzolÃ¡s x Ã©s y pozÃ­ciÃ³ja
	 */
	protected int posX = -1, posY = -1;

	/**
	 * A kÃ©pernyÅ‘ Ã¡tmÃ©retezÃ©se esetÃ©n a vÃ¡ltozÃ¡s nagysÃ¡ga (FHD-hoz kÃ©pest)
	 */
	protected float sizeMod;

	/**
	 * Kostruktor egy kirajzolhatÃ³ jÃ¡tÃ©kelemhez
	 * 
	 * @param panel
	 *            referencia a panelra, melyre a rajzolÃ¡st szeretnÃ©nk vÃ©gezni
	 */
	protected Drawable(JPanel panel) {
		this.panel = panel;
		sizeMod = (float) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1920.0f;
	}

	/**
	 * Kirajzolja a textÃºrÃ¡t egy megadott koordinÃ¡tÃ¡ba
	 * 
	 * @param g
	 *            referencia egy Graphics osztÃ¡lyra
	 */
	public void draw(Graphics g) {
		if (posX < 0 || posY < 0) {
			System.err.println("Position not declared in " + this.getClass());
			return;
		}

		g.drawImage(texture, posX, posY, panel);
	}
		
	public void setTexture(Image image) {		
		texture = image.getScaledInstance(Math.round(image.getWidth(panel) * sizeMod), -1, Image.SCALE_SMOOTH);
	}

	/**
	 * BeÃ¡llÃ­tja, hogy hova kell kirajzolni a pÃ¡lyÃ¡n a kÃ©pet.
	 * 
	 * @param x
	 *            a sor szÃ¡ma (0-val kezdÅ‘dÅ‘en)
	 * @param y
	 *            az oszlop szÃ¡ma (0-val kezdÅ‘dÅ‘en)
	 */
	public void SetPosition(int x, int y) {
		posX = Math.round((x * 64 + 32) * sizeMod);
		posY = Math.round((y * 64 - 4) * sizeMod);
	}
}
>>>>>>> 2b7fced5aa7633115c83d231ffcc1adc58cf9de9
