

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0].toLowerCase();
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
        int index = 0;
		while (!in.isEmpty() && index < dictionary.length) {
			dictionary[index++] = in.readString().toLowerCase();
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for (String dictWord : dictionary) {
			if (dictWord != null && dictWord.equals(word)) {
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
        breakHashTagRecursive(hashtag, dictionary, "");
	}
 
    private static void breakHashTagRecursive(String hashtag, String[] dictionary, String prefix) {
		if (hashtag.isEmpty()) {
			if (!prefix.isEmpty()) {
				System.out.println(prefix);
			}
			return;
		}
		for (int i = 0; i <= hashtag.length(); i++){
			String substr = hashtag.substring(0, i);
			if (existInDictionary(substr, dictionary)) {
				String newPrefix = prefix.isEmpty() ? substr : prefix + " " + substr;
				breakHashTagRecursive(hashtag.substring(i), dictionary, newPrefix);
			}
		}
	}
		
        
}


