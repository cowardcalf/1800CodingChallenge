package test.yangliu.challenge;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.yangliu.challenge.Input;

public class InputTest {

	@Test
	public void readArgumentsWhenNoValidDashFlagReturnStringArrayWithNullValues() {
		String[] emptyArgs = { "" };
		String[] otherArgs = { "-t", "otherArg" };
		
		String[] result = Input.readArguments(null);
		assertNull(result);
		
		result = Input.readArguments(emptyArgs);
		assertNull("Empty arguments result[0]", result[0]);
		assertNull("Empty arguments result[1]", result[1]);
		
		result = Input.readArguments(otherArgs);
		assertNull("Other arguments result[0]", result[0]);
		assertNull("Other arguments result[1]", result[1]);
	}

	@Test
	public void readArgumentsWhenHasValidDashFlagButNoValidValueReturnStringArrayWithNullValue() {
		String[] result;
		String[] noFollowArgsN = { "-n" };
		result = Input.readArguments(noFollowArgsN);
		assertNull("No following arguments of -n", result[0]);
		String[] noFollowArgD = { "-d" };
		result = Input.readArguments(noFollowArgD);
		assertNull("No following arguments of -d", result[1]);
	}
	
	@Test
	public void readArgumentsWhenHasValidDashDFlagWithValidValueReturnTheStringInTheSecondItemOfStringArray() {
		String[] args = { "-d", "/document/dictionary.txt" };

		assertEquals("Normal value", "/document/dictionary.txt", Input.readArguments(args)[1]);
	}

	@Test
	public void readDictionaryFileWhenHasInvalidFilePathReturnNull() {
		assertNull("Null path", Input.readInputFile(null, ""));
		assertNull("Empty path", Input.readInputFile("", ""));
		assertNull("Invalid path", Input.readInputFile("!@#abc$84=", ""));
		assertNull("Not existed file or incorrect path", Input.readInputFile("noFile.txt", ""));
	}

	@Test
	public void readInputFileWhenHasCorrectFilePathAndReplaceRegxReturnListOfReplacedStringsInLowercases() {
		List<String> expected = Arrays.asList("apple", "boy", "coffee", "david");

		assertEquals(expected, Input.readInputFile("testDictionary.txt", "[^a-zA-Z]"));
	}
	
	@Test
	public void readDictionaryFileWhenHasCorrectParametersReturnListOfWordsInLowercases() {
		List<String> expected = Arrays.asList("apple", "boy", "coffee", "david");

		assertEquals(expected, Input.readDictionaryFile("testDictionary.txt"));
		
		expected = Arrays.asList("punctuations", "whitespace", "number");

		assertEquals(expected, Input.readDictionaryFile("testDictionaryWithOtherSymbols.txt"));
	}
	
	@Test
	public void readPhoneNumberFileWhenHasCorrectParametersReturnListOfNumbersInLowercases() {
		List<String> expected = Arrays.asList("123", "456", "789", "012");

		assertEquals(expected, Input.readPhoneNumberFile("testPhoneNumber.txt"));
	}
}
