package graf.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Egy alapvető JPanelt létrehozó osztály. Rendelkezik egy háttérrel és
 * egy hozzá tartozó betűtípussal.
 * 
 * @author jgoldfisch
 *
 */
public class MainPanel extends JPanel {

	/**
	 * A háttér képe (méret 1920 * 1080)
	 */
	private Image backgroundImage;

	/**
	 * A menü betűtípusa
	 */
	private Font font;

	/**
	 * Font méretét modosító szám, a képernyő nagyságától függően
	 */
	protected float sizeMod;

	/**
	 * Konstruktor egy alapvető panelt és betölti a panelhez tartozó
	 * háttérképet és betűtípust.
	 */
	MainPanel() {
		try {
			ClassLoader loader = getClass().getClassLoader();
			backgroundImage = ImageIO.read(loader.getResource("menuBackground.png"));
			
			InputStream is = loader.getResourceAsStream("WOODCUTTER STORM.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is);

		} catch (Exception e) {
			// Amennyiben hiba a betöltésben, lépjen ki.
			System.exit(ERROR);
		}

		// Beállítja a képernyő FHD-tól eltérésének arányát
		sizeMod = (float) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1920.0f;
	}

	/**
	 * Visszadja a menü betűtípusát
	 */
	public Font getFont() {
		return font;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, this);
	}
}
