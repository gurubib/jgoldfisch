package graf.logic;

import graf.out.MethodWriter;

/**
 * Absztrakt osztály, melyből a pályán lévő mozgatható obejktumok öröklődnek.
 */
public abstract class Movable {

    private String skeletonName;
    private Field field;

    public abstract void die();
    public abstract void pushBack(Direction d);
    public abstract void pushByBox(Box b, Direction d, int f);
    public abstract void pushByWorker(Worker w, Direction d, int f);
    public abstract void scorePoint(Direction d);
    public abstract void finalizeStep();

    /**
     * Az objektum nevét kiíró toString() függvény felüldefiniálása.
     */
    @Override
    public String toString() {
        return skeletonName;
    }

    /**
     * Beállítja az objektum nevét a paraméterként kapott értékre
     *
     * @param skeletonName az objektum neve
     */
    public void setSkeletonName(String skeletonName) {
        this.skeletonName = skeletonName;
    }

    /**
     * Beállítja a mezőt a paraméterként kapottra.
     *
     * @param f referencia egy mezőre
     */
    public void setField(Field f) {
        field = f;
    }

    /**
     * Beállítja az osztály által tárolt mezőt a paraméterként kapott értékre.
     *
     * @param f referencia egy mezőre
     */
    public void place(Field f) {
        MethodWriter.printOutMethod("Movable.place", f.toString());

        this.field = f;

        MethodWriter.printOutRet("");
    }

    /**
     * Visszadja a Movable által tárolt mező referenciáját
     *
     * @return referencia a mezőre
     */
    public Field getField() {
        //MethodWriter.printOutMethod("Movable.getField", "");

    	/*
        if (field != null)
        	//MethodWriter.printOutRet(this.field.toString());
        else
        	//MethodWriter.printOutRet("null");
       */
        return this.field;
    }
}
