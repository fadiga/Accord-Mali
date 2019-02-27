package ml.unicef.accord_droid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Home extends Base {

    private static final String TAG = Constants.getLogTag("Home");
    private Button readButton;
    private Button quizButton;
    private Button historyButton;
    private Button aboutButton;
    private Button settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Log.d(TAG, "onCreate Home");

        settingButton = findViewById(R.id.setting);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialogueSettings();
            }
        });
        readButton = findViewById(R.id.readButton);
        quizButton = findViewById(R.id.quizButton);
        historyButton = findViewById(R.id.historyButton);
        aboutButton = findViewById(R.id.aboutButton);
            readButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                            getApplicationContext(),
                            ReadActivity.class);
                    startActivity(intent);
                }
            });
            quizButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                            getApplicationContext(),
                            QuizActivity.class);
                    startActivity(intent);
                }
            });
            historyButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                            getApplicationContext(),
                            News.class);
                    startActivity(intent);
                }
            });
            aboutButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                            getApplicationContext(),
                            AboutActivity.class);
                    startActivity(intent);
                }
            });

        String language = sharedPrefs.getString(Constants.LANGUAGE, "");

        if (language.equals("")) {
            dialogueSettings();
        }
    }
}
