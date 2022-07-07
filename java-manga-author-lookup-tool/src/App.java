public class App {

	public static void main(String[] args) {

		LookupTool lookupTool = new LookupTool();
		lookupTool.init();
	}

	public static void showEmphasizedText(String text) {
		System.out.println("\n----------------------------------------------");
		System.out.println(" " + text);
		System.out.println("----------------------------------------------");
	}

	public static void showMessage(String message) {
		clearConsole();
		System.out.println("\n> " + message);
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
			App.showMessage(e.getMessage());
		}
	}

	public static void exitApp() {
		System.out.println("\nSee you later!\n");
		System.exit(0);
	}
}
