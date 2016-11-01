package main.yangliu.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A map of numbers and words. Initialized by one or more dictionary of words
 * Numbers are mapping to words according to letters on a phone number pad.
 * 
 * @author Yang Liu
 *
 */
public class NumberDictionary {
	/**
	 * The map of single letter and encoding number
	 * Referring to a phone number pad.
	 */
	private Map<Character, Character> numberEncodingMap;
	
	/**
	 * The map of numbers and their converted words.
	 * It can grow by learning a words dictionary.
	 */
	private Map<String, List<String>> numberWordMap;

	/**
	 * Constructor. Initialize the two maps.
	 */
	public NumberDictionary() {
		numberEncodingMap = initNumberEncodingMap();
		numberWordMap = new HashMap<String, List<String>>();
	}

	/**
	 * Initialize number encoding map.
	 * @return new generated map.
	 */
	private Map<Character, Character> initNumberEncodingMap() {
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('A', '2');
		map.put('B', '2');
		map.put('C', '2');
		map.put('D', '3');
		map.put('E', '3');
		map.put('F', '3');
		map.put('G', '4');
		map.put('H', '4');
		map.put('I', '4');
		map.put('J', '5');
		map.put('K', '5');
		map.put('L', '5');
		map.put('M', '6');
		map.put('N', '6');
		map.put('O', '6');
		map.put('P', '7');
		map.put('Q', '7');
		map.put('R', '7');
		map.put('S', '7');
		map.put('T', '8');
		map.put('U', '8');
		map.put('V', '8');
		map.put('W', '9');
		map.put('X', '9');
		map.put('Y', '9');
		map.put('Z', '9');
		return map;
	}

	/**
	 * Add words to the number-words dictionary
	 * It will convert each of the word to numbers then
	 * put the numbers as a key into the map and the relevant
	 * words in a list under that key as the value.
	 * @param wordList the list of words to add
	 * @return how many words be added
	 */
	public int learnDictionary(List<String> wordList) {
		int numberOfWords = 0;
		if (wordList != null && wordList.size() > 0) {
			for (String word : wordList) {
				if(word != null && word.length() > 0){
					word = word.replaceAll("[^a-zA-Z]", ""); // leave letters only
					word = word.toUpperCase();
					String number = ""; // mapping numbers of the word
					for (char ch : word.toCharArray()) {
						number += numberEncodingMap.get(ch);
					}
					// Try get the list from the key of number
					List<String> tempList = numberWordMap.get(number);
					// create and add a new list if it's not existed
					if (tempList == null) {
						tempList = new ArrayList<String>();
						numberWordMap.put(number, tempList);
					}
					// Add if no duplicate word
					if (!tempList.contains(word)) {
						tempList.add(word);
						numberOfWords++; // one word added
					}
				}
			}
		}
		return numberOfWords;
	}
	
	/**
	 * It is the wrapper method of finding possible words combinations from 
	 * a series of number.
	 * It does null check and trim the input numbers being numbers only.
	 * Then it call the inner recursive method to find the results.
	 * @param numbers numbers to be converted to words
	 * @return the words combinations of the input number
	 */
	public List<String> findPossibleWordsFromNumbers(String numbers){
		List<String> resultList = null;
		if(numbers != null && numbers.length() > 0){
			resultList = findPossibleWordsInner(numbers.replaceAll("[^0-9]", ""), false);
		}
		return resultList;
	}
	
	/**
	 * The inner method of finding possible words from a number, 
	 * it is a recursive function with two parameters.
	 * @param numbers the string of numbers to find
	 * @param hasSingleNumber indicates if there is a single number left when calling this function
	 * @return list of possible words combinations
	 */
	private List<String> findPossibleWordsInner(String numbers, boolean hasSingleNumber){
		List<String> resultList = new ArrayList<String>();
		if(numbers != null && numbers.length() > 0){
			// Case of single number as header
			if(!hasSingleNumber){
				String onePossible = numbers.substring(0, 1); // leave header as the number
				if(numbers.length() > 1){ // try find tail combinations
					List<String> tailResults = findPossibleWordsInner(numbers.substring(1, numbers.length()), true);
					if(tailResults != null && tailResults.size() > 0){ // find possible combinations in the tail
						// add each tails to header
						for(String tailStr : tailResults){
							resultList.add(onePossible + "-" + tailStr);
						}
					}
				}
				else // no more numbers
					resultList.add(onePossible);
				
			}
			// Cases of not single number
			for(int headerLength = 1; headerLength <= numbers.length(); headerLength ++){
				List<String> possibles = numberWordMap.get(numbers.substring(0, headerLength));
				if(possibles != null && possibles.size() > 0){ // find this header of numbers in dictionary
					if(headerLength < numbers.length()){// has tail
						List<String> tailResults = findPossibleWordsInner(numbers.substring(headerLength, numbers.length()), false);
						if(tailResults != null && tailResults.size() > 0){ // find possible combinations in the tail
							for(String onePossible : possibles){ // loop headers
								// add each tails to header
								for(String tailStr : tailResults){ // loop tails
									resultList.add(onePossible + "-" + tailStr); // add header + tail combinations
								}
							}
						}
					}else // no tail
						for(String onePossible : possibles) // add all headers
							resultList.add(onePossible);
				}
			}
		}
		return resultList;
	}

	public Map<Character, Character> getNumberEncodingMap() {
		return numberEncodingMap;
	}

	public void setNumberEncodingMap(Map<Character, Character> numberEncodingMap) {
		this.numberEncodingMap = numberEncodingMap;
	}

	public Map<String, List<String>> getNumberWordMap() {
		return numberWordMap;
	}

	public void setNumberWordMap(Map<String, List<String>> numberWordMap) {
		this.numberWordMap = numberWordMap;
	}
}
