

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
		if (!breakHashTagRecursive(hashtag, dictionary, 0)) {
            System.out.println("::error::The output for test did not match");
	    }
	}
	private static boolean breakHashTagRecursive(String hashtag, String[] dictionary, int start) {
        if (start == hashtag.length()) {
            return true;
        }
        for (int end = start + 1; end <= hashtag.length(); end++) {
            String substr = hashtag.substring(start, end);
            if (existInDictionary(substr, dictionary) && breakHashTagRecursive(hashtag, dictionary, end)) {
                System.out.println(substr);
                return true;
            }
        }
        return false;
    }
}


