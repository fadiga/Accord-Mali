package ml.unicef.accord_droid;

/**
 * Created by fad on 15/01/19.
 */

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class PreferenceUserData extends CommonData {

    @Ignore
    private static final String TAG = Constants.getLogTag("PreferenceUserData");

    public String userLanguage;
    public int CounRead = 0;
    public int pageRead = 0;

    public PreferenceUserData() {
    }
    public static PreferenceUserData get() {
        PreferenceUserData report = getUniqueRecord(PreferenceUserData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new PreferenceUserData();
            report.safeSave();
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }
    public PreferenceUserData(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public void setUserLanguage(String name) {
        this.userLanguage = userLanguage;
    }

    public String getUserLanguage() {
        return this.userLanguage;
    }

}