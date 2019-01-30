package ml.unicef.accord_droid;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fad on 04/01/19.
 */
public class Base extends Activity {

    private final static String TAG = "BASELog-BaseActivity";

    String l1 = "Français";
    String l3 = "Bambara";
    String l4 = "Fulfulde";
    String l5 = "Songhoi";
    String l6 = "Tamacheq";
    String l7 = "Bomu";
    String l8 = "Mamara";
    String l9 = "Shennara";
    String l10 = "Soninke";
    String l11 = "Bozo";
    String l12 = "Bogon";
    String l13 = "Malinke";
    String l14 = "Khasonke";

    private Button btnCancel;
    private Button saveButton;
    private Spinner spinner;

    public SharedPreferences sharedPrefs;

    final String[] languages = new String[] { l1, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14 };

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void dialogueSettings() {

        final Dialog settingdialog = new Dialog(Base.this);
        settingdialog.setContentView(R.layout.add_preference);
        settingdialog.setTitle("Paramètre de langue");
        settingdialog.show();
        spinner = (Spinner)settingdialog.findViewById(R.id.spinner);
        btnCancel = (Button)settingdialog.findViewById(R.id.cancelButton);
        saveButton = (Button)settingdialog.findViewById(R.id.saveButton);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String language = sharedPrefs.getString(Constants.LANGUAGE, "");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Base.this,
                android.R.layout.simple_spinner_item,Constants.getLanguages());
        spinner.setAdapter(adapter);
        if (!language.equals("")) {
            int pos = new ArrayList<String>(Arrays.asList(Constants.getLanguages())).indexOf(language);
            spinner.setSelection(pos);
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(Constants.LANGUAGE, String.valueOf(spinner.getSelectedItem()));
                editor.putInt(Constants.PAGE_NB, 0);
                editor.apply();
                settingdialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingdialog.dismiss();
            }
        });
    }
//    public void SetAlarm(final Activity activity) {
//        BroadcastReceiver receiver = new BroadcastReceiver() {
//            @Override public void onReceive( Context context, Intent _ ) {
//
//                final Map<String, String> mapAlerte =  new HashMap<String, String>();
//               // mapAlerte.put("title1", "message1");
//                mapAlerte.put(getString(R.string.hygeme), getString(R.string.repely));
//
//                final CharSequence[] items = {getString(R.string.hygeme)};
//                String randomStr = (String) items[new Random().nextInt(items.length)];
//
//                generateNotification(activity, mapAlerte.get(randomStr), randomStr);
//                context.unregisterReceiver( this );
//                SetAlarm(activity);
//            }
//        };
//        this.registerReceiver( receiver, new IntentFilter("com.malisante.fad.ebolamali") );
//        PendingIntent pintent = PendingIntent.getBroadcast( this, 0, new Intent("com.malisante.fad.ebolamali"), 0 );
//        AlarmManager manager = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
//
//        long interval = 30 * 60 * 1000; // 20
//        //long interval = 10* 1000;
//        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, interval, pintent);
//    }

    private static void generateNotification(Context context, String message, String title) {
        int icon = R.mipmap.ic_launcher;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
//        Intent notificationIntent = new Intent(context, Recall.class);
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                                    Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.CONTENTS_FILE_DESCRIPTOR);
//        notificationIntent.putExtra("title", title);
//        notificationIntent.putExtra("msg", message);
//        PendingIntent intent = PendingIntent.getActivity(context, 1, notificationIntent, 0);
//        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);
    }
}
