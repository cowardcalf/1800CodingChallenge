package main.yangliu.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The input utilities.
 * It has methods of reading running arguments, reading dictionary file and
 * reading phone numbers file 
 * @author Yang Liu
 *
 */
public class Input {

	/**
	 * Read arguments in console input style
	 * @param args accept ["-n", phone numbers file path, "-d", dictionary file path]
	 * @return [phone numbers file path, dictionary file path]
	 */
	public static String[] readArguments(String[] args) {
		String[] result = null;
		if (args != null) {
			result = new String[] { null, null };
			boolean hasN = false; // don't check -n if already found
			boolean hasD = false; // don't check -d if already found
			for (int i = 0; i < args.length; i++) {
				String s = args[i];
				// have parameter -n or -N
				if (!hasN && (s.equals("-n") || s.equals("-N"))) {
					hasN = true;
					if (i + 1 < args.length) {
						String phonePath = args[i + 1];
						result[0] = phonePath;
					}
					// have parameter -d or -D
				} else if (!hasD && (s.equals("-d") || s.equals("-D"))) {
					hasD = true;
					if (i + 1 < args.length) {
						String dicPath = args[i + 1];
						result[1] = dicPath;
					}
				}
			}
		}
		return result;
	}

	/**
	 * The general method of reading input file.
	 * It reads file line by line and do a cleaning of unnecessary syntaxes
	 * @param fileName the path and file name
	 * @param replaceRegx regular expression of unnecessary syntaxes
	 * @return
	 */
	public static List<String> readInputFile(String fileName, String replaceRegx){
		List<String> wordsList = null;
		if (fileName != null && fileName.length() > 0) {
			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

				wordsList = stream.map(s -> s.replaceAll(replaceRegx, "").toLowerCase()).collect(Collectors.toList());

			} catch (IOException e) {
//				e.printStackTrace();
			}

		}
		return wordsList;
	}
	
	/**
	 * Reads dictionary file. Reads letters only.
	 * Uses readInputFile method
	 * @param fileName the path and file name
	 * @return
	 */
	public static List<String> readDictionaryFile(String fileName) {
		return readInputFile(fileName, "[^a-zA-Z]");
	}
	
	/**
	 * Reads dictionary file. Reads numbers only.
	 * Uses readInputFile method
	 * @param fileName the path and file name
	 * @return
	 */
	public static List<String> readPhoneNumberFile(String fileName) {
		return readInputFile(fileName, "[^0-9]");
	}
}
