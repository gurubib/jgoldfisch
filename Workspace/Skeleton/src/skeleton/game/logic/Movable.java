package skeleton.game.logic;

import skeleton.out.MethodWriter;

/**
 * Absztrakt osztály, melybõl a pályán lévõ
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
     * Beállítja az objektum nevét a paraméterként kapott értékre
     *
     * @param skeletonName az objektum neve
     */
    public void setSkeletonName(String skeletonName) {
        this.skeletonName = skeletonName;
    }

    /**
     * Beállítja a mezõt a paraméterként kapottra.
     *
     * @param f referencia egy mezõre
     */
    public void setField(Field f) {
        field = f;
    }

    /**
     * Beállítja az osztály által tárolt mezõt a paraméterként kapott értékre.
     *
     * @param f referencia egy mezõre
     */
    public void place(Field f) {
        MethodWriter.printOutMethod("Movable.place", f.toString());

        this.field = f;

        MethodWriter.printOutRet("");
    }

    /**
     * Visszadja a Movable által tárolt mezõ referenciáját
     *
     * @return referencia a mezõre
     */
    public Field getField() {
        MethodWriter.printOutMethod("Movable.getField", "");

        MethodWriter.printOutRet(this.field.toString());
        return this.field;
    }
}
