import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class LookupTool {

	private Model model;
	private Admin admin;
	Scanner scanner;

	public LookupTool() {
		model = Model.getInstance();
		admin = new Admin();
		scanner = new Scanner(System.in);
	}

	void init() {
		Utils.clearConsole();
		
		boolean isValid = false;
		while (!isValid) {
			Utils.showEmphasizedText("【 Main Menu 】What would you like to do?");
			System.out.println("  1: Look Up Authors");
			System.out.println("  2: Admin Menu");
			System.out.println("  3: Quit App");

			try {
				int input = scanner.nextInt();
				scanner.nextLine(); // prevent subsequent nextLine() skip

				switch (input) {
				case 1:
					searchAuthor();
					break;
				case 2:
					showAdminMenu();
					break;
				case 3:
					App.exitApp();
					break;
				default:
					Utils.showMessage("Wrong input. Please enter one of the numbers listed.", false);
					continue;
				}
				isValid = true;

			} catch (InputMismatchException e) {
				Utils.showMessage("Wrong input. Please enter a number", false);
				scanner.next();
			}
		}
	}

	private void showAdminMenu() {
		boolean isValid = false;

		if (!admin.getIsLoggedIn()) {
			admin.promptLogin();
		}
		while (!isValid) {
			try {
				Utils.showMessage("【 Admin Menu 】What would you like to do?", false);
				System.out.println("  1: Add New Entry");
				System.out.println("  2: Edit Entry");
				System.out.println("  3: Delete Entry");
				System.out.println("  4: Main Menu");
				int input = scanner.nextInt();
				scanner.nextLine(); // prevent subsequent nextLine() skip

				switch (input) {
				case 1:
					isValid = true;
					addAuthor();
					break;
				case 2:
					isValid = true;
					editAuthor();
					break;
				case 3:
					isValid = true;
					deleteEntry();
					break;
				case 4:
					isValid = true;
					init();
					break;
				default:
					throw new Exception("Wrong input. Please enter one of the numbers listed.");
				}
			} catch (InputMismatchException e) {
				Utils.showMessage("Wrong input. Please enter a number.", false);
				scanner.next();
			} catch (Exception e) {
				Utils.showMessage(e.getMessage(), false);
			}
		}
	}

	/**
	 * Prompt user to search for Manga and return title  
	 * 
	 * @return - title of manga
	 */
	private String getUserSearchedManga() {
		String targetMangaTitle = "";
		
		Utils.clearConsole();

		boolean isValid = false;
		while (!isValid) {
			ArrayList<String> matches = new ArrayList<>();
			Utils.showMessage("Enter keyword(s) of Manga to find:", false);

			String input = scanner.nextLine();
			for (String title : model.getCollection().keySet()) {
				if (title.toLowerCase().contains(input.toLowerCase())) {
					matches.add(title);
				}
			}
			if (matches.size() == 0) {
				Utils.showMessage("No item found :(", true);
				promptNextAction();
				continue;
			}
			
			Utils.clearConsole();
			
			boolean isValidTitleNum = false;
			while (!isValidTitleNum) {
				Utils.showMessage(matches.size() + " item(s) found. Select with number key", false);
				for (int i = 0; i < matches.size(); i++) {
					System.out.println((i + 1) + ": " + matches.get(i));
				}
				try {
					int titleNum = scanner.nextInt();
					scanner.nextLine(); // prevent subsequent nextLine() skip
					targetMangaTitle = matches.get(titleNum - 1);
					isValidTitleNum = true;
				} catch (InputMismatchException e) {
					Utils.showMessage("Wrong input. Please enter a number.", false);
					scanner.next();
				} catch (IndexOutOfBoundsException e) {
					Utils.showMessage("Wrong input. Please enter one of the numbers listed.", false);
				}
			}
			isValid = true;
		}
		return targetMangaTitle;
	}

	private void searchAuthor() {
		while (true) {
			String title = getUserSearchedManga();
			Map<String, String> author = model.getCollection().get(title);
			
			Utils.clearConsole();
			
			for (String authorEn : author.keySet()) {
				System.out.println("\n************* Author Info *************\n");
				System.out.println("Author (English) : " + "「" + authorEn + "」");
				System.out.println("Author (Japanese) : " + "「" + author.get(authorEn) + "」");
				System.out.println("\n***************************************");
			}
			promptNextAction();
		}
	}

	private void addAuthor() {
		while (true) {
			System.out.println("Enter title of Manga :");
			System.out.println("(Input will be automatically capitalized)");
			String titleInput = Utils.capitalizeEveryWord(scanner.nextLine());

			System.out.println("\nEnter name of author (English) :");
			System.out.println("(Input will be automatically capitalized)");
			String authorInputEn = Utils.capitalizeEveryWord(scanner.nextLine());

			System.out.println("\nEnter name of author (Japanese) :");
			String authorInputJa = scanner.nextLine();

			String[] confirmTexts = { "Manga title : " + titleInput, "Author (English) : " + authorInputEn,
					"Author (Japanese) : " + authorInputJa };
			if (promptContinueOrModify(confirmTexts) == "modify") {
				continue;
			}
			String result = model.setCollection(titleInput, authorInputEn, authorInputJa);
			if (!result.equals("success")) {
				Utils.showMessage(result, false);
				return;
			}
			Utils.showMessage("Successfully added!", true);
			promptNextAction();
		}
	}

	private void editAuthor() {
		String title = getUserSearchedManga();
		while (true) {
			System.out.println("\nEnter new author name (English)");
			String newNameEn = scanner.nextLine();

			System.out.println("\nEnter new author name (Japanese)");
			String newNameJa = scanner.nextLine();

			String[] confirmTexts = { "New author name (English): " + newNameEn,
					"New author name (Japanese): " + newNameJa };
			if (promptContinueOrModify(confirmTexts) == "modify") {
				continue;
			}

			String result = model.setCollection(title, newNameEn, newNameJa);
			if (!result.equals("success")) {
				Utils.showMessage(result, false);
				return;
			}
			Utils.showMessage("Successfully updated!", true);
			promptNextAction();
		}
	}

	private void deleteEntry() {
		while (true) {
			String title = getUserSearchedManga();
			String[] confirmTexts = { "Delete 「" + title + "」?" };
			if (promptContinueOrModify(confirmTexts) == "modify") {
				continue;
			}

			String result = model.removeFromCollection(title);
			if (!result.equals("success")) {
				Utils.showMessage(result, false);
				return;
			}
			Utils.showMessage("Successfully deleted!", true);
			promptNextAction();
		}
	}

	/**
	 * prompt user to continue or modify input
	 * 
	 * @param texts - texts to display with prompt
	 * @return message - string to determine action based on
	 */
	private String promptContinueOrModify(String[] texts) {
		String message = null;
		
		boolean isValidConfirmInputNum = false;
		while (!isValidConfirmInputNum) {
			Utils.showMessage("Please Confirm the following:", true);
			System.out.println("・・・・・・・・・・・・・・・・・・・");
			for (String text : texts) {
				System.out.println(" " + text);
			}
			System.out.println("・・・・・・・・・・・・・・・・・・・");
			System.out.println("");
			System.out.println("  1: Continue");
			System.out.println("  2: Modify");
			System.out.println("  3: Main Menu");

			try {
				int confirmInput = scanner.nextInt();
				scanner.nextLine(); // prevent subsequent nextLine() skip
				switch (confirmInput) {
				case 1:
					isValidConfirmInputNum = true;
					break;
				case 2:
					isValidConfirmInputNum = true;
					message = "modify";
					break;
				case 3:
					isValidConfirmInputNum = true;
					init();
					break;
				default:
					throw new Exception("Wrong input. Please enter one of the numbers listed.");
				}
			} catch (InputMismatchException e) {
				Utils.showMessage("Wrong input. Please enter a number.", false);
				scanner.next();
			} catch (Exception e) {
				Utils.showMessage(e.getMessage(), false);
			}
		}
		return message;
	}

	/**
	 * prompt user to choose next action
	 */
	private void promptNextAction() {
		boolean isValid = false;
		while (!isValid) {
			try {
				Utils.showMessage("Choose action:", false);
				System.out.println("  1: Do it again");
				System.out.println("  2: Main Menu");
				System.out.println("  3: Quit App");
				int nextOptNum = scanner.nextInt();
				scanner.nextLine(); // prevent subsequent nextLine() to be skipped
				switch (nextOptNum) {
				case 1:
					isValid = true;
					continue;
				case 2:
					init();
					break;
				case 3:
					App.exitApp();
				default:
					throw new Exception("Wrong input. Please enter one of the numbers listed.");
				}
			} catch (InputMismatchException e) {
				Utils.showMessage("Wrong input. Please enter a number.", false);
				scanner.next();
			} catch (Exception e) {
				Utils.showMessage(e.getMessage(), false);
			}
		}
	}
}
