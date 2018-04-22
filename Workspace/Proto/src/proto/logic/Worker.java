package proto.logic;

import java.util.ArrayList;

import proto.out.MethodWriter;

/**
 * A pályán egy munkást reprezentáló osztály. Képes eltolni a ládákat, továbbá
 * pontokat kap, minden helyére illesztett dobozért.
 */
public class Worker extends Movable {

    private int points = 0;
    int force = 5; //A játékos ereje, mely meghatározza, hogy el tudja-e tolni a dobozok sorát.
    ArrayList<Oil> oils = new ArrayList<Oil>(); //Referenciák a munkás által lerakható olaj Slime-okra.
    ArrayList<Honey> honeys = new ArrayList<Honey>();  //Referenciák a munkás által lerakható méz Slime-okra.
    int id;

    
    
    public Worker() {
    	for (int i = 0; i <5; i++) {
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
     * @return A points attribútum értéke
     */
    public int getPoints() {
		return points;
	}

    /**
     * Alapvető setter függvény, beállítható vele az adott attribútum
     * 
     * @param A points attribútum kívánt értéke
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

        this.getField().remove(this);
        this.setField(null);
        Game.getInstance().deleteWorker(this);
        MethodWriter.printOutRet("");
    }

    /**
     * Visszatolja a munkást eggyel a kapott irányba. Amennyiben ez nem
     * lehetséges a munkás meghal.
     *
     * @param d a tolás iránya
     */
    @Override
    public void pushBack(Direction d) {
        MethodWriter.printOutMethod("Worker.pushBack", d.toString());

        Field neighbor = this.getField().getNeighbor(d);

        Movable neighborMovable = neighbor.workerEnters(this, d);

        if (neighborMovable != null) {
            this.die();
        }

        MethodWriter.printOutRet("");
    }

    /**
     * Metódus a munkás mozgatásához, amennyiben egy doboz tolja meg.
     *
     * @param b referencia a munkást megtoló dobozra
     * @param d az irány, melybe a munkást a doboz tolja
     */
    @Override
    public void pushByBox(Box b, Direction d, int f) {
        MethodWriter.printOutMethod("Worker.pushByBox", b.toString() + ", " + d.toString());

        Field neighbor = this.getField().getNeighbor(d);

        Movable neighborMovable = neighbor.workerEnters(this, d);

        if (neighborMovable == this) {
            this.die();
        } else if (neighborMovable != null) {
            neighborMovable.pushByWorker(this, d,f);
            
            if (getField() != null)
            	this.finalizeStep();
        } else {
            this.finalizeStep();
        }

        MethodWriter.printOutRet("");
    }

    /**
     * Metódus a munkás mozgatásához, ammennyiben egy munkás tolja meg.
     *
     * @param w referencia a munkást megtoló munkásra
     * @param d irány, melybe a munkást a másik munkás tolja
     */
    @Override
    public void pushByWorker(Worker w, Direction d, int f) {
        MethodWriter.printOutMethod("Worker.pushByWorker", w.toString() + ", " + d.toString());

        w.goBack(d.getOpposite());
        this.getField().setMovable(this);

        MethodWriter.printOutRet("");
    }

    /**
     * A pontszerzés alkalmával meghívott függvény.
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
     * A munkás irányításáért felelős metódus. A paraméterként kapott irányba
     * mozgatja a munkást és meghívja az ehhez szükséges további metódusokat.
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
            neighborMovable.pushByWorker(this, d,force);
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
     * Visszalép a paraméterként kapott irányba egyet.
     *
     * @param d a visszalépés iránya.
     */
    public void goBack(Direction d) {
        MethodWriter.printOutMethod("Worker.goBack", d.toString());

        Field neighbor = this.getField().getNeighbor(d);

        Movable neighborMovable = neighbor.workerEnters(this, d);

        if (neighborMovable != null) {
            neighborMovable.pushByWorker(this, d,1000);
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
    public void placeOil() {
    	MethodWriter.printOutMethod("Worker.placeOil", "");
    	if (oils.size() == 0) {
    		MethodWriter.printOutRet("");	
    		return;
    	}
    	this.getField().setSlime(oils.remove(0));
    	MethodWriter.printOutRet("");
    }
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
