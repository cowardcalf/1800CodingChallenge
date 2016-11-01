package main.yangliu.challenge;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The main class of the 1800 coding challenge.
 * It initializes phone number list and dictionary of phone numbers
 * recording to the arguments when run this program.
 * After that, it will find all possible words combination of each numbers and
 * output them to the console.
 * It will use default dictionary file if no dictionary file provided.
 * It will handle manually number input via standard input if there
 * is no phone numbers file provided.
 * By receiving a valid number input, it will show words combinations
 * to the console.
 * 
 * @author Yang Liu
 *
 */
public class App {

	private List<String> phoneNumberList;
	private NumberDictionary numberDictionary;

	/**
	 * Constructor, start processes immediately
	 * @param args the start arguments
	 */
	public App(String[] args) {
		start(args);
	}

	/**
	 * Start function
	 * @param args the start arguments
	 */
	private void start(String args[]) {
		System.out.println("Read arguments...");
		System.out.println();
		String[] inputPaths = Input.readArguments(args);
		boolean needDefault = true;
		List<String> wordsList = null;
		if (inputPaths[1] != null) { // Has path provided
			System.out.println("Read dictionary file...");
			wordsList = Input.readDictionaryFile(inputPaths[1]);
			// if it has words return, don't use default dictionary
			if (wordsList != null && wordsList.size() != 0)
				needDefault = false;
		} else { // No path
			System.out.println("No dictionary file provided.");
		}
		if (needDefault) {
			System.out.println("Use default dictionary.");
			wordsList = Input.readDictionaryFile("myDictionary.txt");
		}
		System.out.println("Insert words into dictionary...");
		System.out.println("Inserted " + initDictionay(wordsList) + " words.");
		System.out.println();
		
		if (inputPaths[0] != null) { // Read phone numbers
			System.out.println("Read phone numbers file...");
			phoneNumberList = Input.readPhoneNumberFile(inputPaths[0]);
			if (phoneNumberList == null || phoneNumberList.size() == 0) {
				System.out.println("Cannot find any phone number from the file.");
			} else {
				System.out.println("Read numbers: " + phoneNumberList);
			}
		}else
			System.out.println("No phone numbers file provided.");
		
		System.out.println();
		
		// didn't get any phone number
		// try asking user input manually
		if (phoneNumberList == null || phoneNumberList.size() == 0) {
			handleInputNumberViaStdIn();
		}else{
			System.out.println("Results:");
			outputResultToConsole(phoneNumberList);
		}
	}

	/**
	 * Initialize the number dictionary.
	 * Use it after reading words from a dictionary file.
	 * Create a new number dictionary instance.
	 * Insert words into the dictionary.
	 * @param wordsList words to insert into the dictionary
	 * @return
	 */
	private int initDictionay(List<String> wordsList) {
		numberDictionary = new NumberDictionary();
		return numberDictionary.learnDictionary(wordsList);
	}
	
	/**
	 * If no phone numbers file provided, use this method
	 * to handle the standard input of phone numbers.
	 */
	private void handleInputNumberViaStdIn(){
		Scanner input = new Scanner(System.in);
		boolean finished = false;
		while(!finished){
			System.out.print("Please input a number or quit:  ");
			String line = input.nextLine().trim(); // get input
			if(line.equals("quit")) // if quit then set finished
				finished = true;
			else{
				line = line.replaceAll("[^0-9]", ""); // remove all non-numbers
				if(line.length() > 0){ // output
					System.out.println("Finding result of number: " + line + "...");
					outputResultToConsole(Arrays.asList(line));
				}else{ // invalid input, ask user input again
					System.out.print("Your input is incorrect. Please input a number or quit: ");
				}
			}
		}
		input.close();
	}
	
	/**
	 * Find possible words combinations of the numbers in the list and
	 * output results to the console.
	 * @param numberList List of numbers to convert to words
	 * @return
	 */
	private int outputResultToConsole(List<String> numberList){
		int numberOfFindResult = 0;
		if(numberList != null && numberList.size() > 0){
			for(String number : numberList){
				System.out.println(number + ":");
				List<String> resultList = numberDictionary.findPossibleWordsFromNumbers(number);
				if(resultList != null && resultList.size() > 0){ // the number has results
					for(String result: resultList){
						System.out.println("\t" + result);
					}
					numberOfFindResult++;
				}else{
					System.out.println("\tNo results found");
				}
			}
		}
		return numberOfFindResult;
	}
}
