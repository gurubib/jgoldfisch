package graf.logic;

import graf.out.MethodWriter;

/**Célmező, amire a dobozokat (Box) juttatni kell. 
 * Amennyiben rálép egy doboz, akkor az mozdíthatatlanná válik ott.
 * A Field absztrakt ősosztály leszármazottja.
 * 
 * @author jgoldfisch
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
		
		//az érkező doboz áthelyezése az új mezőre
		b.place(this);
		
		Field backwardNeighbor = this.getNeighbor(d.getOpposite());
		Movable movable = this.getMovable();
		backwardNeighbor.remove(b); // Az érkező Box referenciájának kitörlése arról a mezőről, ahonnan jött.
		//ha már van doboz az EndFielden akkor visszaadja az érkező doboz referenciáját,ezzel jelezve hogy nem lehet már ide lépni
		//mivel az a doboz már nem mozdítható
		if (movable != null) {
			MethodWriter.printOutRet(b.toString());
			return b;
		}
		this.setMovable(b);
		backwardNeighbor.getMovable().scorePoint(d.getOpposite());
		Game.getInstance().getBoxRecorder().endFieldOccupied(this);

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
		
		//az érkező doboz áthelyezése az új mezőre
		w.place(this);
		
		Field backwardNeighbor = this.getNeighbor(d.getOpposite());
		Movable movable = this.getMovable();
		backwardNeighbor.remove(w); // Az érkező Worker referenciájának kitörlése arról a mezőről, ahonnan jött.
		//ha már van doboz az EndFielden akkor visszaadja az érkező Worker referenciáját,ezzel jelezve hogy nem lehet már ide lépni
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
