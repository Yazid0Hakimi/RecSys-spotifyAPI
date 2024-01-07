package ma.enset;
import java.util.prefs.Preferences;

public class Session {
    private static final Preferences preferences = Preferences.userNodeForPackage(Session.class);

    private static final String USERNAME_KEY = "username";
    private static final String USER_ID_KEY = "userId";

    public static String getUsername() {
        return preferences.get(USERNAME_KEY, null);
    }

    public static void setUsername(String username) {
        preferences.put(USERNAME_KEY, username);
    }

    public static long getUserId() {
        return preferences.getLong(USER_ID_KEY, -1);
    }

    public static void setUserId(long userId) {
        preferences.putLong(USER_ID_KEY, userId);
    }

    public static void clearSession() {
        preferences.remove(USERNAME_KEY);
        preferences.remove(USER_ID_KEY);
    }
}
