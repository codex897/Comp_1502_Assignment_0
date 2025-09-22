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
	
	static final int CLB_9_PLUS = 9;
	static final int CLB_8 = 8;
	static final int CLB_7 = 7;
	
	static final String FILEPATH_INPUT = ("data/input/");  
	static final String FILEPATH_OUTPUT = ("data/output/");  
	
	public static void main(String[] args) throws IOException {
		
		

		String fileNameInput;
		String fileNameOutput;
		String line;
		String[] worker;
		
		int totalLangPoints;
		int totalEducationPts;
		int totalWorkExpPts;
		int totalArrangedEmpPts;
		int totalAgePts;
		int totalAdaptPoints;
		int totalQualifiedWorkers = 0;
		
		Scanner keyboard= new Scanner(System.in);
		


		//For reading file//
		
		System.out.print("Please provide the name of the Input file (to be located in data/input/: ");
		fileNameInput = keyboard.nextLine();
		
		File file= new File( FILEPATH_INPUT + fileNameInput );
		FileReader filereader= new FileReader(file);
		BufferedReader bufferedreader= new BufferedReader(filereader);
		
		//reads the first and second line to skip the category section
		line= bufferedreader.readLine();
		line= bufferedreader.readLine();
		
		
		//output file
		System.out.print("Please provide the name of the Input file (to be located in data/output/: ");
		fileNameOutput = keyboard.nextLine();
		
		FileWriter fileWriter = new FileWriter(FILEPATH_OUTPUT +"qualified-applicants-full.txt");
		PrintWriter outputfile = new PrintWriter(fileWriter);
		//output simple text styling just like the example
		outputfile.println(String.format("%-21s|%-21s|%5s|%5s", "First Name", "Last Name", "Age", "Score"));
		outputfile.println(String.format("%-21s+%-21s+%5s+%5s", "---------------------", "---------------------", "-----", "-----"));
		
		
		//while loop to read each line and extract information to find qualified applicants
		while(line != null) {
			int totalWorkerScore = 0;
			
			worker= line.split("\t");
			
			// for the input for the other functions, use (worker[X_INDEX]) for example System.out.println(worker[X_INDEX]);
			
			totalLangPoints= getLangPts(worker[SPEAK_1_INDEX], worker[LISTEN_1_INDEX], worker[READ_1_INDEX], worker[WRITE_1_INDEX], worker[ALL_2_INDEX]);
			totalEducationPts = getEducationPts(worker[EDUCATION_INDEX]);
			totalWorkExpPts = getWorkExpPts(worker[WORK_EXPERIENCE_INDEX]);			
			totalAgePts = getAgePts(worker[AGE_INDEX]);
			totalArrangedEmpPts= getArrangedEmpPts(worker[ARRANGED_EMPLOYMENT_INDEX]);
			totalAdaptPoints= getAdaptibilityPts(worker[ADAPTABILITY_SPOUSE_LANGUAGE_INDEX], worker[ADAPTABILITY_SPOUSE_EDUCATION_INDEX], worker[ADAPTABILITY_SPOUSE_WORK_INDEX], worker[ADAPTABILITY_YOU_EDUCATION_INDEX], worker[ADAPTABILITY_YOU_WORK_INDEX], worker[ADAPTABILITY_YOU_EMPLOYMENT_INDEX], worker[ADAPTABILITY_RELATIVE_INDEX]);
			
			//add up the total and if 67 or above add to output file
			totalWorkerScore = totalLangPoints + totalAdaptPoints + totalEducationPts + totalAgePts + totalWorkExpPts + totalArrangedEmpPts;
			
			if (totalWorkerScore >= 67) {
				totalQualifiedWorkers ++;
				outputfile.println(String.format("%-21s %-21s %5s %5s", worker[FIRST_NAME_INDEX], worker[LAST_NAME_INDEX], worker[AGE_INDEX], totalWorkerScore));
			}
			
			
			line= bufferedreader.readLine();
		}
		
	
		bufferedreader.close();
		outputfile.close();
		
		// message to state how many workers were qualified
		System.out.println("\nThere were " + totalQualifiedWorkers + " qualified applicants");
	}
	

	//functions for the main method starts here
	
	static int getLangPts(String speak, String listen, String read, String write, String all) {
		
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
		switch(all) {
			case "yes":
				points += 4;
				break;
			default:
				points += 0;
				break;
		}
		
		return points;
	}

	
	static int getEducationPts(String education) {
		
		int points = 0;
		
		switch(education) {
			case "Secondary school (high school diploma)":
				points += 5;
				break;
			case "\"One-year degree, diploma or certificate\"":
				points += 15;
				break;
			case "One-year degree, diploma or certificate":
				points += 15;
				break;
			case "\"Two-year degree, diploma or certificate\"":
				points += 19;
				break;
			case "Two-year degree, diploma or certificate":
				points += 19;
				break;
			case "Bachelor's degree or other programs (three or more years)":
				points += 21;
				break;
			case "\"Two or more certificates, diplomas, or degrees\"":
				points += 22;
				break;
			case "Two or more certificates, diplomas, or degrees":
				points += 22;
				break;
			case "Professional degree needed to practice in a licensed profession":
				points += 23;
				break;
			case "University degree at the Master's level":
				points += 23;
				break;
			case "University degree at the Doctoral (PhD) level":
				points += 25;
				break;
			default:
				points += 0;
				break;
		}
		
		return points;
	}
	
	
	static int getWorkExpPts(String workExpYears) {
		int points = 0;
		int workExpInt= Integer.parseInt(workExpYears);
		
		
		if (workExpInt >= 6) {
			points += 15;
		} else if (workExpInt >= 4) {
			points += 13;
		} else if (workExpInt >= 2) {
			points += 11;
			
		} else if (workExpInt == 1) {
			points += 9;
			
		}else if (workExpInt < 1) {
			points += 0;			
		}
		
		return points;
	}
	

	static int getArrangedEmpPts(String yesNoArrangedEmp) {
		int points = 0;
		switch(yesNoArrangedEmp) {
		case "yes":
			points += 10;
			break;
		default:
			points += 0;
			break;
		}
		return points;
	}
	
	
	static int getAgePts(String ageStr) {
	    int age = Integer.parseInt(ageStr);

	    int points = 0;

	    if (age >= 18 && age <= 35) {
	        points = 12;  
	    } else if (age == 36) {
	        points = 11;
	    } else if (age == 37) {
	        points = 10;
	    } else if (age == 38) {
	        points = 9;
	    } else if (age == 39) {
	        points = 8;
	    } else if (age == 40) {
	        points = 7;
	    } else if (age == 41) {
	        points = 6;
	    } else if (age == 42) {
	        points = 5;
	    } else if (age == 43) {
	        points = 4;
	    } else if (age == 44) {
	        points = 3;
	    } else if (age == 45) {
	        points = 2;
	    } else if (age == 46) {
	        points = 1;
	    } else {
	        points = 0;  
	    }

	    return points;
	}
	
	
	static int getAdaptibilityPts(String spouseLanguage, String spouseStudies, String spouseWork, String yourStudies,String yourWork, String yourWorkFuture, String yourRelatives ) {		

		int totalPoints = 0;

		switch (spouseLanguage.toLowerCase()) {
			case "yes":
				totalPoints += 5;
				break;
			default:
				break; 
		}
		   
		switch (spouseStudies.toLowerCase()) {
			case "yes":
				totalPoints += 5;
				break;
			default:
				break;
		}

		switch (spouseWork.toLowerCase()) {
			case "yes":
				totalPoints += 5;
				break;
			default:
				break;
		}

		switch (yourStudies.toLowerCase()) {
			case "yes":
				totalPoints += 5;
				break;
			default:
				break;
		}
   
		switch (yourWork.toLowerCase()) {
			case "yes":
				totalPoints += 10;
				break;
			default:
				break;
		}
		
		switch (yourWorkFuture.toLowerCase()) {
			case "yes":
				totalPoints += 5;
				break;
			default:
				break;
		}

		switch (yourRelatives.toLowerCase()) {
			case "yes":
				totalPoints += 5;
				break;
			default:
				break;
		}

			if (totalPoints > 10) {
				totalPoints = 10;
			}
		        return totalPoints;
	}
	
	
	
	
	
	
	
}
	
		

	

	







