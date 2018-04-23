package proto.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import proto.logic.*;

/**
 * A bemeneti nyelvet kezelő osztály, a kiadott parancsokat értelmezi és dolgozza fel.
 * Meghívja a parancs végrehajtásához szükséges egyéb metódusokat,
 * továbbá tárolja a játék referenciáját (Singleton osztály). Itt fut igazából az üzenetkezelő loop.
 * 
 * @author jgoldfisch
 *
 */
public class CommandHandler {
	
	/**
	 * Az üzenetkezelő ciklus kilépési feltétele, az exit parancsnál válik igazzá.
	 */
	private boolean close = false;
	
	/**
	 * Az adott játék referenciája, a Singleton minta alapján kezelve.
	 */
	private Game game = Game.getInstance();
	
	/**
	 * A betöltött pályafájl neve.
	 */
	private String loadedMap;
	
	/**
	 * A használt logok, fájlnévvel azonosítva.
	 */
	private java.util.Map<String, PrintStream> logs = new HashMap<String, PrintStream>();
	
	
	/**
	 * Default konstruktor, a System.out-ot automatikusan, scrn azonosítóval eltárolja a logokba.
	 */
	public CommandHandler() {
		logs.put("scrn", System.out);
	}
	
	/**
	 * Az üzenetkezelő loop-ot futtató függvény, beolvas egy sornyi parancsot,
	 * majd darabokra szedi és továbbadja a parancskezelő függvénynek, ami folytatja a feldolgozást.
	 * 
	 * @throws IOException
	 */
	public void readInput() throws IOException {
		while (!close) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String wholeCommand = br.readLine();
			String[] parts = wholeCommand.split(" ");
		
			process(parts);
		}
	}
	
	/**
	 * A parancsok feldolgozásáért, értelmezéséért felelős függvény.
	 * Szétszedi az adott utasítást parancsra és argumentumokra,
	 * majd meghívja a megfelelő parancs elvégzését lebonyolító függvényt. 
	 * 
	 * @param args Az adott utasítás darabokra szedve
	 */
	private void process(String[] args) {
		String command = "";
		
		if (args.length != 0) {
			command = args[0];
			
		}

		switch (command) {
			case "load-map":
				if (args.length == 2)
					load_map(args[1]);
				break;
				
			case "control":
				if (args.length == 3) {
					int workerID = Integer.parseInt(args[1]);
					Direction d;
					
					switch (args[2]) {
						case "-u":
							d = Direction.UP;
							break;
							
						case "-r":
							d = Direction.RIGHT;
							break;
							
						case "-d":
							d = Direction.DOWN;
							break;
							
						case "-l":
							d = Direction.LEFT;
							break;
						default:
							d = Direction.RIGHT;
							break;						
					}
					
					control(workerID, d);				
				}
				break;
				
			case "drop-special":
				if (args.length == 3) {
					int workerID = Integer.parseInt(args[1]);
					
					drop_special(workerID, args[2]);
				}
				
				break;
				
			case "ls-workers":
				if (args.length == 1)
					ls_workers();
				break;
				
			case "ls-boxes":
				if (args.length == 1)
					ls_boxes();
				break;
				
			case "ls-fields":
				if (args.length == 1)
					ls_fields();
				break;
				
			case "log":
				if (args.length == 2)
					log(args[1]);
				break;
				
			case "show-log":
				if (args.length == 1)
					show_log();
				break;
				
			case "log-off":
				if (args.length == 2)
					log_off(args[1]);
				break;
				
			case "drop-map":
				if (args.length == 1)
					drop_map();
				break;
				
			case "show-map":
				if (args.length == 1)
					show_map();
				break;
				
			case "run-test":
				if (args.length == 2)
					run_test(args[1]);
				break;
				
			case "exit":
				if (args.length == 1)
					exit();
				break;
			}
	}
	
	/**
	 * A pálya betöltését elvégző parancs függvénye.
	 * 
	 * @param fileName A pályafájl neve
	 */
	private void load_map(String fileName) {
		game.startGame(fileName);
		loadedMap = fileName;
	}
	
	/**
	 * A munkás irányítását végző parancs függvénye.
	 * 
	 * @param workerID Az irányítandó munkás azonosítója.
	 * @param d A kívánt irány.
	 */
	private void control(int workerID, Direction d) {
		//System.out.println("control(" + workerID + ", " + d.toString() + ")");
		List<Worker> workers = game.getWorkers();
		
		Worker controlledWorker = null;
		
		for (Worker w : workers) {
			if (w.getId() == workerID)
				controlledWorker = w;
		}
		
		if (controlledWorker != null)
			controlledWorker.control(d);
	}
	
	/**
	 * Speciális elem lerakását végző parancs függvénye.
	 * 
	 * @param workerID A munkás azonosítója
	 * @param type A speciális elem típusa
	 */
	private void drop_special(int workerID, String type) {
		//System.out.println("drop_special(" + workerID + ", " + type + ")");
		
		List<Worker> workers = game.getWorkers();
		
		Worker controlledWorker = null;
		
		for (Worker w : workers) {
			if (w.getId() == workerID)
				controlledWorker = w;
		}
		
		if (controlledWorker != null) {
			if (type.equals("-h"))
				controlledWorker.placeHoney();
			else
				controlledWorker.placeOil();
		}
			
	}
	
	/**
	 * Munkások listázását elvégző parancs függvénye.
	 */
	private void ls_workers() {
		//System.out.println("ls_workers(" + ")");
		
		List<Worker> workers = game.getWorkers();
		
		List<String> lines = new ArrayList<String>(); 
		
		for (Worker w : workers) {
			String[] pos = w.getField().toString().split(" ");
			
			lines.add("ID:" + w.getId() + " X:" + pos[1] + " Y:" + pos[0] + " F:" + w.getForce());
		}
		
		writeToLogs(lines);
	}
	
	/**
	 * Dobozok listázását elvégző parancs függvénye.
	 */
	private void ls_boxes() {
		//System.out.println("ls_boxes(" + ")");
		
		List<Box> boxes = game.getBoxes();
		
		List<String> lines = new ArrayList<String>(); 
		
		for (Box b : boxes) {
			String[] pos = b.getField().toString().split(" ");
		
			boolean fix = false;
			List<Field> fixFields = game.getBoxRecorder().getFixFields();
		
			for (Field f : fixFields) {
				if (f == b.getField())
					fix = true;
			}			
			
			lines.add("ID:" + b.toString() + " X:" + pos[1] + " Y:" + pos[0] + " " + fix);
		}
		
		writeToLogs(lines);
	}
	
	/**
	 * Mezők listázását elvégző parancs függvénye.
	 */
	private void ls_fields() {
		//System.out.println("ls_fields(" + ")");
		
		List<Field> fields = game.getMap().getFields();
		
		List<String> lines = new ArrayList<String>();
		
		for (Field f : fields) {
			String[] pos = f.toString().split(" ");
			
			String slime = "x";
			String movable = "x";
			
			if (f.getSlime() != null)
				slime = f.getSlime().toString();
			
			if (f.getMovable() != null)
				movable = f.getMovable().toString();
			
			switch (pos[2]) {
			case "wall":
				lines.add(" X:" + pos[1] + " Y:" + pos[0] +  " " + pos[2]);
				break;
			case "simple":
				lines.add(" X:" + pos[1] + " Y:" + pos[0] +  " " + pos[2] + " " + slime + " " + movable);
				break;
			case "hole":
				lines.add( " X:" + pos[1] + " Y:" + pos[0] + " " + pos[2] + " " + pos[3] + " " + slime + " " + movable);
				break;
				
			case "switch":
				lines.add(" X:" + pos[1] + " Y:" + pos[0] +  " " + pos[2] + " " + pos[3] + " " + slime + " " + movable);
				break;
				
			case "endz":
				lines.add(" X:" + pos[1] + " Y:" + pos[0] +  " " + pos[2] + " " + slime + " " + movable);
				break;
			}
		}
		
		lines.sort((p1, p2) -> p1.compareTo(p2));
		
		writeToLogs(lines);
	}
	
	/**
	 * Logolást hozzáadó parancs függvénye.
	 * 
	 * @param fileName A kívánt log fájlneve
	 */
	private void log(String fileName) {
		//System.out.println("log(" + fileName + ")");
		
		Path logPath = Paths.get(System.getProperty("user.dir"), "logs", fileName);
		
		try {
			PrintStream newPrStr = new PrintStream(new FileOutputStream(logPath.toFile(), false));
			logs.put(fileName, newPrStr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * A meglévő logolási helyeket listázó parancs függvénye.
	 */
	private void show_log() {
		//System.out.println("show_log(" + ")");
		
		List<String> lines = new ArrayList<String>();
		
		for (Entry<String, PrintStream> e : logs.entrySet()) {
			lines.add(e.getKey());
		}
		
		writeToLogs(lines);
	}
	
	/**
	 * Adott logolás befejezését elvégző parancs függvénye.
	 * 
	 * @param arg A befejezendő parancs neve / fájlneve
	 */
	private void log_off(String arg) {
		//System.out.println("log(" + arg + ")");
		
		logs.remove(arg);
	}
	
	/**
	 * A betöltött pálya eldobásáért felelős parancs függvénye.
	 */
	private void drop_map() {
		//System.out.println("drop_map(" + ")");		
		game.setMap(null);
		loadedMap = "No map is loaded";
	}
	
	/**
	 * A betöltött pálya fájlnevét kiíró parancs függvénye.
	 */
	private void show_map() {
		//System.out.println("show_map(" + ")");
		List<String> lines = new ArrayList<String>();
		
		lines.add(loadedMap);
		
		writeToLogs(lines);
	}
	
	/**
	 * Előre megírt teszteset futtatásáért felelős parancs függvénye.
	 * 
	 * @param fileName Az előre megírt teszteset fájlneve
	 */
	private void run_test(String fileName) {
		//System.out.println("log(" + fileName + ")");
		
		Path testFilePath = Paths.get(System.getProperty("user.dir"), "tests", fileName);
		
		try {
			List<String> commands = Files.readAllLines(testFilePath);
			
			for (String s : commands) {
				String[] parts = s.split(" ");
				process(parts);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Kilépésért felelős parancs függvénye.
	 */
	private void exit() {

		//System.out.println("BYE <3 :*");
		close = true;
	}
	
	/**
	 * A logolásért felelős függvény, végigmegy az összes logon és kiírja a kapott sorokat.
	 * 
	 * @param lines A logolni kívánt sorok listája
	 */
	private void writeToLogs(List<String> lines) {
		
		for (PrintStream p : logs.values()) {
			for (String s : lines) { 
					p.println(s);
			}
		}
		
	}
	
}	
