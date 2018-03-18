package skeleton.game.logic;

import skeleton.out.MethodWriter;

/**Célmező, amire a dobozokat (Box) juttatni kell. 
 * Amennyiben rálép egy doboz, akkor az mozdíthatatlanná válik ott.
 * A Field absztrakt ősosztály leszármazottja.
 * 
 * @author jgoldfisch
 *
 */
*
*
*/
public class EndField extends Field {
	/**
	*Fogadja a mezőre érkező Box-t, beállítja annak a mező referenciáját, aztán pedig kitörli a régi mezőjén lévő referenciáját (ahonnan jött, null-ra állítja). 
	*Végezetül pedig mozdíthatatlanná teszi a mezőre érkezett Box-t. Továbbá elindítja a pontszerzési folyamatot a megfelelő munkás számára.
	*
	*@param b
	*            A mezőre lépő Box referenciája.
	*@param d
	*            A mezőre lépő Box lépésének iránya.
	*/
	@Override
	public Movable boxEnters(Box b, Direction d) {
		MethodWriter.printOutMethod("EndField.boxEnters", b.toString() + ", " + d.toString());
		
		if (this.getMovable() == null) {
			//Megkérdezi a felhasználotól, hogy van-e már doboz a mezőn.
			MethodWriter.printOutQuestion("Is there a box on the field? B/X");

			String answer = MethodWriter.readFromStdin();
			//az adott válasz alapján a doboz létrehozása ha szükséges.
			switch (answer) {
			case "b":
				Box b2 = new Box();
				b2.setSkeletonName(MethodWriter.nameGenerator("b"));
				b2.setField(this);
				this.setMovable(b2);
				break;
			default:
				this.setMovable(null);
			}

		}
		//az érkező doboz áthelyezése az új mezőre
		b.place(this);
		
		Field backwardNeighbor = this.getNeighbor(d.getOpposite());
		Movable movable = this.getMovable();
		this.setMovable(b);
		backwardNeighbor.remove(b); // Az érkező Box referenciájának kitörlése arról a mezőről, ahonnan jött.
		//ha már van doboz az EndFielden akkor visszaadja az érkező doboz referenciáját,ezzel jelezve hogy nem lehet már ide lépni
		//mivel az a doboz már nem mozdítható
		if (movable != null) {
			MethodWriter.printOutRet(b.toString());
			return b;
		}
		
		backwardNeighbor.getMovable().scorePoint(d.getOpposite());

		MethodWriter.printOutRet("null");
		return null;
	}
	
	/**
	*Fogadja a mezőre érkező Worker-t, beállítja annak a mező referenciáját, 
	*aztán pedig kitörli a régi mezőjén lévő referenciáját (ahonnan jött, null-ra állítja).
	*
	* @param w
	*            A mezőre lépő Worker referenciája.
	* @param d
	*            A mezőre lépő Worker lépésének iránya.
	*/
	@Override
	public Movable workerEnters(Worker w, Direction d) {
		MethodWriter.printOutMethod("EndField.workerEnters", w.toString() + ", " + d.toString());
		
		if (this.getMovable() == null) {
			//Megkérdezi a felhasználotól, hogy van-e már doboz a mezőn.
			MethodWriter.printOutQuestion("Is there a box on the field? B/X");

			String answer = MethodWriter.readFromStdin();
			//az adott válasz alapján a doboz létrehozása ha szükséges.
			switch (answer) {
			case "b":
				Box b2 = new Box();
				b2.setSkeletonName(MethodWriter.nameGenerator("b"));
				b2.setField(this);
				this.setMovable(b2);
				break;
			default:
				this.setMovable(null);
			}

		}
		//az érkező doboz áthelyezése az új mezőre
		w.place(this);
		
		Field backwardNeighbor = this.getNeighbor(d.getOpposite());
		Movable movable = this.getMovable();
		this.setMovable(w);
		backwardNeighbor.remove(w); // Az érkező Worker referenciájának kitörlése arról a mezőről, ahonnan jött.
		//ha már van doboz az EndFielden akkor visszaadja az érkező doboz referenciáját,ezzel jelezve hogy nem lehet már ide lépni
		//mivel az a doboz már nem mozdítható
		if (movable != null) {
			MethodWriter.printOutRet(w.toString());
			return w;
		}
		this.setMovable(w);
		MethodWriter.printOutRet("null");
		return null;
	}

}
