
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.isEmpty()) return word2.length();
		if (word2.isEmpty()) return word1.length();
		int cost = (word1.charAt(0) == word2.charAt(0)) ? 0 : 1;
		
		return Math.min(Math.min(levenshtein(tail(word1), word2) + 1, levenshtein(word1, tail(word2)) +1), levenshtein(tail(word1), tail(word2)) + cost); 
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		int index = 0;
		while (!in.isEmpty() && index < dictionary.length) {
			dictionary[index] = in.readString().toLowerCase();
			index++;
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String closestMatch = word;
		int minDistance = Integer.MAX_VALUE;
		for (String dictWord : dictionary){
			int distance = levenshtein(word, dictWord);
			if (distance < minDistance) {
				minDistance = distance;
				closestMatch = dictWord;
			}
		}
		return minDistance <= threshold ? closestMatch : word;
	}

}
