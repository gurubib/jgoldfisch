package skeleton.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A szkeleton program megfelelő megjelenítéséért felelős osztály,
 * és az ehhez tartozó feladatokat irányítja, valamint az adatokat tárolja.
 * A feladata általános így minden metódusa és változója statikus.
 * 
 * @author jgoldfisch
 *
 */
public class MethodWriter {
	
	/**
	 * Az adott függvényhívás mélysége, fontos a kiíráshoz.
	 */
	private static int methodDepth = 0;
	
	/**
	 * Az aktuális esetben szereplő dobozok száma, fontos a megfelelő nevezéktanhoz.
	 */
	private static int boxNum = 0;
	
	/**
	 * Az aktuális esetben szereplő munkások száma, fontos a megfelelő nevezéktanhoz.
	 */
	private static int workerNum = 1;
	
	/**
	 * A nevezéktanhoz szükséges dobozszámot adja vissza. Ez a doboz nevének második fele.
	 * 
	 * @return Soron következő doboz sorszám
	 */
	private static String boxNamePostfix() {
		return Integer.toString(++boxNum);
	}

	/**
	 * A nevezéktanhoz szükséges munkásszámot adja vissza. Ez a munkás nevének második fele.
	 * 
	 * @return Soron következő munkás sorszám
	 */
	private static String workerNamePostfix() {
		return Integer.toString(++workerNum);
	}
	
	/**
	 * Előállít egy, a nevezéktannak megfelelő nevet az adott előtag és a számlálók alapján.
	 * Nem megfelelő előtag esetén általános nevet ad.
	 * 
	 * @param prefix A névhez szükséges előtag
	 * @return A kész, megfelelő név
	 */
	public static String nameGenerator(String prefix) {
		if (prefix.equals("b")) {
			return prefix + boxNamePostfix();
		} else if (prefix.equals("w")){
			return prefix + workerNamePostfix();
		} else {
			return "m";
		}
	}
	
	/**
	 * Egy metódust ír ki, az előre meghatározott (lsd. szkeleton terv) formában.
	 * 
	 * @param methodName Metódus neve
	 * @param params Metódus paraméterei
	 */
	public static void printOutMethod(String methodName, String params) {
		methodDepth++;
		
		System.out.print(">>");
		
		for (int i = 0; i < methodDepth; ++i)
			System.out.print("   ");
		
		System.out.println(methodName + "(" + params + ");");
	}
	
	/**
	 * A visszatéréseket írja ki megfelelő formában. (lsd. szkeleton terv)
	 * 
	 * @param retVal A visszaadandó objektum neve
	 */
	public static void printOutRet(String retVal) {
		System.out.print("<<");
		
		for (int i = 0; i < methodDepth; ++i)
			System.out.print("   ");
		
		System.out.println("ret" + ((retVal == null || retVal.equals(""))? retVal : (" " + retVal)) + ";");
		
		methodDepth--;
	}
	
	/**
	 * Kérdést ír ki, a megfelelő előírt formában. (lsd. szkeleton terv)
	 * 
	 * @param question Kiírandó kérdés
	 */
	public static void printOutQuestion(String question) {
		methodDepth++;
		
		System.out.print("? ");
		
		for (int i = 0; i < methodDepth; ++i)
			System.out.print("   ");
		
		System.out.print(question + ": ");
		
		methodDepth--;
	}
	
	/**
	 * Bemenetről való olvasás.
	 * 
	 * @return Beolvasott sztring
	 */
	public static String readFromStdin() {
		String answer = null;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			answer = br.readLine();
			answer = answer.toLowerCase();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * Számlálók alaphelyzetbe állítása.
	 */
	public static void resetCounters() {
		methodDepth = 0;
		boxNum = 0;
		workerNum = 1;
	}
}
