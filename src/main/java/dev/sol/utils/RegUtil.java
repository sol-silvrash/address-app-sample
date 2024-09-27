package dev.sol.utils;

import java.util.prefs.Preferences;

public class RegUtil {
    public static <T> String getValueFromRegistry(String key, Class<T> t) {
        Preferences preferences = Preferences.userNodeForPackage(t);
        return preferences.get(key, null);
    }

    public static <T> void setValueToRegistry(String key,
            String value,
            Class<T> t) {
        Preferences preferences = Preferences.userNodeForPackage(t);
        if (value == null)
            preferences.remove(key);
        else
            preferences.put(key, value);
    }
}
