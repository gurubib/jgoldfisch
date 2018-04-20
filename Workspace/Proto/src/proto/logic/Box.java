package proto.logic;

import proto.out.MethodWriter;
/**
 * 
 * Ennek az osztĂˇlynak az egyes pĂ©ldĂˇnyai fogjĂˇk reprezentĂˇlni a dobozokat a jĂˇtĂ©kban. 
 * Ĺ�ket kell majd a cĂ©lmezĹ‘re eljuttatni.
 *
 */
public class Box extends Movable {

/**
 * Ez a fĂĽggvĂ©ny felel az entitĂˇs elpusztĂ­tĂˇsĂˇĂ©rt, illetve csĂ¶kkenti a szabad dobozok szĂˇmĂˇt.
 */
	@Override
	public void die() {
		MethodWriter.printOutMethod("Box.die", "");
		
		//ReferenciĂˇk nullra ĂˇllĂ­tĂˇsa
		this.getField().remove(this);
		this.setField(null);
		
		MethodWriter.printOutRet("");
	}
/**
 * Ennek a fĂĽggvĂ©nynek a segĂ­tsĂ©gĂ©vel a tolĂˇs irĂˇnyĂˇba visszalĂ©ptethetjĂĽk a dobozt. 
 * (Erre szĂĽksĂ©g van pl. amikor a falba bele akarnĂˇnk tolni a dobozt.)
 *
 * @param d a visszalĂ©ptetĂ©s irĂˇnya
 */
	@Override
	public void pushBack(Direction d) {
		MethodWriter.printOutMethod("Box.pushBack", d.toString());
		
		Field neighbor = getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.boxEnters(this, d);
		
		if (neighborMovable != null)
			neighborMovable.pushBack(d);
		
		MethodWriter.printOutRet("");
	}
/**
 * FĂĽggvĂ©ny arra az esetre, amennyiben a lĂˇdĂˇnkat (Box) egy mĂˇsik doboz tolja meg, 
 * intĂ©zi, hogy a lĂˇda a megfelelĹ‘ helyre kerĂĽljĂ¶n.
 *
 *@param b a doboz, ami tol
 *@param d az irĂˇny ahonnan tol
 */
	@Override
	public void pushByBox(Box b, Direction d) {
		MethodWriter.printOutMethod("Box.pushByBox", b.toString() + ", " + d.toString());
		
		Field neighbor = getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.boxEnters(this, d);
		
		//Ha Ă¶nmagunkat kapjuk vissza, akkor visszalĂ©pĂĽnk ellenkezĹ‘ irĂˇnyba
		if (neighborMovable == this) {
			Field backwardNeighbor = neighbor.getNeighbor(d.getOpposite());
			Movable backwardMovable = backwardNeighbor.boxEnters(this, d.getOpposite());
			
			backwardMovable.pushBack(d.getOpposite());
		} else if (neighborMovable != null) {
			neighborMovable.pushByBox(this, d);
		}
		
		this.finalizeStep();

		MethodWriter.printOutRet("");
	}
/**
 * A lĂˇda (Box) egy jĂˇtĂ©kos Ăˇltal valĂł megtolĂˇsĂˇkor hĂ­vjuk meg, 
 * intĂ©zi hogy a lĂˇda a megfelelĹ‘ helyre kerĂĽljĂ¶n.
 * 
 *@param w a munkĂˇs, aki tolja a lĂˇdĂˇt
 *@param d az irĂˇny, ahonnan tolja
 */
	@Override
	public void pushByWorker(Worker w, Direction d) {
		MethodWriter.printOutMethod("Box.pushByWorker", w.toString() + ", " + d.toString());
		
		Field neighbor = getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.boxEnters(this, d);
		
		//Ha Ă¶nmagunkat kapjuk vissza, akkor visszalĂ©pĂĽnk ellenkezĹ‘ irĂˇnyba
		if (neighborMovable == this) {
			Field backwardNeighbor = neighbor.getNeighbor(d.getOpposite());
			Movable backwardMovable = backwardNeighbor.boxEnters(this, d.getOpposite());
			
			backwardMovable.pushBack(d.getOpposite());
		} else if (neighborMovable != null) {
			neighborMovable.pushByBox(this, d);
		}
		
		this.finalizeStep();

		MethodWriter.printOutRet("");
	}
/**
 * Amennyiben a cĂ©lmezĹ‘re tolĂłdott a lĂˇda, ennek a fĂĽggvĂ©nynek a segĂ­tsĂ©gĂ©vel vĂ©gezhetjĂĽk el
 *  a megfelelĹ‘ jĂˇtĂ©kos pontjainak nĂ¶velĂ©sĂ©t, Ă­gy a hĂ­vĂˇst mindig tovĂˇbbadja 
 *  a megfelelĹ‘ irĂˇnyban ĂˇllĂł Movable-nek (meghĂ­vja rajta Ă¶nmagĂˇt).
 *
 *@param d az irĂˇny, ahonnan toltĂˇk a lĂˇdĂˇt
 */
	@Override
	public void scorePoint(Direction d) {
		MethodWriter.printOutMethod("Box.finalizeStep", "");
		
		Field neighbor = this.getField().getNeighbor(d);
		
		Movable neighborMovable = neighbor.getMovable();
		
		neighborMovable.scorePoint(d);
		
		MethodWriter.printOutRet("");
	}
/**
 * Ez az a fĂĽggvĂ©ny, ami az egyes lĂ©pĂ©sek vĂ©glegesĂ­tĂ©sĂ©rt felel, 
 * a lĂ©ptetĂ©s Ă©rvĂ©nyessĂ©ge vizsgĂˇlatĂˇt kĂ¶vetĹ‘en.
 */
	@Override
	public void finalizeStep() {
		MethodWriter.printOutMethod("Box.finalizeStep", "");
		
		getField().boxArrived(this);
		
		MethodWriter.printOutRet("");
	}

}
