package proto.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import proto.logic.*;

public class CommandHandler {
	
	boolean close = false;
	Game game = Game.getInstance();
	
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
			case "load_map":
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
				
			case "drop_special":
				if (args.length == 3) {
					int workerID = Integer.parseInt(args[1]);
					
					drop_special(workerID, args[2]);
				}
				
				break;
				
			case "ls_workers":
				if (args.length == 1)
					ls_workers();
				break;
				
			case "ls_boxes":
				if (args.length == 1)
					ls_boxes();
				break;
				
			case "ls_fields":
				if (args.length == 1)
					ls_fields();
				break;
				
			case "log":
				if (args.length == 2)
					log(args[1]);
				break;
				
			case "show_log":
				if (args.length == 1)
					show_log();
				break;
				
			case "log_off":
				if (args.length == 2)
					log_off(args[1]);
				break;
				
			case "drop_map":
				if (args.length == 1)
					drop_map();
				break;
				
			case "show_map":
				if (args.length == 1)
					show_map();
				break;
				
			case "run_test":
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
	}
	
	private void control(int workerID, Direction d) {
		System.out.println("control(" + workerID + ", " + d.toString() + ")");		
	}
	
	private void drop_special(int workerID, String type) {
		System.out.println("drop_special(" + workerID + ", " + type + ")");
	}
	
	private void ls_workers() {
		//System.out.println("ls_workers(" + ")");
		
		List<Worker> workers = game.getWorkers();
		
		for (Worker w : workers) {
			String[] pos = w.getField().toString().split(" ");
			
			System.out.println("ID:" + w.getId() + " X:" + pos[0] + " Y:" + pos[1] + " F:" + w.getForce());
		}
			
	}
	
	private void ls_boxes() {
		System.out.println("ls_boxes(" + ")");
	}
	
	private void ls_fields() {
		System.out.println("ls_fields(" + ")");
	}
	
	private void log(String fileName) {
		System.out.println("log(" + fileName + ")");
	}
	
	private void show_log() {
		System.out.println("show_log(" + ")");
	}
	
	private void log_off(String arg) {
		System.out.println("log(" + arg + ")");		
	}
	
	private void drop_map() {
		System.out.println("drop_map(" + ")");		
	}
	
	private void show_map() {
		System.out.println("show_map(" + ")");
	}
	
	private void run_test(String fileName) {
		System.out.println("log(" + fileName + ")");
	}
	
	private void exit() {
		System.out.println("exit(" + ")");
		close = true;
	}
	
	
}	
