package test.yangliu.challenge;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import main.yangliu.challenge.Input;

public class InputTest {

	@Test
	public void ReadArgumentsWhenNoArgumentsReturnNull() {
		assertNull(Input.readArguments(null));
	}

	@Test
	public void ReadArgumentsWhenNoValidDashFlagReturnStringArrayWithNullValues() {
		String[] emptyArgs = { "" };
		String[] otherArgs = { "-t", "otherArg" };
		String[] result = Input.readArguments(emptyArgs);
		assertNull("Empty arguments result[0]", result[0]);
		assertNull("Empty arguments result[1]", result[1]);
		result = Input.readArguments(otherArgs);
		assertNull("Other arguments result[0]", result[0]);
		assertNull("Other arguments result[1]", result[1]);
	}

	@Test
	public void ReadArgumentsWhenHasValidDashFlagButNoValidValueReturnStringArrayWithNullValue() {
		String[] emptyArgsN = { "-n", "" };
		String[] noFollowArgsN = { "-n" };
		String[] invalidArgsN = { "-n", "-t" };
		String[] notNumberArgsN = { "-n", "  a.bc-d e " };

		String[] result = Input.readArguments(emptyArgsN);
		assertNull("Empty arguments of -n", result[0]);
		result = Input.readArguments(noFollowArgsN);
		assertNull("No following arguments of -n", result[0]);
		result = Input.readArguments(invalidArgsN);
		assertNull("Invalid arguments of -n", result[0]);
		result = Input.readArguments(notNumberArgsN);
		assertNull("No numbers of -n", result[0]);
		
		String[] noFollowArgD = { "-d" };
		result = Input.readArguments(noFollowArgD);
		assertNull("No following arguments of -d", result[1]);
	}
	
	@Test
	public void ReadArgumentsWhenHasValidDashNFlagWithValidValueReturnNumberInTheFirstItemOfStringArray(){
		String[] normalNumber = {"-n", "123456"};
		String[] withWhiteSpace = {"-n", " 1234 56 "};
		String[] withPunctuation = {"-n", "~!@#1234%^&*5678()-=90[];'"};
		String[] withLetters = {"-n", "eij12ixcl3456twyo"};
		
		assertEquals("Normal numbers", "123456", Input.readArguments(normalNumber)[0]);
		assertEquals("With white spaces", "123456", Input.readArguments(withWhiteSpace)[0]);
		assertEquals("With punctuations", "1234567890", Input.readArguments(withPunctuation)[0]);
		assertEquals("With Letters", "123456", Input.readArguments(withLetters)[0]);
	}
	
	@Test
	public void ReadArgumentsWhenHasValidDashDFlagWithValidValueReturnTheStringInTheSecondItemOfStringArray(){
		String[] args = {"-d", "/document/dictionary.txt"};
		
		assertEquals("Normal value", "/document/dictionary.txt", Input.readArguments(args)[1]);
	}
}
