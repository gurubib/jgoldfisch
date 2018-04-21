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

public class CommandHandler {
	
	private boolean close = false;
	private Game game = Game.getInstance();
	private String loadedMap;
	
	private java.util.Map<String, PrintStream> logs = new HashMap<String, PrintStream>();
	
	public CommandHandler() {
		logs.put("scrn", System.out);
	}
	
	public void readInput() throws IOException {
		while (!close) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String wholeCommand = br.readLine();
			String[] parts = wholeCommand.split(" ");
		
			process(parts);
		}
	}
	
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
	
	private void load_map(String fileName) {
		game.startGame(fileName);
		loadedMap = fileName;
	}
	
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
	
	private void ls_workers() {
		//System.out.println("ls_workers(" + ")");
		
		List<Worker> workers = game.getWorkers();
		
		List<String> lines = new ArrayList<String>(); 
		
		for (Worker w : workers) {
			String[] pos = w.getField().toString().split(" ");
			
			lines.add("ID:" + w.getId() + " X:" + pos[0] + " Y:" + pos[1] + " F:" + w.getForce());
		}
		
		writeToLogs(lines);
	}
	
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
			
			lines.add("ID:" + b.toString() + " X:" + pos[0] + " Y:" + pos[1] + " " + fix);
		}
		
		writeToLogs(lines);
	}
	
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
				lines.add("X:" + pos[0] + " Y:" + pos[1] + " " + pos[2]);
				break;
			case "simple":
				lines.add("X:" + pos[0] + " Y:" + pos[1] + " " + pos[2] + " " + slime + " " + movable);
				break;
			case "hole":
				lines.add("X:" + pos[0] + " Y:" + pos[1] + " " + pos[2] + " " + pos[3] + " " + slime + " " + movable);
				break;
				
			case "switch":
				lines.add("X:" + pos[0] + " Y:" + pos[1] + " " + pos[2] + " " + pos[3] + " " + slime + " " + movable);
				break;
				
			case "endz":
				lines.add("X:" + pos[0] + " Y:" + pos[1] + " " + pos[2] + " " + slime + " " + movable);
				break;
			}
		}
		
		lines.sort((p1, p2) -> p1.compareTo(p2));
		
		writeToLogs(lines);
	}
	
	private void log(String fileName) {
		//System.out.println("log(" + fileName + ")");
		
		try {
			PrintStream newPrStr = new PrintStream(new FileOutputStream(fileName, true));
			logs.put(fileName, newPrStr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void show_log() {
		//System.out.println("show_log(" + ")");
		
		List<String> lines = new ArrayList<String>();
		
		for (Entry<String, PrintStream> e : logs.entrySet()) {
			lines.add(e.getKey());
		}
		
		writeToLogs(lines);
	}
	
	private void log_off(String arg) {
		//System.out.println("log(" + arg + ")");
		
		logs.remove(arg);
	}
	
	private void drop_map() {
		//System.out.println("drop_map(" + ")");		
		game.setMap(null);
		loadedMap = "No map is loaded";
	}
	
	private void show_map() {
		//System.out.println("show_map(" + ")");
		List<String> lines = new ArrayList<String>();
		
		lines.add(loadedMap);
		
		writeToLogs(lines);
	}
	
	private void run_test(String fileName) {
		//System.out.println("log(" + fileName + ")");
		
		Path testFilePath = Paths.get(System.getProperty("user.dir"), fileName);
		
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
	
	private void exit() {

		System.out.println("BYE <3 :*");
		close = true;
	}
	
	private void writeToLogs(List<String> lines) {
		
		for (PrintStream p : logs.values()) {
			for (String s : lines) { 
					p.println(s);
			}
		}
		
	}
	
}	
