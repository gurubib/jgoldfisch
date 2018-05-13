package graf.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * Absztakt ősosztálya minden a pályára rajzolható játékelemnek. Tartalmaz egy
 * alapvető textúrát és a panelt, melyre a kirajzolás történni fog.
 * 
 * @author jgoldfisch
 *
 */
abstract public class Drawable {
	/**
	 * A játékelemhez tartozó textúra
	 */
	private Image texture;

	/**
	 * A panel, melyre a kirajzolást végezzÃ¼k
	 */
	protected JPanel panel;

	/**
	 * Az kirajzolás x és y pozíciója
	 */
	protected int posX = -1, posY = -1;

	/**
	 * A képernyő átméretezése esetén a változás nagysága (FHD-hoz képest)
	 */
	protected float sizeMod;

	/**
	 * Kostruktor egy kirajzolható játékelemhez
	 * 
	 * @param panel
	 *            referencia a panelra, melyre a rajzolást szeretnénk végezni
	 */
	protected Drawable(JPanel panel) {
		this.panel = panel;
		sizeMod = (float) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1920.0f;
	}

	/**
	 * Kirajzolja a textúrát egy megadott koordinátába
	 * 
	 * @param g
	 *            referencia egy Graphics osztályra
	 */
	public void draw(Graphics g) {
		if (posX < 0 || posY < 0) {
			System.err.println("Position not declared in " + this.getClass());
			return;
		}

		g.drawImage(texture, posX, posY, panel);
	}
	
	/**
	 * Textúra beállításása (setter)
	 * 
	 * @param image A beállítandó kép
	 */
	public void setTexture(Image image) {		
		texture = image.getScaledInstance(Math.round(image.getWidth(panel) * sizeMod), -1, Image.SCALE_SMOOTH);
	}

	/**
	 * Beállítja, hogy hova kell kirajzolni a pályán a képet.
	 * 
	 * @param x
	 *            a sor száma (0-val kezdődően)
	 * @param y
	 *            az oszlop száma (0-val kezdődően)
	 */
	public void SetPosition(int x, int y) {
		posX = Math.round((x * 64 + 32) * sizeMod);
		posY = Math.round((y * 64 - 4) * sizeMod);
	}
}
