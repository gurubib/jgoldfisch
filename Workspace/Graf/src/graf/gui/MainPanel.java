package graf.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Egy alapvetÅ‘ JPanelt lÃ©trehozÃ³ osztÃ¡ly. Rendelkezik egy hÃ¡ttÃ©rrel Ã©s egy
 * hozzÃ¡ tartozÃ³ betÅ±tÃ­pussal.
 * 
 * @author jgoldfisch
 *
 */
public class MainPanel extends JPanel {

	/**
	 * A hÃ¡ttÃ©r kÃ©pe (mÃ©ret 1920 * 1080)
	 */
	private Image backgroundImage;

	/**
	 * A menÃ¼ betÅ±tÃ­pusa
	 */
	private Font font;
	
	/**
	 * Font méretét modosító szám, a képernyő nagyságától függően
	 */
	protected float sizeMod;
	
	/**
	 * Konstruktor egy alapvetÅ‘ panelt Ã©s betÃ¶lti a panelhez tartozÃ³ hÃ¡ttÃ©rkÃ©pet Ã©s
	 * betÅ±tÃ­pust.
	 */
	MainPanel() {
		try {
			ClassLoader loader = getClass().getClassLoader();
			backgroundImage = ImageIO.read(loader.getResource("menuBackground.png"));

			InputStream is = loader.getResourceAsStream("WOODCUTTER STORM.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is);

		} catch (Exception e) {
			// Amennyiben hiba a betÃ¶ltÃ©sben, lÃ©pjen ki.
			System.exit(ERROR);
		}
		
		// Beállítja a képernyő FHD-tól eltérésének arányát
		sizeMod = (float)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1920.0f;
	}

	/**
	 * Visszadja a menÃ¼ betÅ±tÃ­pusÃ¡t
	 */
	public Font getFont() {
		return font;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage.getScaledInstance(getWidth(), -1, Image.SCALE_SMOOTH), 0, 0, this);
	}
}
