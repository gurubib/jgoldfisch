package graf.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import graf.out.MethodWriter;

/**
 * A pálya betöltéséért és tárolásáért felelős osztály.
 */
public class Map {

	private List<Field> fields = new ArrayList<Field>();

	/**
	 * A pálya fájlból való betöltésére szolgáló függvény.
	 * 
	 * @param mapFile
	 *            A pályát tartalmazó fájl
	 */
	public void loadMap(String mapFile) {
		// obj beolv
		List<Worker> workers = new ArrayList<Worker>();
		List<Box> boxes = new ArrayList<Box>();
		List<Field> walls = new ArrayList<Field>();
		
		SwitchField prevSwitch = null;
		HoleField prevHole = null;

		Path mapFilePath = Paths.get(System.getProperty("user.dir"), "maps", mapFile);

		try {
			List<String> lines = Files.readAllLines(mapFilePath);

			int width = lines.get(0).split(" ").length;
			int height = lines.size();

			String[][] mapString = new String[height][width];

			for (int i = 0; i < height; ++i) {
				String line = lines.get(i);
				mapString[i] = line.substring(1, line.length() - 1).split(" ");
			}

			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {

					String objString = mapString[i][j];

					switch (objString) {
					case "W":
						WallField wf = new WallField();
						wf.setSkeletonName(Integer.toString(j+1) + " " + Integer.toString(i+1) + " wall");
						fields.add(wf);
						walls.add(wf);

						if (i > 0) {
							int upperNeighborNum = (i - 1) * width + j;
							wf.setNeighbor(Direction.UP, fields.get(upperNeighborNum));
							fields.get(upperNeighborNum).setNeighbor(Direction.DOWN, wf);
						}

						if (j > 0) {
							wf.setNeighbor(Direction.LEFT, fields.get(fields.size() - 2));
							fields.get(fields.size() - 2).setNeighbor(Direction.RIGHT, wf);
						}

						break;

					case "X":
						SimpleField sf = new SimpleField();
						sf.setSkeletonName(Integer.toString(j+1) + " " + Integer.toString(i+1) + " simple");
						fields.add(sf);

						if (i > 0) {
							int upperNeighborNum = (i - 1) * width + j;
							sf.setNeighbor(Direction.UP, fields.get(upperNeighborNum));
							fields.get(upperNeighborNum).setNeighbor(Direction.DOWN, sf);
						}

						if (j > 0) {
							sf.setNeighbor(Direction.LEFT, fields.get(fields.size() - 2));
							fields.get(fields.size() - 2).setNeighbor(Direction.RIGHT, sf);
						}

						break;
					case "B":
						SimpleField sfb = new SimpleField();
						sfb.setSkeletonName(Integer.toString(j+1) + " " + Integer.toString(i+1) + " simple");
						fields.add(sfb);

						if (i > 0) {
							int upperNeighborNum = (i - 1) * width + j;
							sfb.setNeighbor(Direction.UP, fields.get(upperNeighborNum));
							fields.get(upperNeighborNum).setNeighbor(Direction.DOWN, sfb);
						}

						if (j > 0) {
							sfb.setNeighbor(Direction.LEFT, fields.get(fields.size() - 2));
							fields.get(fields.size() - 2).setNeighbor(Direction.RIGHT, sfb);
						}

						Box box = new Box();
						box.setField(sfb);
						box.setSkeletonName(MethodWriter.nameGenerator("b"));
						sfb.setMovable(box);
						boxes.add(box);

						break;
					case "S":
						SwitchField sw = new SwitchField();
						sw.setSkeletonName(Integer.toString(j+1) + " " + Integer.toString(i+1) + " switch");
						fields.add(sw);

						if (i > 0) {
							int upperNeighborNum = (i - 1) * width + j;
							sw.setNeighbor(Direction.UP, fields.get(upperNeighborNum));
							fields.get(upperNeighborNum).setNeighbor(Direction.DOWN, sw);
						}

						if (j > 0) {
							sw.setNeighbor(Direction.LEFT, fields.get(fields.size() - 2));
							fields.get(fields.size() - 2).setNeighbor(Direction.RIGHT, sw);
						}
						
						if (prevHole == null) {
							prevSwitch = sw;
						} else {
							sw.setHole(prevHole);
							prevHole.setSwitchField(sw);
							prevHole = null;
						}

						break;
					case "H":
						HoleField hf = new HoleField();
						hf.setSkeletonName(Integer.toString(j+1) + " " + Integer.toString(i+1) + " hole");
						fields.add(hf);

						if (i > 0) {
							int upperNeighborNum = (i - 1) * width + j;
							hf.setNeighbor(Direction.UP, fields.get(upperNeighborNum));
							fields.get(upperNeighborNum).setNeighbor(Direction.DOWN, hf);
						}

						if (j > 0) {
							hf.setNeighbor(Direction.LEFT, fields.get(fields.size() - 2));
							fields.get(fields.size() - 2).setNeighbor(Direction.RIGHT, hf);
						}
						
						if (prevSwitch == null) {
							prevHole = hf;
						} else {
							hf.setSwitchField(prevSwitch);
							prevSwitch.setHole(hf);
							prevSwitch = null;
						}
						
						break;
						
					case "E":
						EndField e = new EndField();
						e.setSkeletonName(Integer.toString(j+1) + " " + Integer.toString(i+1) + " endz");
						fields.add(e);

						if (i > 0) {
							int upperNeighborNum = (i - 1) * width + j;
							e.setNeighbor(Direction.UP, fields.get(upperNeighborNum));
							fields.get(upperNeighborNum).setNeighbor(Direction.DOWN, e);
						}

						if (j > 0) {
							e.setNeighbor(Direction.LEFT, fields.get(fields.size() - 2));
							fields.get(fields.size() - 2).setNeighbor(Direction.RIGHT, e);
						}

						break;
						
					default:
						int workerID = Integer.parseInt(objString);
						
						SimpleField sfw = new SimpleField();
						sfw.setSkeletonName(Integer.toString(j+1) + " " + Integer.toString(i+1) + " simple");
						fields.add(sfw);

						if (i > 0) {
							int upperNeighborNum = (i - 1) * width + j;
							sfw.setNeighbor(Direction.UP, fields.get(upperNeighborNum));
							fields.get(upperNeighborNum).setNeighbor(Direction.DOWN, sfw);
						}

						if (j > 0) {
							sfw.setNeighbor(Direction.LEFT, fields.get(fields.size() - 2));
							fields.get(fields.size() - 2).setNeighbor(Direction.RIGHT, sfw);
						}

						Worker worker = new Worker();
						worker.setField(sfw);
						worker.setSkeletonName("w" + Integer.toString(workerID));
						sfw.setMovable(worker);
						workers.add(worker);
						worker.setId(workerID);
						
						break;
					}
				}
			}

			/*
			Field testField = fields.get(19);

			System.out.println(" UP: " + testField.getNeighbor(Direction.UP) + " LEFT: "
					+ testField.getNeighbor(Direction.LEFT) + " DOWN: " + testField.getNeighbor(Direction.DOWN)
					+ " RIGHT: " + testField.getNeighbor(Direction.RIGHT) + " THIS " + testField.toString());
			*/

		} catch (IOException e) {
			System.out.println(e);
		}

		// Maganak
		Game.getInstance().setWorkers(workers);
		Game.getInstance().setBoxes(boxes);
		Game.getInstance().getBoxRecorder().setFixFields(walls);
	}

	/**
	 * Függvény arra az esetre, ha manuálisan akarjuk felépíteni a pályát.
	 * 
	 * @param fields
	 *            A felépítendő mező lista.
	 */
	public void manualLoad(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * Függvény egy mező cseréjére.
	 * 
	 * @param n
	 *            A listában az n-edik mező.
	 * @param f
	 *            Az új mező.
	 */
	public void confField(int n, Field f) {
		fields.set(n, f);
	}

	/**
	 * Getter függvény
	 * 
	 * @param n
	 *            A listában az n-edik mező.
	 * @return A kért mező.
	 */
	public Field getField(int n) {
		return fields.get(n);
	}

	/**
	 * A térkép alaphelyzetbe állítása, tehát minden mezőről töröljük az
	 * entitásokat, ha volt rajtuk.
	 */
	public void reset() {
		for (Field f : fields) {
			Movable m = f.getMovable();

			if (m != null) {
				f.setMovable(null);
			}
		}
	}

	/**
	 * Mezők lekérésére szolgáló függvény.
	 * @return
	 */
	public List<Field> getFields() {
		return fields;
	}
	/**
	 * A kapott paraméterre állítja a mezőket.
	 * @param fields
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
