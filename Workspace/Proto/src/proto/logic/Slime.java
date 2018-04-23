package proto.logic;

/**
 * A mezőn elhelyzehető módosítók interfésze, melynek a célja, 
 * hogy a surlódást megváltoztassa. Munkás helyezheti el adott mezőre. 
 * Ha már található a mezőn Slime, akkor felülírja azt.
 * 
 * @author jgoldfisch
 *
 */
public interface Slime {
	
	/**
	 * A kapott erőt módosítja, a viselkedés az implementáló osztályokban 
	 * kerül megfelelő megvalósításra.
	 * @param f a kapott erő
	 * @return a megváltoztatott erő
	 */
	int interact(int f);
}
