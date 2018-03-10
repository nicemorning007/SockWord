package cn.nicemorning;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nicemorning on 10-Mar-18.
 * In package cn.nicemorning
 */

public class BaseApplication extends Application {
    private static Map<String, Activity> destoryMap = new HashMap<>();

    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }

    public static void destroyActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            destoryMap.get(key).finish();
        }
    }
}
