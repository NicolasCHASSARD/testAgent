package testAgent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadCSV {

	private List<Agent> listAgent;
	private String file;
	private BufferedReader br;
	private Hashtable<Integer, List<Agent>> time; // Agents per year
	private Hashtable<Integer, Hashtable<String, Integer>> outputs; // Output per year

	/**
	 * Class constructor: initialize the attributes
	 * 
	 * @param file The path to the CSV file
	 */
	public ReadCSV(String file) {
		this.file = file;
		time = new Hashtable<Integer, List<Agent>>();
		outputs = new Hashtable<Integer, Hashtable<String, Integer>>();
		listAgent = new ArrayList<Agent>();
	}

	/**
	 * Read the CSV file
	 * 
	 * @throws IOException Throws this exception if the file 'resources/AgentTest.csv' is not found
	 */
	public void run() throws IOException {
		String line = "";
		String cvsSplitBy = ",";
		br = new BufferedReader(new FileReader(file));
		String[] columns = br.readLine().split(cvsSplitBy);

		while ((line = br.readLine()) != null) {
			String[] values = line.split(cvsSplitBy);
			listAgent.add(getAgentLine(values, columns));
		}
		time.put(0, listAgent);
	}


	/**
	 * Parse one line of the CSV and fill an Agent object
	 * 
	 * @param values The content of the line as a String array
	 * @param columns The content of the columns as a String array
	 * @return Returns the Agent created from the chosen line of the CSV file
	 */
	public Agent getAgentLine(String[] values, String[] columns) {
		Agent ag = new Agent();
		
		for (int i = 0; i < 10; ++i) {
			if (values[i].isEmpty())
				values[i] = "0";

			if (columns[i].contains("Agent_Breed"))
				ag.setAgentBreed(values[i]);
			else if (columns[i].contains("Policy_ID"))
				ag.setPolicyID(Double.parseDouble(values[i]));
			else if (columns[i].contains("Age"))
				ag.setAge(Integer.parseInt(values[i]));
			else if (columns[i].contains("Social_Grade"))
				ag.setSocialGrade(Integer.parseInt(values[i]));
			else if (columns[i].contains("Payment_at_Purchase"))
				ag.setPaymentAtPurchase(Integer.parseInt(values[i]));
			else if (columns[i].contains("Attribute_Brand"))
				ag.setAttributeBrand(Float.parseFloat(values[i]));
			else if (columns[i].contains("Attribute_Price"))
				ag.setAttributePrice(Float.parseFloat(values[i]));
			else if (columns[i].contains("Attribute_Promotions"))
				ag.setAttributePromotions(Float.parseFloat(values[i]));
			else if (columns[i].contains("Auto_Renew"))
				ag.setAutoRenew(Integer.parseInt(values[i]));
			else if (columns[i].contains("Inertia_for_Switch")) {
				ag.setInertiaForSwitch(Integer.parseInt(values[i]));
				continue;
			}
		}
		// ag.printAgent();
		return ag;
	}

	/**
	 * Execute the algorithm for one year, respecting the rules described in the CSV file AgentTest.csv
	 * 
	 * @param year The number of the year (from 1 to 15)
	 * @param brandFactor The Brand Factor value chosen by the user, between 0.1 and 2.9
	 */
	public void incrementList(int year, float brandFactor) {
		List<Agent> newList = new ArrayList<Agent>();
		Iterator<Agent> iterator = time.get(year - 1).iterator();

		while (iterator.hasNext()) {
			Agent ag = iterator.next();
			Agent newAgent = ag.clone();
			newAgent.setAge(newAgent.getAge() + 1);

			if (ag.getAutoRenew() == 0) {
				float affinity = ((ag.getPaymentAtPurchase() / ag.getAttributePrice())
						+ (2 * ag.getAttributePromotions() * ag.getInertiaForSwitch()));

				if (ag.getAgentBreed().contains("Breed_C")) {
					if (affinity < (ag.getSocialGrade() * ag.getAttributeBrand()))
						newAgent.setAgentBreed("Breed_NC");
				} else if (ag.getAgentBreed().contains("Breed_NC")) {
					if (affinity < (ag.getSocialGrade() * ag.getAttributeBrand() * brandFactor))
						newAgent.setAgentBreed("Breed_C");
				}
			}
			newList.add(newAgent);
		}
		time.put(year, newList);
		fillOutput(year);
	}
	
	/**
	 * Analyze the list of agent and create an output
	 * 
	 * @param year Number of the year for which the output Hashtable will be filled
	 */
	public void fillOutput(int year) {
		Hashtable<String, Integer> output = createHashtable();
		Iterator<Agent> iterator = time.get(year).iterator();
		
		while (iterator.hasNext()) {
			Agent ag = iterator.next();
			if (ag.getAgentBreed().contains("Breed_NC")) {
				output.put("nbNC", output.get("nbNC") + 1);
				if (ag.getPreviousBreed().contains("Breed_C"))
					output.put("Clost", output.get("Clost") + 1);
			}
			else if (ag.getAgentBreed().contains("Breed_C")) {
				output.put("nbC", output.get("nbC") + 1);
				if (ag.getPreviousBreed().contains("Breed_NC")) {
					output.put("Cgained", output.get("Cgained") + 1);
					if (ag.getPostPreviousBreed().contains("Breed_C"))
						output.put("Cregained", output.get("Cregained") + 1);
				}
			}
		}
		outputs.put(year, output);
	}
 
	/**
	 * Initialize the output Hashtable
	 * 
	 * @return Returns the Hashtable initialized
	 */
	public Hashtable<String, Integer> createHashtable() {
		Hashtable<String, Integer> output = new Hashtable<String, Integer>();
		output.put("Clost", 0);
		output.put("Cregained", 0);
		output.put("Cgained", 0);
		output.put("nbNC", 0);
		output.put("nbC", 0);

		return output;
	}
	
	/*----------------------------------------------------------------*/
	/*-----------              PRINT FUNCTIONS              ----------*/
	/*----------------------------------------------------------------*/
	
	/**
	 * Execute the algorithm for 15 years and print the output
	 * 
	 * @param brandFactor The Brand Factor chosen by the user
	 */
	public void printAllExecution(float brandFactor) {
		time.put(1, listAgent);
		for (int i = 1; i <= 15; ++i)
			incrementList(i, brandFactor);
		
		printAllOutputs();
	}
	
	 
	/**
	 * Print the output for the 15 years
	 */
	public void printAllOutputs() {
		System.out.println("Breed_NC\t| Breed_C\t| Breed_C Gained\t| Breed_C Lost\t| Breed_C Regained");
		System.out.println("-------------------------------------------------------------------------------------------");
		 for (int j = 1; j <= 15; ++j) {
			 Hashtable<String, Integer> out = outputs.get(j);
		 	printOutput(out);
		 }
	}
	
	 
	/**
	 * Execute the algorithm for 1 year and print the output of this year
	 * 
	 * @param year The number of the year to execute (from 1 to 15)
	 * @param brandFactor The Band Factor value chosen by the user
	 */
	public void printOneYear(int year, float brandFactor) {
		incrementList(year, brandFactor);
		System.out.println("Breed_NC\t| Breed_C\t| Breed_C Gained\t| Breed_C Lost\t| Breed_C Regained");
		System.out.println("-------------------------------------------------------------------------------------------");
		printOutput(outputs.get(year));
	}
	
	/**
	 * Print an output
	 * 
	 * @param out The output Hashtable to print
	 */
	public void printOutput(Hashtable<String, Integer> out) {	
		System.out.print(out.get("nbNC") + "\t\t| ");
		System.out.print(out.get("nbC") + "\t\t| ");
		System.out.print(out.get("Cgained") + "\t\t\t| ");
		System.out.print(out.get("Clost") + "\t\t| ");
		System.out.print(out.get("Cregained") + "\n");
	}
}
