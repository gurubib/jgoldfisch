package skeleton.game.logic;

/**
 * Enumer�ci� a p�ly�n tal�lhat� ir�nyokhoz.
 */
public enum Direction {
	/**
	 * A p�ly�n felfel� mutat� ir�ny
	 */
	UP,

	/**
	 * A p�ly�n lefel� mutat� ir�ny
	 */
	DOWN,

	/**
	 * A p�ly�n balra mutat� ir�ny
	 */
	LEFT,

	/**
	 * A p�ly�n jobbra mutat� ir�ny
	 */
	RIGHT;

	/**
	 * Az ellent�tes ir�nyt t�rol� v�ltoz�
	 */
	private Direction opposite;

	/**
	 * Az egyes ir�nyokhoz tart�z� ellent�tes ir�nyok
	 */
	static {
		UP.opposite = DOWN;
		DOWN.opposite = UP;
		LEFT.opposite = RIGHT;
		RIGHT.opposite = LEFT;
	}

	/**
	 * Visszadja az ellent�tes ir�nyt
	 * 
	 * @return ellent�tes ir�ny, Direction literal
	 */
	public Direction getOpposite() {
		return opposite;
	}

}
