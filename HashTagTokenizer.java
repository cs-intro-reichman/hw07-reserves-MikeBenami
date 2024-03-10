

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

	public static boolean breakHashTag(String hashtag, String[] dictionary) {
		return breakHashTagRecursive(hashtag, dictionary, 0,"");
	}
	private static boolean breakHashTagRecursive(String hashtag, String[] dictionary, int start, String result) {
        if (start == hashtag.length()) {
			System.out.println(result.trim());
            return true;
        }
        for (int end = start + 1; end <= hashtag.length(); end++) {
            String substr = hashtag.substring(start, end);
            if (existInDictionary(substr, dictionary)) {
				if (breakHashTagRecursive(hashtag, dictionary, end, result + substr + " ")) {
					return true;
				}
            }
        }
        return false;
    }
}


