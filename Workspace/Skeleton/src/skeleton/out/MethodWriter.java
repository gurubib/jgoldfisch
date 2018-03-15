package skeleton.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MethodWriter {
	
	
	private static int methodDepth = 0;
	
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
		
		System.out.println("ret " + retVal + ";");
		
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
	
}
