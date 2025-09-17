import java.io.*;
import java.util.Scanner;
public class FederalSkilledWorker {
	
	//constant variables//
	
	static final int FIRST_NAME_INDEX = 0;
	static final int LAST_NAME_INDEX = 1;
	static final int AGE_INDEX = 2;
	
	static final int MARITAL_STATUS_INDEX = 3;
	
	static final int SPEAK_1_INDEX = 4;
	static final int LISTEN_1_INDEX = 5;
	static final int READ_1_INDEX = 6;
	static final int WRITE_1_INDEX = 7;
	static final int ALL_2_INDEX = 8;
	
	static final int EDUCATION_INDEX = 9;
	static final int WORK_EXPERIENCE_INDEX = 10;
	static final int ARRANGED_EMPLOYMENT_INDEX = 11;
	
	static final int ADAPTABILITY_SPOUSE_LANGUAGE_INDEX = 12;
	static final int ADAPTABILITY_SPOUSE_EDUCATION_INDEX = 13;
	static final int ADAPTABILITY_SPOUSE_WORK_INDEX = 14;
	
	static final int ADAPTABILITY_YOU_EDUCATION_INDEX = 15;
	static final int ADAPTABILITY_YOU_WORK_INDEX = 16;
	static final int ADAPTABILITY_YOU_EMPLOYMENT_INDEX = 17;
	
	static final int ADAPTABILITY_RELATIVE_INDEX = 18;
	
	
	
	public static void main(String[] args) throws IOException {
		
//	  public static final File CONFIG_FILE = new File("I can't see the path");  What is this?

		String fileName;
		String line;
		String[] worker;
		
		int totalEducationPoints;
		
		Scanner keyboard= new Scanner(System.in);
		
		
		//For reading file//
		
		System.out.println("What is your file name: ");
		fileName = keyboard.nextLine();
		
		File file= new File("dataTextFile/" + fileName );
		FileReader filereader= new FileReader(file);
		BufferedReader bufferedreader= new BufferedReader(filereader);
		
		//reads the first and second line to skip the category section
		line= bufferedreader.readLine();
		line= bufferedreader.readLine();
		
		
		while(line != null) {
			
			worker= line.split("\t");
			System.out.println(worker[FIRST_NAME_INDEX]); //this line is temporary, just to test if it works 
			//place getFunctions here
			// for the input for the other functions, use (worker[X_INDEX]) for example System.out.println(worker[X_INDEX]);
			
			totalEducationPoints= getEducationPts(worker[SPEAK_1_INDEX], worker[LISTEN_1_INDEX], worker[READ_1_INDEX], worker[WRITE_1_INDEX], worker[ALL_2_INDEX]);
			System.out.println(totalEducationPoints); //temporary test 
			
			line= bufferedreader.readLine();
			
			
		}
		
		bufferedreader.close();
		
	
	}
	
	//IDK if these variable are needed but if it is then will put on top probably
	static final int CLB_9_PLUS = 9;
	static final int CLB_8 = 8;
	static final int CLB_7 = 7;
	
	static int getEducationPts(String speak, String listen, String read, String write, String all) {
		
		int points = 0;
		
		//speak section
		int speakInt= Integer.parseInt(speak);
		
		if (speakInt >= CLB_9_PLUS) {
			points += 6;
		} else if( speakInt == CLB_8) {
			points += 5;
		} else if( speakInt == CLB_7) {
			points += 4;
		}
		
		//listen section
		int listenInt= Integer.parseInt(listen);
		
		if (listenInt >= CLB_9_PLUS) {
			points += 6;
		} else if( listenInt == CLB_8) {
			points += 5;
		} else if( listenInt == CLB_7) {
			points += 4;
		}
		
		//read section
		int readInt= Integer.parseInt(read);
		
		if (readInt >= CLB_9_PLUS) {
			points += 6;
		} else if( readInt == CLB_8) {
			points += 5;
		} else if( readInt == CLB_7) {
			points += 4;
		}
		
		//write section
		int writeInt= Integer.parseInt(write);
		
		if (writeInt >= CLB_9_PLUS) {
			points += 6;
		} else if( writeInt == CLB_8) {
			points += 5;
		} else if( writeInt == CLB_7) {
			points += 4;
		}
		
		//second language section
		//https://www.w3schools.com/java/ref_string_equals.asp 
		System.out.println(all);
		if (all.equals("yes")) {
			points += 4;
		} else if( all.equals("no")) {
			points += 0;
		}
		
		return points;
	}
	
	

}

