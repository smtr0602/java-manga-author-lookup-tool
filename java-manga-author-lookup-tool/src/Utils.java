
public class Utils {

	/**
	 * Capitalize each and every word
	 * 
	 * @param str - original string
	 * @return formatted string
	 */
	public static String capitalizeEveryWord(String str) {

		String words[] = str.split("\\s");
		String capitalizeWord = "";
		for (String w : words) {
			String first = w.substring(0, 1);
			String afterfirst = w.substring(1);
			capitalizeWord += first.toUpperCase() + afterfirst + " ";
		}
		return capitalizeWord.trim();
	}
}
