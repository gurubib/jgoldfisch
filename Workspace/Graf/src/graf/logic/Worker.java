package graf.logic;

import java.util.ArrayList;

import graf.out.MethodWriter;

/**
 * A játékosok által irányított munkást reprezentáló osztály. 
 * Ő tudja a ládákat (Box) eltolni, illetve a pontokat is ő kapja majd. 
 * Ismeri a mezőt, ahol áll és tárolja a pontjait. 
 * Meg tud halni, ez többféleképpen is bekövetkezhet, 
 * ilyenkor eltűnik a pályáról.
 */
public class Worker extends Movable {

	/**
	 * A játékos pontjai, amiket az egyes ládák célmezőre juttatásáért kap.
	 */
	private int points = 0;
	
	/**
	 * A játékos ereje, mely minden meghatározza, 
	 * hogy el tudja-e tolni a dobozok sorát.
	 */
	int force = 5; // A játékos ereje, mely meghatározza, hogy el tudja-e tolni a dobozok
					// sorát.
	
	/**
	 * Referenciák a munkás által lerakható olaj Slime-okra.
	 */
	ArrayList<Oil> oils = new ArrayList<Oil>(); // Referenciák a munkás által lerakható olaj Slime-okra.
	/**
	 * Referenciák a munkás által lerakható méz Slime-okra.
	 */
	ArrayList<Honey> honeys = new ArrayList<Honey>(); // Referenciák a munkás által lerakható méz Slime-okra.
	/**
	 * A munkás azonosítója.
	 */
	int id;
	
	/**
	 * Default konstruktor, mely ellátja olajjal és mézzel a játékost.
	 */
	public Worker() {
		for (int i = 0; i < 5; i++) {
			oils.add(new Oil());
			honeys.add(new Honey());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	/**
	 * Alapvető getter függvény, visszaadja az adott attribútumot
	 * 
	 * @return points attribútum értéke
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Alapvető setter függvény, beállítható vele az adott attribútum
	 * 
	 * @param points points attribútum kívánt értéke
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Elpusztítja a játékost és csökkenti a munkások számát egyel.
	 */
	@Override
	public void die() {
		MethodWriter.printOutMethod("Worker.die", "");

		if (this.getField() != null) {
			this.getField().remove(this);
			this.setField(null);
		}
		
		Game.getInstance().deleteWorker(this);
		MethodWriter.printOutRet("");
	}

	/**
	 * Ennek a függvénynek ott van szerepe, amikor egy munkás a 
	 * dobozt a falba bele akarná tolni. Ekkor ezt nem engedi majd, 
	 * hanem az egész sor (pl.: munkás-doboz-fal) egyet visszafele 
	 * fog lépni. Ekkor ha a munkás visszalépendő mezőjén tartózkodik 
	 * valami, akkor a munkás meghal.
	 * Ennek a megvalósításáért részben felelős ez a metódus is.
	 *
	 * @param d a tolás iránya
	 */
	@Override
	public void pushBack(Direction d) {
		MethodWriter.printOutMethod("Worker.pushBack", d.toString());

		Field neighbor = this.getField().getNeighbor(d);

		Movable neighborMovable = neighbor.workerEnters(this, d);

		if (neighborMovable != null) {
			neighbor.setMovable(neighborMovable);
			this.die();
		}

		MethodWriter.printOutRet("");
	}

	/**
	 * Ez a függvény arra az esetre szolgál, hogyha a munkást 
	 * egy dobozzal (Box) próbálnának meg eltolni. 
	 * Továbbá továbbadja a kapott erőt a következőnek a sorban.
	 *
	 * @param b referencia a munkást megtoló dobozra
	 * @param d az irány, melybe a munkást a doboz tolja
	 * @param f az erő, amivel tolják
	 */
	@Override
	public void pushByBox(Box b, Direction d, int f) {
		MethodWriter.printOutMethod("Worker.pushByBox", b.toString() + ", " + d.toString());

		Field neighbor = this.getField().getNeighbor(d);

		Movable neighborMovable = neighbor.workerEnters(this, d);

		if (neighborMovable == this) {
			this.die();
		} else if (neighborMovable != null) {
			neighborMovable.pushByWorker(this, d, f);

			if (getField() != null)
				this.finalizeStep();
		} else {
			this.finalizeStep();
		}

		MethodWriter.printOutRet("");
	}

	/**
	 * Ez a függvény arra az esetre szolgál, ha a munkást egy 
	 * másik munkás próbálná meg eltolni. 
	 * Továbbá továbbadja a kapott erőt a következőnek a sorban.
	 * @param w referencia a munkást megtoló munkásra
	 * @param d irány, melybe a munkást a másik munkás tolja
	 * @param f az erő, amivel tolják
	 */
	@Override
	public void pushByWorker(Worker w, Direction d, int f) {
		MethodWriter.printOutMethod("Worker.pushByWorker", w.toString() + ", " + d.toString());

		w.goBack(d.getOpposite());
		this.getField().setMovable(this);

		MethodWriter.printOutRet("");
	}

	/**
	 * A pontok növeléséért felelős, méghozzá az 
	 * increasePoints() függvény segítségével.
	 *
	 * @param d Az irány, melybe továbadja a pontszerzést.
	 */
	@Override
	public void scorePoint(Direction d) {
		MethodWriter.printOutMethod("Worker.scorePoint", d.toString());

		this.increasePoints();

		MethodWriter.printOutRet("");
	}

	/**
	 * A munkás (Worker) irányításáért felelős függvény. 
	 * A paraméterben kapott irányban fogja megpróbálni elmozdítani 
	 * a munkást a szomszédos mezők figyelembe vételével 
	 * (Mindez önakaratból történik, tehát a felhasználó beavatkozására).
	 *
	 * @param d a mozgatás iránya
	 */
	public void control(Direction d) {
		MethodWriter.printOutMethod("Worker.control", "d");

		Field neighbor = this.getField().getNeighbor(d);

		Movable neighborMovable = neighbor.workerEnters(this, d);

		if (neighborMovable == this) {
			Field backwardNeighbor = this.getField().getNeighbor(d.getOpposite());
			backwardNeighbor.workerEnters(this, d.getOpposite());
		} else if (neighborMovable != null) {
			neighborMovable.pushByWorker(this, d, force);
		}

		this.finalizeStep();

		MethodWriter.printOutRet("");
	}

	/**
	 * Véglegesíti a mezőre lépést.
	 */
	@Override
	public void finalizeStep() {
		MethodWriter.printOutMethod("Worker.finalizeStep", "");

		getField().workerArrived(this);

		MethodWriter.printOutRet("");
	}

	/**
	 * A munkás - munkás ütközés / eltolás megfelelő 
	 * lekezelésében van szerepe, mégpedig elindít egy visszafele 
	 * való lökést, hiszen nem szabad hagyni, hogy az egyik munkás 
	 * a másikat közvetlenül eltolja, tehát úgymond visszarendezi 
	 * a dolgokat.
	 *
	 * @param d a visszalépés iránya.
	 */
	public void goBack(Direction d) {
		MethodWriter.printOutMethod("Worker.goBack", d.toString());

		Field neighbor = this.getField().getNeighbor(d);

		Movable neighborMovable = neighbor.workerEnters(this, d);

		if (neighborMovable != null) {
			neighborMovable.pushByWorker(this, d, 1000);
		}

		MethodWriter.printOutRet("");
	}

	/**
	 * Növeli a munkás pontjainak számát eggyel.
	 */
	public void increasePoints() {
		MethodWriter.printOutMethod("Worker.increasePoint", "");

		points++;

		MethodWriter.printOutRet("");
	}
	/**
	 * Függvény arra az esetre, ha a játékos egy olajmezőt helyez a pályára.
	 */
	public void placeOil() {
		MethodWriter.printOutMethod("Worker.placeOil", "");
		if (oils.size() == 0) {
			MethodWriter.printOutRet("");
			return;
		}
		this.getField().setSlime(oils.remove(0));
		MethodWriter.printOutRet("");
	}
	/**
	 * Függvény arra, ha a játékos egy mézet helyez a pályára.
	 */
	public void placeHoney() {
		MethodWriter.printOutMethod("Worker.placeHoney", "");
		if (honeys.size() == 0) {
			MethodWriter.printOutRet("");
			return;
		}
		MethodWriter.printOutRet("");
		this.getField().setSlime(honeys.remove(0));
	}

}
