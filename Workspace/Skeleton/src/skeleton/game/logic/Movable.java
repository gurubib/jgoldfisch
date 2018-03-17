package skeleton.game.logic;

import skeleton.out.MethodWriter;

/**
 * Absztrakt oszt�ly, melyb�l a p�ly�n l�v�
 */
public abstract class Movable {

    private String skeletonName;
    private Field field;

    public abstract void die();
    public abstract void pushBack(Direction d);
    public abstract void pushByBox(Box b, Direction d);
    public abstract void pushByWorker(Worker w, Direction d);
    public abstract void scorePoint(Direction d);
    public abstract void finalizeStep();

    @Override
    public String toString() {
        return skeletonName;
    }

    /**
     * Be�ll�tja az objektum nev�t a param�terk�nt kapott �rt�kre
     *
     * @param skeletonName az objektum neve
     */
    public void setSkeletonName(String skeletonName) {
        this.skeletonName = skeletonName;
    }

    /**
     * Be�ll�tja a mez�t a param�terk�nt kapottra.
     *
     * @param f referencia egy mez�re
     */
    public void setField(Field f) {
        field = f;
    }

    /**
     * Be�ll�tja az oszt�ly �ltal t�rolt mez�t a param�terk�nt kapott �rt�kre.
     *
     * @param f referencia egy mez�re
     */
    public void place(Field f) {
        MethodWriter.printOutMethod("Movable.place", f.toString());

        this.field = f;

        MethodWriter.printOutRet("");
    }

    /**
     * Visszadja a Movable �ltal t�rolt mez� referenci�j�t
     *
     * @return referencia a mez�re
     */
    public Field getField() {
        MethodWriter.printOutMethod("Movable.getField", "");

        MethodWriter.printOutRet(this.field.toString());
        return this.field;
    }
}
