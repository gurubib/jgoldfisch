package graf.gui;

import java.awt.CardLayout;
import java.util.List;

import graf.logic.Direction;
import graf.logic.Game;
import graf.logic.Worker;

public class Controller {
	
	private MainFrame frame;
	private Game game = Game.getInstance();
	private KeyboardEventHandler keyboardHandler;
	
	public Controller() {
		game.setController(this);
	}
	
	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public KeyboardEventHandler getKeyboardHandler() {
		return keyboardHandler;
	}

	public void setKeyboardHandler(KeyboardEventHandler keyboardHandler) {
		this.keyboardHandler = keyboardHandler;
	}

	public void workerControlled(int workerID, Direction d) {
		List<Worker> workers = game.getWorkers();
		
		Worker controlledWorker = null;
		
		for (Worker w : workers) {
			if (w.getId() == workerID)
				controlledWorker = w;
		}
		
		if (controlledWorker != null) {
			controlledWorker.control(d);
			
			int score = controlledWorker.getPoints();
			frame.panel.setScore(workerID, score);
		}
		
		updateView();
	}
	
	public void oilDropped(int workerID) {
		
	}
	
	public void honeyDropped(int workerID) {
		
	}
	
	public void updateView() {
		frame.getPanel().drawDynamicView();
	}
	
	public void startGame(String mapFile) {
		game.startGame(mapFile);
		keyboardHandler.setGaming(true);
	}
	
	public void endGame(int score1, int score2) {
		frame.endPanel.setScores(score1, score2);
		CardLayout cd = (CardLayout) frame.endPanel.getParent().getLayout();
		cd.show(frame.endPanel.getParent(), "END");
	}
}
