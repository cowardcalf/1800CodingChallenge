package test.yangliu.challenge;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import main.yangliu.challenge.Input;

public class InputTest {

	@Test
	public void whenNoArgumentsReturnNull() {
		assertNull(Input.readPhoneNumberFromArgument(null));
	}

	@Test
	public void whenNoDashNArgumentsReturnNull() {
		String[] emptyArgs = {""};
		String[] otherArgs = {"-t", "otherArg"};
		assertNull("empty arguments", Input.readPhoneNumberFromArgument(emptyArgs));
		assertNull("have other arguments", Input.readPhoneNumberFromArgument(otherArgs));
	}
	
	@Test
	public void whenHasDashNArgumentButNoNumberAfterThatThenThrowInvalidParameterException() {
		String[] emptyArgs = {"-n", ""};
		String[] noFollowArgs = {"-n"};
		String[] invalidArgs = {"-n", "-t"};
		String[] notNumberArgs = {"-n", "abcde"};
		
		RuntimeException exception = null;
		try{
			Input.readPhoneNumberFromArgument(emptyArgs);
		}catch(RuntimeException e){
			exception = e;
		}
		assertNotNull("Empty argument, exception was not thrown", exception);
		assertEquals("Empty parameter", "Invalid Parameter of -n", exception.getMessage());
		try{
			Input.readPhoneNumberFromArgument(noFollowArgs);
		}catch(RuntimeException e){
			exception = e;
		}
		assertNotNull("No following argument, exception was not thrown", exception);
		assertEquals("No following argument", "Invalid Parameter of -n", exception.getMessage());
		try{
			Input.readPhoneNumberFromArgument(invalidArgs);
		}catch(RuntimeException e){
			exception = e;
		}
		assertNotNull("Invalid argument, exception was not thrown", exception);
		assertEquals("Invalid argument", "Invalid Parameter of -n", exception.getMessage());
		try{
			Input.readPhoneNumberFromArgument(notNumberArgs);
		}catch(RuntimeException e){
			exception = e;
		}
		assertNotNull("Not number argument, exception was not thrown", exception);
		assertEquals("Not number argument", "Invalid Parameter of -n", exception.getMessage());
	}
}
