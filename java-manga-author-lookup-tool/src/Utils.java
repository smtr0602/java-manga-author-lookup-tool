
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
	
	public static void showMessage(String message) {
		clearConsole();
		System.out.println("\n> " + message);
	}
	
	public static void showEmphasizedText(String text) {
		System.out.println("\n----------------------------------------------");
		System.out.println(" " + text);
		System.out.println("----------------------------------------------");
	}

	public static void clearConsole() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				Runtime.getRuntime().exec("clear");
				System.out.print("\033\143");

			}
		} catch (Exception e) {
			showMessage(e.getMessage());
		}
	}
}
