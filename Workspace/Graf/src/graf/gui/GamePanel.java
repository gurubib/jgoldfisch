package graf.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import graf.gui.elements.*;
import graf.logic.*;

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
		this.repaint();
	}

	/**
	 * Kirajzolja a dinamikus elemeket
	 */
	public void drawDynamicView() {
		// TODO: Kitalálni, hogyan kéne csak azokat, egyáltalán lehet-e
		this.repaint();
	}
	
	/**
	 * Kit�rli a t�rk�pen tal�lhat� elemeket
	 */
	public void clearMap() {
		dynamicDrawables.clear();
		staticDrawables.clear();
	}

	/**
	 * Betőlti a játék pályához tartozó textúrákat.
	 * 
	 * @param viewStyle
	 *            A pálya neve stringként. Lehet: <b>Jungle</b>, <b>Temple</b>,
	 *            <b>Dungeon</b>
	 */
	public void loadTextures(String viewStyle) {
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
		
		for(Field f : Game.getInstance().getMap().getFields()) {
			String type = f.toString().split(" ")[2];
			
			switch(type) {
			case "simple":
				staticDrawables.add(new G_SimpleField(this, (SimpleField) f));
				break;
			case "endz":
				staticDrawables.add(new G_EndField(this, (EndField) f));
				break;
			case "hole":
				staticDrawables.add(new G_HoleField(this, (HoleField) f));
				break;
			case "switch":
				staticDrawables.add(new G_SwitchField(this, (SwitchField) f));
				break;
			case "wall": default:
				break;
			}
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

	public void addG_Worker(Worker worker) {
		G_Worker gWorker = new G_Worker(this, worker);
		dynamicDrawables.add(gWorker);
	}
	
	public void addG_Box(Box box) {
		G_Box gBox = new G_Box(this, box);
		dynamicDrawables.add(gBox);
	}
}
