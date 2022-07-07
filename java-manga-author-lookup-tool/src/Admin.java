import java.util.Scanner;

public class Admin {

	private Model model;
	private boolean isLoggedIn;
	private String username;
	Scanner scanner;

	public Admin() {
		model = Model.getInstance();
		scanner = new Scanner(System.in);
	}

	void promptLogin() {
		Utils.clearConsole();
		
		Utils.showEmphasizedText("Please login:");

		boolean isValid = false;
		while (!isValid) {
			System.out.println("Enter username:");
			String usernameInput = scanner.nextLine();

			System.out.println("\nEnter password:");
			String passwordInput = scanner.nextLine();

			// wrong username
			if (model.getAdminUsers().get(usernameInput) == null) {
				Utils.showMessage("User not found :( Please try again. \n", false);
				continue;

				// wrong password
			} else if (!passwordInput.equals(model.getAdminUsers().get(usernameInput))) {
				Utils.showMessage("Wrong password :( Please try again. \n", false);
				continue;
			}

			isValid = true;
			isLoggedIn = true;
			setUsername(usernameInput);

			Utils.clearConsole();
			System.out.println("\n*********************************");
			System.out.println("  WELCOME BACK, " + username + "!");
			System.out.println("*********************************");
		}
	}

	private void setUsername(String username) {
		this.username = username;
	}

	boolean getIsLoggedIn() {
		return isLoggedIn;
	}
}
