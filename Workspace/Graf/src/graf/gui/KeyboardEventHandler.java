package graf.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.JTextComponent.KeyBinding;

import graf.logic.Direction;

public class KeyboardEventHandler implements KeyListener {

	Controller controller;
	boolean gaming = false;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public boolean isGaming() {
		return gaming;
	}

	public void setGaming(boolean gaming) {
		this.gaming = gaming;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (gaming) {
			switch(arg0.getKeyChar()) {
			case 'w': 
				controller.workerControlled(1, Direction.UP);
				break;
			case 'a': 
				controller.workerControlled(1, Direction.LEFT);
				break;
			case 's': 
				controller.workerControlled(1, Direction.DOWN);
				break;
			case 'd': 
				controller.workerControlled(1, Direction.RIGHT);
				break;
			case 'q':
				controller.honeyDropped(1);
				break;
			case 'e':
				controller.oilDropped(1);
				break;
			case 'k':
				controller.honeyDropped(2);
				break;
			case 'l':
				controller.oilDropped(2);
				break;
			}
			
			switch(arg0.getKeyCode()) {
			case KeyEvent.VK_UP: 
				controller.workerControlled(2, Direction.UP);
				break;
			case KeyEvent.VK_LEFT: 
				controller.workerControlled(2, Direction.LEFT);
				break;
			case KeyEvent.VK_DOWN: 
				controller.workerControlled(2, Direction.DOWN);
				break;
			case KeyEvent.VK_RIGHT: 
				controller.workerControlled(2, Direction.RIGHT);
				break;
			}
		}
		

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
