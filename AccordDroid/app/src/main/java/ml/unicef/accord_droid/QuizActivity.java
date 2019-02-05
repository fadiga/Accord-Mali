package ml.unicef.accord_droid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends Base {

    private static final String TAG = Constants.getLogTag("QuizActivity");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_home);
        setupUI();
    }
    private void setupUI() {
        final Button ssong = findViewById(R.id.ssong);
        ssong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ssong.setBackgroundResource(R.drawable.speaker);

            }
        });

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        Home.class);
                startActivity(intent);
            }
        });
        Button level1 = findViewById(R.id.level1);
        check_level(1, level1);
        Button level2 = findViewById(R.id.level2);
        check_level(2, level2);
        Button level3= findViewById(R.id.level3);
        check_level(3, level3);
        Button level4 = findViewById(R.id.level4);
        check_level(4, level4);
        Button level5 = findViewById(R.id.level5);
        check_level(5, level5);
        Button level6 = findViewById(R.id.level6);
        check_level(6, level6);

        }
        public void check_level(final int el, Button level){

            int current_level = sharedPrefs.getInt(Constants.CURRENT_LEVEL, 1);
            if (current_level < el){
                level.setBackgroundResource(R.drawable.levellock);
                level.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(QuizActivity.this,"NON ACTIVE", Toast.LENGTH_LONG).show();
                    }});
            } else {
                level.setBackgroundResource(R.drawable.level);
                level.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), PlayQuiz.class);
                        intent.putExtra("level", el);
                        startActivity(intent);
                    }});
            }

        }
    }
