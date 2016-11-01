package test.yangliu.challenge;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import main.yangliu.challenge.App;

public class AppTest {

	@Test
	public void AppStartWithCorrectDictionaryAndPhoneNumbers() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		new App(new String[] { "-d", "testAppDictionary.txt", "-n", "testAppPhoneNumber.txt" });
		assertEquals("Read arguments...\n" + "\n" + "Read dictionary file...\n" + "Insert words into dictionary...\n"
				+ "Inserted 5 words.\n" + "\n" + "Read phone numbers file...\n"
				+ "Read numbers: [7349472, 7, 38, 7327, 277537327, 364, 27753364364, 277453, 1277534, 4727753, 927753473270, 93644, 97327036443643, 27327]\n"
				+ "\n" + "Results:\n" + "7349472:\n" + "\tNo results found\n" + "7:\n" + "\t7\n" + "38:\n"
				+ "\tNo results found\n" + "7327:\n" + "\tPEAR\n" + "277537327:\n" + "\tAPPLE-PEAR\n" + "364:\n"
				+ "\tDOG\n" + "\tFOG\n" + "27753364364:\n" + "\tAPPLE-DOG-DOG\n" + "\tAPPLE-DOG-FOG\n"
				+ "\tAPPLE-FOG-DOG\n" + "\tAPPLE-FOG-FOG\n" + "277453:\n" + "\tNo results found\n" + "1277534:\n"
				+ "\t1-APPLE-4\n" + "4727753:\n" + "\tNo results found\n" + "927753473270:\n" + "\t9-APPLE-4-PEAR-0\n"
				+ "93644:\n" + "\t9-DOG-4\n" + "\t9-FOG-4\n" + "97327036443643:\n" + "\t9-PEAR-0-DOG-4-DOG-3\n"
				+ "\t9-PEAR-0-DOG-4-FOG-3\n" + "\t9-PEAR-0-FOG-4-DOG-3\n" + "\t9-PEAR-0-FOG-4-FOG-3\n" + "27327:\n"
				+ "\t2-PEAR\n" + "\tA-PEAR\n", outContent.toString());
		System.setOut(null);
	}
}