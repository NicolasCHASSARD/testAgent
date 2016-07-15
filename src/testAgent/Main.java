package testAgent;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	/**
	 * Entry point of the program
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException  {
		String file = "resources/AgentTest.csv";
		runProgram(file);
	}
	
	/** Start the program by asking for the user input
	 * 
	 * @param file Relative path to the CSV file (should be in 'resources/AgentTest.csv'
	 * @throws IOException
	 */
	static void runProgram(String file) throws IOException {
		Scanner scanner = new Scanner(System.in);
		boolean cont = false;
		
		do {
			System.out.println("Do you want to run the 15 years with one unique brandfactor? (y/n):");
			String str = scanner.nextLine();
			if (str.contains("y") || str.contains("Y")) {
				executeAllYears(scanner, file);
				cont = true;
				if (startAgain(scanner))
					cont = false;
				else 
					System.out.println("Goodbye!");
			} else if (str.contains("n") || str.contains("N")) {
				executeOneYear(scanner, file);
				cont = true;
				if (startAgain(scanner))
					cont = false;
				else
					System.out.println("Goodbye!");
			} else {
				System.out.println("Please, answer with a 'y' or 'n'");
			}
		} while (!cont);
	}
	
	/** Ask for the user input, to know if the program should run again
	 * 
	 * @param scanner Console scanner
	 * @return
	 */
	static boolean startAgain(Scanner scanner) {
		boolean cont = false;
		boolean start = false;
		
		do {
			System.out.println("Do you want to start again? (y/n):");
			String str = scanner.nextLine();
			if (str.contains("y") || str.contains("Y")) {
				cont = true;
				start = true;
			} else if (str.contains("n") || str.contains("N")) {
				cont = true;
				start = false;
			} else {
				System.out.println("Please, answer with a 'y' or 'n'");
			}
		} while (!cont);
		
		return start;
	}

	/**
	 * Ask the user for a valid Brand Factor value (between 0.1 and 2.9)
	 * 
	 * @param scanner Console scanner
	 * @param year The year of the execution (between 1 to 15)
	 * @return Returns the Brand Factor value
	 */
	static float getBrandFactor(Scanner scanner, int year) {
		boolean getBF = true;
		float brandFactor = 0;
		
		do {
			if (year == 0)
				System.out.print("Please, choose a brand factor between 0.1 and 2.9:\n");
			else
				System.out.print("Please, choose a brand factor between 0.1 and 2.9 for year " + year + ":\n");
			float bF = Float.parseFloat(scanner.nextLine());
			if (bF < 0.1 || bF > 2.9) {
				System.out.println("This number is out of range");
			} else {
				brandFactor = bF;
				getBF = false;
			}
		} while (getBF);
		return brandFactor;
	}
	

	/**
	 * Launch the ReadCSV class and run the program for 15 years
	 * 
	 * @param scanner Console scanner
	 * @param file The path to the CSV file
	 * @throws IOException
	 */
	static void executeAllYears(Scanner scanner, String file) throws IOException {
		ReadCSV reader = new ReadCSV(file);
		reader.run();
		reader.printAllExecution(getBrandFactor(scanner, 0));
	}

	/**
	 * Launch the ReadCSV class and run the program year after year
	 * 
	 * @param scanner Console scanner
	 * @param file The path to the CSV file
	 * @throws IOException
	 */
	static void executeOneYear(Scanner scanner, String file) throws IOException {
		ReadCSV reader = new ReadCSV(file);
		for (int i = 1; i <= 15; ++i) {
			reader.run();
			reader.printOneYear(i, getBrandFactor(scanner, i));
		}
		System.out.println("Synthesis:");
		reader.printAllOutputs();
	}
}
