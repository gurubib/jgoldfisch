package proto.logic;

import proto.out.MethodWriter;
/**
 * A mezőn elhelyzehető módosító, melynek a célja, 
 * hogy a surlódást csökkentse, emiatt egy munkás több dobozt tud eltolni.
 *  Munkás helyezheti el adott mezőre. Ha már található a mezőn Slime,
 *  akkor felülírja azt.
 *  
 * @author jgoldfisch
 *
 */
public class Oil implements Slime{

	/**
	 * A kapott erőt módosítja, a viselkedésének megfelelően, 
	 * mivel könnyebbé teszi az eltolást így kevesebbel csökkenti 
	 * az erőt mint alapesetben csökkenne.
	 * 
	 * @param f a kapott erő
	 * @return a változott erő
	 */
	@Override
	public int interact(int f) {
		MethodWriter.printOutMethod("Oil.interact",Integer.toString(f));
		MethodWriter.printOutRet(Integer.toString(f-1));
		return f-1;
	}
	
	/**
	 * Az objektum azonosítóját kiírő toString() függvény felüldefiniálása.
	 */
	@Override
	public String toString() {
		return "o";
	}

}
