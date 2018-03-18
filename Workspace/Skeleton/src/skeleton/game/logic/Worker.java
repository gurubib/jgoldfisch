package skeleton.game.logic;

import skeleton.out.MethodWriter;

/**
 * A pályán egy munkást reprezentáló osztály. Képes eltolni a ládákat, továbbá
 * pontokat kap, minden helyére illesztett dobozért.
 */
public class Worker extends Movable {

    private int points = 0;

    /**
     * Elpusztítja a játékost és csökkenti a munkások számát egyel.
     */
    @Override
    public void die() {
        MethodWriter.printOutMethod("Worker.die", "");

        this.getField().remove(this);

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
            neighborMovable.die();
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
    public void pushByBox(Box b, Direction d) {
        MethodWriter.printOutMethod("Worker.pushByBox", b.toString() + ", " + d.toString());

        Field neighbor = this.getField().getNeighbor(d);

        Movable neighborMovable = neighbor.workerEnters(this, d);

        if (neighborMovable == this) {
            this.die();
        } else if (neighborMovable != null) {
            neighborMovable.pushByWorker(this, d);
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
    public void pushByWorker(Worker w, Direction d) {
        MethodWriter.printOutMethod("Worker.pushByWorker", w.toString() + ", " + d.toString());

        w.goBack(d.getOpposite());

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
            neighborMovable.pushByWorker(this, d);
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
            neighborMovable.pushByWorker(this, d);
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

}
