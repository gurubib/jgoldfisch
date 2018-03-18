package skeleton.game.logic;

/**
 * Enumeráció a pályán található irányokhoz.
 */
public enum Direction {
	/**
	 * A pályán felfelé mutató irány
	 */
	UP,

	/**
	 * A pályán lefelé mutató irány
	 */
	DOWN,

	/**
	 * A pályán balra mutató irány
	 */
	LEFT,

	/**
	 * A pályán jobbra mutató irány
	 */
	RIGHT;

	/**
	 * Az ellentétes irányt tároló változó
	 */
	private Direction opposite;

	/**
	 * Az egyes irányokhoz tartozó ellentétes irányok
	 */
	static {
		UP.opposite = DOWN;
		DOWN.opposite = UP;
		LEFT.opposite = RIGHT;
		RIGHT.opposite = LEFT;
	}

	/**
	 * Visszadja az ellentétes irányt
	 * 
	 * @return ellentétes irány, Direction literal
	 */
	public Direction getOpposite() {
		return opposite;
	}

}
