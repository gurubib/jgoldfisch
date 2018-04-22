import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Compare {

	public static void main(String[] args) {

		String name1 = args[0];
		String name2 = args[1];
		
		boolean areEqual = true;
		int numOfLines = 1;
		
		try(BufferedReader br1 = new BufferedReader(new FileReader(new File(name1)));
			BufferedReader br2 = new BufferedReader(new FileReader(new File(name2)))) {
						
			String line1 = br1.readLine();         
	        String line2 = br2.readLine();
	         
	        while (line1 != null || line2 != null) {
	            if(line1 == null || line2 == null) {
	                areEqual = false;              
	                break;
	            } else if(!line1.equalsIgnoreCase(line2)) {
	                areEqual = false;         
	                break;
	            }
	             
	            line1 = br1.readLine();             
	            line2 = br2.readLine();	             
	            numOfLines++;
	        }			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		if(areEqual) {
			System.out.println("Two files are match");
		} else {
			System.out.println("The two files differ in line " + numOfLines);
		}
		
	}
}
