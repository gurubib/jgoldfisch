package skeleton.game.logic;

public enum Direction {
	UP,
	RIGHT,
	DOWN,
	LEFT;
	
	public Direction getOpposite() {
		switch(this) {
			case UP:
				return Direction.DOWN;
			case RIGHT:
				return Direction.LEFT;
			case DOWN:
				return Direction.UP;
			case LEFT:
				return Direction.RIGHT;
			default:
				return Direction.RIGHT;
		}
	}
}
