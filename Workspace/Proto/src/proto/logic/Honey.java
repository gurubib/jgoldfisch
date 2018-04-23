package proto.logic;

import proto.out.MethodWriter;

/**
 * A mezőn elhelyezhető módosító, melynek a célja, 
 * hogy a surlódást növelje, emiatt egy munkás kevesebb dobozt tud eltolni.
 *  Munkás helyezheti el adott mezőre. Ha már található a mezőn Slime, 
 *  akkor felülírja azt.
 * 
 * @author jgoldfisch
 *
 */
public class Honey implements Slime{

	/**
	 * A kapott erőt módosítja, a viselkedésének megfelelően, 
	 * mivel nehezebbé teszi az eltolást így többel csökkenti az erőt,
	 * mint alapesetben csökkenne.
	 * 
	 * @param f a kapott erő
	 * @return a változott erő
	 */
	@Override
	public int interact(int f) {
		MethodWriter.printOutMethod("Honey.interact",Integer.toString(f));
		MethodWriter.printOutRet(Integer.toString(f-3));
		return f-3;
	}
	
	/**
	 * A kiíratásnál a mező típúsát kiíró toString() függvény felüldefiniálása.
	 */
	@Override
	public String toString() {
		return "h";
	}

}
