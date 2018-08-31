public class LetterBag {
	private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private static int[] letterFrequencies;
	public static void main(String[] args) {
		LetterBag bag = new LetterBag();
		bag.add("a");
		bag.add("a");
		bag.add("a");
		bag.add("a");
		bag.add("a");
		bag.add("a");
		bag.add("d");
		bag.add("d");
		bag.add("b");
		bag.add("y");
		bag.add("g");
		bag.add("b");
		bag.add("b");
		bag.add("x");
		System.out.println(bag.getTotalWords()); //returns 14
		System.out.println(bag.getNumUniqueWords());//returns 6
		System.out.println(bag.getNumOccurances("b")); // returns 3
		System.out.println(bag.getMostFrequent()); //returns a
	}
	public LetterBag() {
		letterFrequencies = new int[26];
	}
	/***
	 * adds a letter to the bag
	 * @param letter the letter to be added
	 */
	public void add(String letter) {
		String lower = letter.toLowerCase();
		int index = getIndexForLetter(lower);
		letterFrequencies[index]++;
	}
	private static int getIndexForLetter(String lower) {
		return alphabet.indexOf(lower);
	}
	/***
	 * returns the total number of words in the bag
	 * @return total number of words in bag
	 */
	public static int getTotalWords() {
		int counter = 0;
		for(int i = 0; i < 26; i++) {
			counter = counter + letterFrequencies[i];
		}
		return counter;
	}
	/***
	 * returns the number of unique letters in the bag
	 * @return total number of unique letters in bag
	 */
	public static int getNumUniqueWords() {
		int counter = 0;
		for(int i = 0; i < 26; i++) {
			if(letterFrequencies[i] > 0) {
				counter++;
			}
		}
		return counter;
	}
	/***
	 * returns the number of appearances of a letter in the bag
	 * @param letter the letter that is being checked
	 * @return the number of occurances of the letter
	 */
	public static int getNumOccurances(String letter){
		String lower = letter.toLowerCase();
		int index = getIndexForLetter(lower);
		return letterFrequencies[index];
	}
	/***
	 * returns the most frequent letter in the bag
	 * @return the most frequent letter
	 */
	public static String getMostFrequent(){
		int highest = 0;
		String highestLetter = null;
		for(int i = 0; i < 26; i++) {
			if (letterFrequencies[i]> highest) {
				highest = letterFrequencies[i];
				highestLetter = alphabet.substring(i, i+1);
			}
		}
		return highestLetter;
	}

}


