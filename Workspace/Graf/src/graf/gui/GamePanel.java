package graf.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import graf.gui.elements.*;
import graf.logic.*;

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
	 * A staikus elemekt tartalmazó tömb
	 */
	private ArrayList<Drawable> staticDrawables = new ArrayList<>();

	private JLabel sc1, sc2;

	public GamePanel() {
		initComponents();
	}

	public void initComponents() {
		// ---------------------------------------------
		// PANELEK LÃ‰TREHOZÃ�SA
		// ---------------------------------------------
		// LegfelsÅ‘ panel, border layouttal
		// ---------------------------------------------
		setLayout(new BorderLayout());
		int border = Math.round(5 * sizeMod);
		setBorder(new EmptyBorder(border, border, border, border));

		// DÃ©li panel lÃ©trehozÃ¡sa, a vissza gombhoz
		// ---------------------------------------------
		JPanel northPanel = new JPanel(new BorderLayout());
		int borderNorth = Math.round(10 * sizeMod);
		northPanel.setBorder(new EmptyBorder(borderNorth, borderNorth, borderNorth, borderNorth));

		// ---------------------------------------------
		// FELIRATOK LÃ‰TREHOZÃ�SA
		// ---------------------------------------------
		// FÃ¶cÃ­m lÃ©trehozÃ¡sa, az, hogy ki a nyertes
		// ---------------------------------------------
		sc1 = new JLabel();
		sc1.setFont(getFont().deriveFont(32.0f * sizeMod));
		sc1.setForeground(new Color(197, 209, 227));
		sc1.setOpaque(false);
		sc1.setText("<html>Player1<br>0</html>");

		// Az elsÅ‘ jÃ¡tÃ©kos pontjait jelÃ¶lÅ‘ szÃ¶veg
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

	public void setScore(int workerID, int score) {
		if (workerID == 1) {
			sc1.setText("<html>Player1<br>" + score + "</html>");
		} else if (workerID == 2) {
			sc2.setText("<html><div align=right>Player2<br>" + score + "</div></html>");
		}
	}
}
