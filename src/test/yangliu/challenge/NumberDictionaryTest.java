package test.yangliu.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

import main.yangliu.challenge.NumberDictionary;

public class NumberDictionaryTest {
	@Test
	public void createNewNumberDictionaryShouldHaveCorrectNumberEncodingDictionary() {
		NumberDictionary nd = new NumberDictionary();
		Map<Character, Character> map = nd.getNumberEncodingMap();
		assertEquals((char) '2', (char) map.get('A'));
		assertEquals((char) '2', (char) map.get('B'));
		assertEquals((char) '2', (char) map.get('C'));
		assertEquals((char) '3', (char) map.get('D'));
		assertEquals((char) '3', (char) map.get('E'));
		assertEquals((char) '3', (char) map.get('F'));
		assertEquals((char) '4', (char) map.get('G'));
		assertEquals((char) '4', (char) map.get('H'));
		assertEquals((char) '4', (char) map.get('I'));
		assertEquals((char) '5', (char) map.get('J'));
		assertEquals((char) '5', (char) map.get('K'));
		assertEquals((char) '5', (char) map.get('L'));
		assertEquals((char) '6', (char) map.get('M'));
		assertEquals((char) '6', (char) map.get('N'));
		assertEquals((char) '6', (char) map.get('O'));
		assertEquals((char) '7', (char) map.get('P'));
		assertEquals((char) '7', (char) map.get('Q'));
		assertEquals((char) '7', (char) map.get('R'));
		assertEquals((char) '7', (char) map.get('S'));
		assertEquals((char) '8', (char) map.get('T'));
		assertEquals((char) '8', (char) map.get('U'));
		assertEquals((char) '8', (char) map.get('V'));
		assertEquals((char) '9', (char) map.get('W'));
		assertEquals((char) '9', (char) map.get('X'));
		assertEquals((char) '9', (char) map.get('Y'));
		assertEquals((char) '9', (char) map.get('Z'));
	}

	@Test
	public void learnDictionaryHasNullOrEmptyWordsListReturnZero() {
		NumberDictionary nd = new NumberDictionary();
		assertEquals(nd.learnDictionary(null), 0);
		assertEquals(nd.learnDictionary(new ArrayList<String>()), 0);
	}

	@Test
	public void learnDictionaryGenerateCorrectNumberMappings() {
		NumberDictionary nd = new NumberDictionary();
		List<String> inputList = Arrays.asList("apple", "pear", "dog", "fog", "apple",
				"!@#$%^930457num  0ber#$%^&^&:<?  389", "", null);
		assertEquals("learn 5 correct words", nd.learnDictionary(inputList), 5);
		Map<String, List<String>> pwm = nd.getNumberWordMap();
		assertEquals(Arrays.asList("APPLE"), pwm.get("27753"));
		assertEquals(Arrays.asList("PEAR"), pwm.get("7327"));
		assertEquals(Arrays.asList("DOG", "FOG"), pwm.get("364"));
		assertEquals(Arrays.asList("NUMBER"), pwm.get("686237"));
	}

	@Test
	public void findPossibleWordsFromNumbers() {
		NumberDictionary nd = new NumberDictionary();
		List<String> inputList = Arrays.asList("apple", "pear", "dog", "fog", "a");
		nd.learnDictionary(inputList);
		assertNull("null input", nd.findPossibleWordsFromNumbers(null));
		assertNull("empty input", nd.findPossibleWordsFromNumbers(""));
		assertEquals("no result", Arrays.asList(), nd.findPossibleWordsFromNumbers("7349472"));
		assertEquals("single Number", Arrays.asList("7"), nd.findPossibleWordsFromNumbers("7"));
		assertEquals("two single numbers", Arrays.asList(), nd.findPossibleWordsFromNumbers("38"));
		assertEquals("one possible words", Arrays.asList("PEAR"), nd.findPossibleWordsFromNumbers("7327"));
		assertEquals("two possible words", Arrays.asList("APPLE-PEAR"), nd.findPossibleWordsFromNumbers("277537327"));
		assertEquals("two words in same pattern", Arrays.asList("DOG", "FOG"), nd.findPossibleWordsFromNumbers("364"));
		assertEquals("Three possible words with two words in same pattern",
				Arrays.asList("APPLE-DOG-DOG", "APPLE-DOG-FOG", "APPLE-FOG-DOG", "APPLE-FOG-FOG"),
				nd.findPossibleWordsFromNumbers("27753364364"));
		assertEquals("Single number in the middle of a possible word", Arrays.asList(),
				nd.findPossibleWordsFromNumbers("277453"));
		assertEquals("single numbers with one word", Arrays.asList("1-APPLE-4"),
				nd.findPossibleWordsFromNumbers("1277534"));
		assertEquals("two single numbers with one word", Arrays.asList(), nd.findPossibleWordsFromNumbers("4727753"));
		assertEquals("single numbers with two words", Arrays.asList("9-APPLE-4-PEAR-0"),
				nd.findPossibleWordsFromNumbers("927753473270"));
		assertEquals("single numbers with two words in same pattern", Arrays.asList("9-DOG-4", "9-FOG-4"),
				nd.findPossibleWordsFromNumbers("93644"));
		assertEquals(
				"single numbers with three words and two words in same pattern", Arrays.asList("9-PEAR-0-DOG-4-DOG-3",
						"9-PEAR-0-DOG-4-FOG-3", "9-PEAR-0-FOG-4-DOG-3", "9-PEAR-0-FOG-4-FOG-3"),
				nd.findPossibleWordsFromNumbers("97327036443643"));
		assertEquals("with possible one letter word", Arrays.asList("2-PEAR", "A-PEAR"), nd.findPossibleWordsFromNumbers("27327"));
	}
}
