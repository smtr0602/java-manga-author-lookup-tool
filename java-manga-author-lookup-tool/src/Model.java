import java.util.HashMap;
import java.util.Map;

public class Model {

	private static Model model;
	private Map<String, String> adminUsers;
	private Map<String, Map<String, String>> collection;

	private Model() {
		getAdminUsersFromDB();
		getCollectionFromDB();
	}

	/**
	 * create singleton object that can be used across different classes
	 * 
	 * @return
	 */
	public static Model getInstance() {
		// create singleton object if not exists
		if (model == null) {
			model = new Model();
		}
		return model;
	}

	/**
	 * Retrieve all admin users from database
	 */
	public void getAdminUsersFromDB() {
		adminUsers = new HashMap<>();
		// temporary data until integrated with database
		adminUsers.put("admin", "1234");
	}

	public Map<String, String> getAdminUsers() {
		return adminUsers;
	}

	/**
	 * Retrieve all entries from database
	 */
	public void getCollectionFromDB() {
		// temporary data until integrated with database
		collection = new HashMap<>();
		setCollection("Hunter x Hunter", "Yoshihiro Togashi", "冨樫 義博");
		setCollection("Dragon Ball", "Akira Toriyama", "鳥山明");
		setCollection("Demon Slayer", "Koyoharu Gotouge", "吾峠 呼世晴");
		setCollection("Slam Dunk", "Takehiko Inoue", "井上雄彦");
		setCollection("One Piece", "Eiichiro Oda", "尾田栄一郎");
		setCollection("Jujutsu Kaisen", "Gege Akutami", "芥見下々");
	}

	public Map<String, Map<String, String>> getCollection() {
		return collection;
	}

	/**
	 * Set entry with specified data
	 * 
	 * @param title
	 * @param author1
	 * @param author2
	 * @return
	 */
	public String setCollection(String title, String author1, String author2) {
		String message = "success";
		try {
			collection.put(title, new HashMap<>() {
				private static final long serialVersionUID = 1L;
				{
					put(author1, author2);
				}
			});
		} catch (Exception e) {
			message = e.getMessage();
		}
		return message;
	}

	/**
	 * Remove entry with specified title from HashMap
	 * 
	 * @param title
	 * @return
	 */
	public String removeFromCollection(String title) {
		String message = "success";
		try {
			collection.remove(title);
		} catch (Exception e) {
			message = e.getMessage();
		}
		return message;
	}
}
