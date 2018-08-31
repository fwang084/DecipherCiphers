import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class CipherClass {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:" + '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;
	
	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of movement.
	 * @param alphabet the alphabet to be used for the encryption
	 * @param plainText the plain text to be encrypted.
	 * @param shiftAmount the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plain, int shiftAmount, String alphabet) {
		String newString = "";
		for(int i = 0; i < plain.length(); i++) {
			String letter = plain.substring(i, i+1);
			int index = alphabet.indexOf(letter);
			index = index + shiftAmount;
			while(index < 0) {             //makes the index positive
				index = index + alphabet.length();
			}
			int position = index % alphabet.length(); //takes the remainder
			String newLetter = alphabet.substring(position, position + 1);
			newString = newString + newLetter;
		}
		return newString;
	}


	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the rotation cipher.
	 * @param alphabet the alphabet to be used for the encryption
	 * @param cipherText the encrypted text.
	 * @param shiftAmount the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(int shift, String cipher, String alphabet) {
		String newString = "";
		for(int i = 0; i < cipher.length(); i++) {
			String letter = cipher.substring(i, i+1);
			int index = alphabet.indexOf(letter);
			index = index - shift;
			while(index < 0) {             //makes the index positive
				index = index + alphabet.length();
			}
			int position = index % alphabet.length(); //takes the remainder
			String newLetter = alphabet.substring(position, position + 1);
			newString = newString + newLetter;
		}
		return newString;
	}
	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the String code
	 * @param alphabet the alphabet to be used for the encryption
	 * @param plainText the plain text to be encrypted.
	 * @param code the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plainText, String code, String alphabet) {
		int[] shifts = findShifts(code, alphabet);
		String newString = "";
		for(int i = 0; i < plainText.length(); i++) {
			int modValue = i % shifts.length;
			int shiftValue = shifts[modValue];
			String letter = plainText.substring(i, i+1);
			int index = alphabet.indexOf(letter);
			index = index + shiftValue;
			while(index < 0) {             //makes the index positive
				index = index + alphabet.length();
			}
			int position = index % alphabet.length(); //takes the remainder
			String newLetter = alphabet.substring(position, position + 1);
			newString = newString + newLetter;
		}
		return newString;
	}


	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * @param alphabet the alphabet to be used for the encryption
	 * @param cipherText the encrypted text.
	 * @param code the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipherText, String code, String alphabet) {
		int[] shifts = findShifts(code, alphabet);
		String newString = "";
		for(int i = 0; i < cipherText.length(); i++) {
			int modValue = i % shifts.length;
			int shiftValue = shifts[modValue];
			String letter = cipherText.substring(i, i+1);
			int index = alphabet.indexOf(letter);
			index = index - shiftValue;
			while(index < 0) {             //makes the index positive
				index = index + alphabet.length();
			}
			int position = index % alphabet.length(); //takes the remainder
			String newLetter = alphabet.substring(position, position + 1);
			newString = newString + newLetter;
		}
		return newString;
	}
public static int[] findShifts(String code, String alphabet) {
		int[] shifts = new int[code.length()];
		for(int i = 0; i < code.length(); i++){
			String letter = code.substring(i, i+1);
			int alphabetPosition = alphabet.indexOf(letter);
			shifts[i] = alphabetPosition;
		}
		return shifts;
	}
	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * @param alphabet the alphabet to be used 
	 * @param cipher the cipher to be cracked
	 * @param shift the amount to be shifted
	 * @return returns the decrypted cipher
	 */
	public String rotationCipherCrack(String cipher, String alphabet, int shift) {
		String newString = "";
		for(int i = 0; i < alphabet.length(); i++) {
			for(int j = 0; j < cipher.length(); j++) {
				String letter = cipher.substring(j, j+1);
				int index = alphabet.indexOf(letter);
				index = index - shift;
				while(index < 0) {             //makes the index positive
					index = index + alphabet.length();
				}
				int position = index % alphabet.length(); //takes the remainder
				String newLetter = alphabet.substring(position, position + 1);
				newString = newString + newLetter;
			}
		}	
		return newString;
	}
	/**
	 * Returns the result of cracking the vigenere cipher with a three-letter code.
	 * @param alphabet the alphabet to be used 
	 * @param cipher the cipher to be cracked
	 * @return returns the decrypted cipher
	 */
	public String vigenereCipherCrackThreeLetter(String cipher, String alphabet) {
		String code = "";
		for(int i = 0; i < 3; i++) {
			String temp = getLettersOut(cipher, i, 3);
			code = code + findCodeLetter(temp, alphabet);
		}
		return vigenereCipherDecrypt(cipher, code, alphabet);
	}
	/**
	 * Returns the result of cracking a generic vigenere cipher.
	 * @param alphabet the alphabet to be used 
	 * @param cipher the cipher to be cracked
	 * @return returns the decrypted cipher
	 */
	public String vigenereCipherCrack(String cipher, String alphabet) {
		double highestValue = 0;
		double currentValue = 0;
		double count = 0;
		String message;
		String[] words;
		String solution;
		for(int i = 0; i  < 26; i++) {
			String code = "";
			for(int j = 0; j < i; j++) {
				String temp = getLettersOut(cipher, j, i);
				code = code + findCodeLetter(temp, alphabet);
			}
			message = vigenereCipherDecrypt(cipher, code, alphabet);
			words = getWords(message);
count = 0;
			for(int j = 0; j <  words.length; j++) {
				if (d1.isWord(words[i])) count++;
			}
			currentValue = count / words.length;
			if(currentValue > highestValue){
				highestValue = currentValue;
				solution = message;
			}
		}
	}
public static String getLettersOut(String encrypted, int index, int length) {
		String letters = "";
		for(int i = index; i < encrypted.length(); i = i+length) {
			letters = letters + encrypted.substring(i, i+1);
		}
		return letters;
	}
	public static String findCodeLetter(String temp, String alphabet) {
		for(int i = 0; i < alphabet.length(); i++) {
			String decoded = rotationCipherDecrypt(i, alphabet, temp);
			LetterBag bag = new LetterBag();
			for(int x = 0; x < decoded.length(); x++) {
				((LetterBag) bag).add(decoded.substring(x, x+1));
			}
			if (bag.getMostFrequent().equals("")){
				return alphabet.substring(i, i+1);
			}
		}
		return null;
	}


	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *          The plaintext string you wish to remove illegal characters from
	 * @param alphabet
	 *          A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { 			// loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) 	// get index of char
				b.append(plaintext.charAt(i)); 									// if it exists, keep it
			else
																												// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
																																				// a
																																				// message
						+ "\"");
		}
		return b.toString();
	}


	/**
	 * returns true if plaintext is valid English.
	 * 
	 * @param plaintext
	 *          the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	private static boolean isEnglish(String plaintext) {
		String[] words = getWords(plaintext);	
		Dictionary d1 = Dictionary.buildDictionary("C:\\Users\\Public\\CipherBlankTemplate\\words ");
		double count = 0;
		for(int i = 0; i <  words.length; i++) {
		if (d1.isWord(words[i])) count++;
		}
		if (count/words.length > 0.3){
			return true;
		}
		return false;
	}
public static String[] getWords(String str) {
		String message = str.trim();
		ArrayList<Integer> spaceLocations = new ArrayList<Integer>();
		int numOfWords = 1;
		for(int i = 0; i < message.length(); i++) {
			if(message.substring(i, i+1).equals(" ")){
				spaceLocations.add(i);
				if(!message.substring(i-1, i).equals("")) {
					numOfWords++;
				}
			}
		}	
		String[] words = new String[numOfWords];
		for(int i = 0; i < numOfWords; i++) {
			words[i] = "";
		}
		int position = 0;
		int loopTo = 0;
		int wordNumber = 0;
		while(words[words.length-2].equals(" ")){
			loopTo = message.indexOf(" ", position);
			for(int i = position; i < loopTo; i++) {
				words[wordNumber] = words[wordNumber] + message.substring(i, i+1);
			}
			wordNumber++;
			position = loopTo;
			while(message.substring(position, position+1).equals(" ")){
				position++;
			}
		}
		for(int i = position; i < message.length(); i++) {
			words[words.length-1] = words[words.length-1] + message.substring(i, i+1);
		}
		return words;
	}


}


