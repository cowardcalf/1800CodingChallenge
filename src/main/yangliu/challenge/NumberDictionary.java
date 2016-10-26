package main.yangliu.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A map of numbers and words. Initilised by a or more dictionary of words
 * Numbers are converted by word according to letters on a phone number pad.
 * 
 * @author Yang Liu
 *
 */
public class NumberDictionary {
	private Map<Character, Character> numberEncodingMap;
	private Map<String, List<String>> numberWordMap;

	public NumberDictionary() {
		numberEncodingMap = initNumberEncodingMap();
		numberWordMap = new HashMap<String, List<String>>();
	}

	private Map<Character, Character> initNumberEncodingMap() {
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('a', '2');
		map.put('b', '2');
		map.put('c', '2');
		map.put('d', '3');
		map.put('e', '3');
		map.put('f', '3');
		map.put('g', '4');
		map.put('h', '4');
		map.put('i', '4');
		map.put('j', '5');
		map.put('k', '5');
		map.put('l', '5');
		map.put('m', '6');
		map.put('n', '6');
		map.put('o', '6');
		map.put('p', '7');
		map.put('q', '7');
		map.put('r', '7');
		map.put('s', '7');
		map.put('t', '8');
		map.put('u', '8');
		map.put('v', '8');
		map.put('w', '9');
		map.put('x', '9');
		map.put('y', '9');
		map.put('z', '9');
		return map;
	}

	public int learnDictionary(List<String> wordList) {
		int numberOfWords = 0;
		if (wordList != null && wordList.size() > 0) {
			for (String word : wordList) {
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
		return numberOfWords;
	}
}
