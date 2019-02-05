package ml.unicef.accord_droid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PlayQuiz extends Base {

    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);
        Bundle extras = getIntent().getExtras();
        level = extras.getString("level");

        Toast.makeText(PlayQuiz.this,"Level : " + level , Toast.LENGTH_LONG).show();
    }

    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    public String[] evaluateGui() {
        String[] ret = new String[5];
//        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2Greece = findViewById(R.id.question_2_Greece);
        CheckBox checkBoxQuestion2Burma = findViewById(R.id.question_2_Burma);
        CheckBox checkBoxQuestion2Luxembourg = findViewById(R.id.question_2_Luxembourg);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2Greece.isChecked() == true && checkBoxQuestion2Burma.isChecked() == false && checkBoxQuestion2Luxembourg.isChecked() == true) {
            answerQuestion2 = true;
        }

        CheckBox checkBoxQuestion4Capital = findViewById(R.id.question_4_capital);
        CheckBox checkBoxQuestion4Hessia = findViewById(R.id.question_4_hessia);
        CheckBox checkBoxQuestion4Bavaria = findViewById(R.id.question_4_bavaria);

        Boolean answerQuestion4 = false;

        Boolean capital = checkBoxQuestion4Capital.isChecked();
        Boolean hessia = checkBoxQuestion4Hessia.isChecked();
        Boolean bavaria = checkBoxQuestion4Bavaria.isChecked();


        if (capital == false && hessia == false && bavaria == true) {
            answerQuestion4 = true;
        }

//        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();
        ret[0] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();

        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"paris", "true", "south", "true", "germany"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    public void toastResult(int result) {
        String message = result + " out of 5. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Quite nice.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        } else if (result == 5) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    public void reset(View view) {
//        EditText editText = findViewById(R.id.question_1);
//        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_Greece);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Burma);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Luxembourg);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question_4_capital);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_hessia);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_bavaria);
        checkBox.setChecked(false);

        radioGroup = findViewById(R.id.radio_group_question_5);
        radioGroup.clearCheck();
    }
}