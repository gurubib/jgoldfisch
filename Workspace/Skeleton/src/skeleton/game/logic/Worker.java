package skeleton.game.logic;

import skeleton.out.MethodWriter;

/**
 * A p�ly�n egy munk�st reprezent�l� oszt�ly. K�pes eltolni a l�d�kat, tov�bb�
 * pontokat kap, minden hely�re illesztett doboz�rt.
 */
public class Worker extends Movable {

    private int points = 0;

    /**
     * Elbuszt�tja a j�t�kost �s cs�kkenti a munk�sok sz�m�t egyel.
     */
    @Override
    public void die() {
        MethodWriter.printOutMethod("Worker.die", "");

        this.getField().remove(this);

        MethodWriter.printOutRet("");
    }

    /**
     * Visszatolja a munk�st eggyel a kapott ir�nyba. Amennyiben ez nem
     * lehets�ges a munk�s meghal.
     *
     * @param d a tol�s ir�nya
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
     * Met�dus a munk�s mozgat�s�hoz, amennyiben egy doboz tolja meg.
     *
     * @param b referencia a munk�st megtol� dobozra
     * @param d az ir�ny, melybe a munk�st a doboz tolja
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
     * Met�dus a munk�s mozgat�s�hoz, ammennyiben egy munk�s tolja meg.
     *
     * @param w referencia a munk�st megtol� munk�sra
     * @param d ir�ny, melybe a munk�st a m�sik munk�s tolja
     */
    @Override
    public void pushByWorker(Worker w, Direction d) {
        MethodWriter.printOutMethod("Worker.pushByWorker", w.toString() + ", " + d.toString());

        w.goBack(d.getOpposite());

        MethodWriter.printOutRet("");
    }

    /**
     * A pontszerz�s alkalm�val megh�vott f�ggv�ny.
     *
     * @param d Az ir�ny, melybe tov�badja a pontszerz�st.
     */
    @Override
    public void scorePoint(Direction d) {
        MethodWriter.printOutMethod("Worker.scorePoint", d.toString());

        this.increasePoints();

        MethodWriter.printOutRet("");
    }

    /**
     * A munk�s ir�ny�t�s��rt felel�s met�dus. A param�terk�nt kapott ir�nyba
     * mozgatja a munk�st �s megh�vja az ehhez sz�ks�ges tov�bbi met�dusokat.
     *
     * @param d a mozgat�s ir�nya
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
     * V�gleges�ti a mez�re l�p�st.
     */
    @Override
    public void finalizeStep() {
        MethodWriter.printOutMethod("Worker.finalizeStep", "");

        getField().workerArrived(this);

        MethodWriter.printOutRet("");
    }

    /**
     * Visszal�p a param�terk�nt kapott ir�nyba egyet.
     *
     * @param d a visszal�p�s ir�nya.
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
     * N�veli a munk�s pontjainak sz�m�t eggyel.
     */
    public void increasePoints() {
        MethodWriter.printOutMethod("Worker.increasePoint", "");

        points++;

        MethodWriter.printOutRet("");
    }

}
