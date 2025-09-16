import java.io.*;
import java.util.Scanner;
public class FederalSkilledWorker {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String fileName;
		String line;
		String[] worker;
		
		Scanner keyboard= new Scanner(System.in);
		
		System.out.println("What is your file name: ");
		fileName = keyboard.nextLine();
		
		File file= new File("dataTextFile/" + fileName );
		FileReader filereader= new FileReader(file);
		BufferedReader bufferedreader= new BufferedReader(filereader);
		
		line= bufferedreader.readLine();
		line= bufferedreader.readLine();
	
		worker= line.split("\t");
		

		
		System.out.println(worker[0]); 
		int b= 0;
		while(line != null) {
			
			System.out.println(worker[0]); 
			
			
			line= bufferedreader.readLine();
			worker= line.split("\t");
		}
		
		bufferedreader.close();
		
		
			
	}

}
