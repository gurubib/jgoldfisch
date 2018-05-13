package graf.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import graf.gui.elements.*;
import graf.logic.*;

/**
 * Az MVC minta view részének egy eleme, maga a játékmenetet megjelenítő panel.
 * Tartalmazza a modellben szereplő entitások kirajzolandó változatait. Ezeket a modell alapján jeleníti meg.
 * JPanel leszármazott, ezzel könnyítve a megvalósítást. Tárolja a pályához tartozó textúrákat is.
 * 
 * @author jgoldfisch
 *
 */
public class GamePanel extends MainPanel {

	/**
	 * A pálya képe
	 */
	private Image level;

	/**
	 * Valami TODO: Törölni is lehetne
	 */
	private Image overLay;

	/**
	 * A dinamikus elemeket tartalmazó tömb
	 */
	private ArrayList<Drawable> dynamicDrawables = new ArrayList<>();

	/**
	 * A staikus elemeket tartalmazó tömb
	 */
	private ArrayList<Drawable> staticDrawables = new ArrayList<>();

	/**
	 * Játékosok neveinek és kiírására szolgáló JLabel
	 */
	private JLabel sc1, sc2;

	/**
	 * Konstruktor, meghívja az inicializáló függvényt
	 */
	public GamePanel() {
		initComponents();
	}

	/**
	 * A Swing komponenseket inicializáló függvény
	 */
	public void initComponents() {
		// ---------------------------------------------
		// PANELEK LÉTREHOZÁSA
		// ---------------------------------------------
		// Legfelső panel, border layouttal
		// ---------------------------------------------
		setLayout(new BorderLayout());
		int border = Math.round(5 * sizeMod);
		setBorder(new EmptyBorder(border, border, border, border));

		// Déli panel létrehozása, a vissza gombhoz
		// ---------------------------------------------
		JPanel northPanel = new JPanel(new BorderLayout());
		int borderNorth = Math.round(10 * sizeMod);
		northPanel.setBorder(new EmptyBorder(borderNorth, borderNorth, borderNorth, borderNorth));

		// ---------------------------------------------
		// FELIRATOK LÉTREHOZÁSA
		// ---------------------------------------------
		// Főcím létrehozása, az, hogy ki a nyertes
		// ---------------------------------------------
		sc1 = new JLabel();
		sc1.setFont(getFont().deriveFont(32.0f * sizeMod));
		sc1.setForeground(new Color(197, 209, 227));
		sc1.setOpaque(false);
		sc1.setText("<html>Player1<br>0</html>");

		// Az első játékos pontjait jelölő szöveg
		// ---------------------------------------------
		sc2 = new JLabel();
		sc2.setFont(getFont().deriveFont(32.0f * sizeMod));
		sc2.setForeground(new Color(197, 209, 227));
		sc2.setOpaque(false);
		sc2.setText("<html><div align=right>Player2<br>0</div></html>");
		
		northPanel.add(sc1, BorderLayout.WEST);
		northPanel.add(sc2, BorderLayout.EAST);
		northPanel.setOpaque(false);
		
		add(northPanel, BorderLayout.NORTH);
	}

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
	 * Kitörli a térképen található elemeket
	 */
	public void clearMap() {
		sc1.setText("<html>Player1<br>0</html>");
		sc2.setText("<html><div align=right>Player2<br>0</div></html>");
		
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
			levelGround = "Map" + 1 + ".png";
			break;
		case "Temple":
			levelGround = "Map" + 2 + ".png";
			break;
		case "Dungeon":
			levelGround = "Map" + 3 + ".png";
			break;
		}
		
		for (Field f : Game.getInstance().getMap().getFields()) {
			String type = f.toString().split(" ")[2];

			switch (type) {
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
			case "wall":
			default:
				break;
			}
		}
		
		try {
			ClassLoader loader = getClass().getClassLoader();
			level = ImageIO.read(loader.getResource(levelGround));
		} catch (Exception e) {
			// Amennyiben hiba a betöltésben, lépjen ki.
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Felüldefiniált rajzoló függvény
	 */
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

	/**
	 * G_Worker hozzáadását elvégző függvény
	 * 
	 * @param worker A G_Worker-hez tartozó modellbeli objektum
	 */
	public void addG_Worker(Worker worker) {
		G_Worker gWorker = new G_Worker(this, worker);
		dynamicDrawables.add(gWorker);
	}

	/**
	 * G_Box hozzáadását elvégző függvény
	 * 
	 * @param box A G_Box-hoz tatozó modellbeli objektum
	 */
	public void addG_Box(Box box) {
		G_Box gBox = new G_Box(this, box);
		dynamicDrawables.add(gBox);
	}

	/**
	 * Adott munkás pontjának a kiíratását elvégző függvény, a JLabel-ekre ír
	 * 
	 * @param workerID Az adott munkás azonosítója
	 * @param score	A kiírandó pontszám
	 */
	public void setScore(int workerID, int score) {
		if (workerID == 1) {
			sc1.setText("<html>Player1<br>" + score + "</html>");
		} else if (workerID == 2) {
			sc2.setText("<html><div align=right>Player2<br>" + score + "</div></html>");
		}
	}
}
