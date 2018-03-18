package skeleton.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MethodWriter {
	
	private static int methodDepth = 0;
	private static int boxNum = 0;
	private static int workerNum = 0;
	
	private static String boxNamePostfix() {
		return Integer.toString(++boxNum);
	}
	
	private static String workerNamePostfix() {
		return Integer.toString(++workerNum);
	}
	
	public static String nameGenerator(String prefix) {
		if (prefix.equals("b")) {
			return prefix + boxNamePostfix();
		} else if (prefix.equals("w")){
			return prefix + workerNamePostfix();
		} else {
			return "m";
		}
	}
	
	public static void printOutMethod(String methodName, String params) {
		methodDepth++;
		
		System.out.print(">>");
		
		for (int i = 0; i < methodDepth; ++i)
			System.out.print("   ");
		
		System.out.println(methodName + "(" + params + ");");
	}
	
	public static void printOutRet(String retVal) {
		System.out.print("<<");
		
		for (int i = 0; i < methodDepth; ++i)
			System.out.print("   ");
		
		System.out.println("ret" + ((retVal == null || retVal.equals(""))? retVal : (" " + retVal)) + ";");
		
		methodDepth--;
	}
	
	public static void printOutQuestion(String question) {
		methodDepth++;
		
		System.out.print("? ");
		
		for (int i = 0; i < methodDepth; ++i)
			System.out.print("   ");
		
		System.out.print(question + ": ");
		
		methodDepth--;
	}
	public static String readFromStdin() {
		String answer = null;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			answer = br.readLine();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public static void resetNameCounters() {
		methodDepth = 0;
		boxNum = 0;
		workerNum = 0;
	}
}
