package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class GamePanel extends MainPanel {

	/**
	 * A pálya képe
	 */
	private Image level;
	
	/**
	 * Valami
	 * TODO: Törölni is lehetne
	 */
	private Image overLay;

	/**
	 * A dinamikus elemeket tartalmazó tömb
	 */
	private ArrayList<Drawable> dynamicDrawables = new ArrayList<>();
	
	/**
	 * A staikus elemekt tartalmazó tömb
	 */
	private ArrayList<Drawable> staticDrawables = new ArrayList<>();

	/**
	 * Kirajzolja a statikus elmeket. Elég egyszer meghívni a játék elején.
	 */
	public void drawStaticView() {
		this.invalidate();
	}

	/**
	 * Kirajzolja a dinamikus elemeket
	 */
	public void drawDynamicView() {
		// TODO: Kitalálni, hogyan kéne csak azokat, egyáltalán lehet-e
		this.invalidate();
	}

	/**
	 * Betőlti a játék pályához tartozó textúrákat.
	 * 
	 * @param viewStyle
	 *            A pálya neve stringként. Lehet: <b>Jungle</b>, <b>Temple</b>,
	 *            <b>Dungeon</b>
	 */
	public void loadTextures(String viewStyle) {
		// TODO: A map inicializásása, ha itt kell
		// TODO: Minden játékobjecthez hozzáadni egy G_**-t

		String levelGround = "";

		switch (viewStyle) {
		case "Jungle":
			levelGround = "map" + 1 + ".png";
			break;
		case "Temple":
			levelGround = "map" + 2 + ".png";
			break;
		case "Dungeon":
			levelGround = "map" + 3 + ".png";
			break;
		}

		try {
			ClassLoader loader = getClass().getClassLoader();
			level = ImageIO.read(loader.getResource(levelGround));
		} catch (Exception e) {
			// Amennyiben hiba a betÃ¶ltÃ©sben, lÃ©pjen ki.
			System.exit(ERROR);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(level, 0, 0, getWidth(), getHeight(), this);

		for (Drawable stat : staticDrawables) {
			stat.draw(g);
		}

		for (Drawable dyn : dynamicDrawables) {
			dyn.draw(g);
		}
	}

}
