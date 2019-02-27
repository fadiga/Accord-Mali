package ml.unicef.accord_droid;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizPlay extends Base {

    private static final String TAG = Constants.getLogTag("QuizPlay");
    private int level;
    private int type;
    private RadioButton rsp1;
    private RadioButton rsp2;
    private RadioButton rsp3;
    private RadioButton rsp4;
    private CheckBox checkBoxCh1;
    private CheckBox checkBoxCh2;
    private CheckBox checkBoxCh3;
    private CheckBox checkBoxCh4;
    private JSONArray choises;
    private JSONObject qJsonObject;
    private int nbQ = 0;

    private int scoreQ1 = -1;
    private int scoreQ2 = -1;
    private int scoreQ3 = -1;
    private int scoreQ4 = -1;
    private int scoreQ5 = -1;
    private Button start1;
    private Button start2;
    private Button start3;
    private Button start4;
    private Button start5;
    private RadioGroup radioGroup;

    private ArrayList<Integer> list_q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_play);
        Bundle extras = getIntent().getExtras();
        level = extras.getInt(Constants.LEVEL);
        TextView levelV = findViewById(R.id.level_v);

        start1 = findViewById(R.id.start1);
        start2 = findViewById(R.id.start2);
        start3 = findViewById(R.id.start3);
        start4 = findViewById(R.id.start4);
        start5 = findViewById(R.id.start5);
        levelV.setText("" + level);

        list_q =  makeQuestion();
        playGame(nbQ);

    }

    public ArrayList<Integer> makeQuestion() {
        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = 1; i <= 5; ++i) number.add(i);
        Collections.shuffle(number);
        return number;
    }

    public void  playGame (int nbQ) {
        //        Toast.makeText(getApplicationContext(), "nbQ : " + nbQ + "list_q.get(nbQ) " + list_q.get(nbQ), Toast.LENGTH_LONG).show();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray qLevel=obj.getJSONArray("l" + level);
            JSONObject c = qLevel.getJSONObject(new Random().nextInt(qLevel.length()));
            qJsonObject = c.getJSONObject("Q" + list_q.get(nbQ));
            TextView labelQ = findViewById(R.id.question_label);
            labelQ.setText(qJsonObject.getString("label"));
            type = qJsonObject.getInt("type");
            choises = qJsonObject.getJSONArray("choises");
            radioGroup = findViewById(R.id.radio_group);
            radioGroup.clearCheck();
            LinearLayout checkBox = findViewById(R.id.checkBox);

            if (type == Constants.QTUPE2) {
                radioGroup.setVisibility(View.GONE);
                checkBox.setVisibility(View.VISIBLE);
                checkBoxCh1 = findViewById(R.id.ch1);
                checkBoxCh1.setChecked(false);
                checkBoxCh1.setText(""+choises.get(0));
                checkBoxCh2 = findViewById(R.id.ch2);
                checkBoxCh2.setChecked(false);
                checkBoxCh2.setText("" + choises.get(1));
                checkBoxCh3 = findViewById(R.id.ch3);
                checkBoxCh3.setChecked(false);
                checkBoxCh3.setText("" +choises.get(2));
                checkBoxCh4 = findViewById(R.id.ch4);
                checkBoxCh4.setChecked(false);
                checkBoxCh4.setText("" +choises.get(3));
            } else if (type == Constants.QTUPE1) {
                radioGroup.setVisibility(View.VISIBLE);
                checkBox.setVisibility(View.GONE);
                rsp1 = findViewById(R.id.rsp1);
                rsp1.setText(""+choises.get(0));
                rsp2 = findViewById(R.id.rsp2);
                rsp2.setText("" + choises.get(1));
                rsp3 = findViewById(R.id.rsp3);
                rsp3.setText("" +choises.get(2));
                rsp4 = findViewById(R.id.rsp4);
                rsp4.setText("" +choises.get(3));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void evaluateGuiCheck() throws JSONException {
        if (type == Constants.QTUPE1) {
            int response = qJsonObject.getInt("responses");
            int ch = -1;

            if (rsp1.isChecked()){ ch=1; }
            if (rsp2.isChecked()){ ch=2; }
            if (rsp3.isChecked()){ ch=3; }
            if (rsp4.isChecked()){ ch=4; }

            if (response == ch){
                refreshScore(1);
            } else {
                refreshScore(0);
            }
        } else if (type == Constants.QTUPE2) {
            ArrayList<Boolean> repG = new ArrayList<>();
            repG.add(checkBoxCh1.isChecked());
            repG.add(checkBoxCh2.isChecked());
            repG.add(checkBoxCh3.isChecked());
            repG.add(checkBoxCh4.isChecked());

            JSONArray responses = qJsonObject.getJSONArray("responses");
            Boolean resp = false;
            for (int i=0; i<responses.length(); i++) {
                if (repG.get(i).equals(responses.get(i))) {
                    resp = true;
                } else {
                    resp = false;
                    break;
                }
            }
            if (resp){ refreshScore(1);
            } else { refreshScore(0);
            }
        }
        refreshScoreUI();
        if (nbQ < 4) {
            nbQ++;
            playGame(nbQ);
        } else {
            final Dialog endGameDialog = new Dialog(QuizPlay.this,  R.style.hidetitle);
            endGameDialog.setCancelable(false);
            endGameDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            endGameDialog.setContentView(R.layout.quiz_end);
            endGameDialog.show();

            TextView msgScore = endGameDialog.findViewById(R.id.msgscore);
            TextView msg = endGameDialog.findViewById(R.id.msg);
            TextView msg_end = endGameDialog.findViewById(R.id.msg_end);

            LinearLayout tryLL = endGameDialog.findViewById(R.id.tryLL);
            LinearLayout nextLevelLy = endGameDialog.findViewById(R.id.nextLevelLL);
            LinearLayout endLevelLL = endGameDialog.findViewById(R.id.endLevelLL);

            msgScore.setText("SCORE FINALE : " + result() + "/" + 5);

            endLevelLL.setVisibility(View.GONE);
            tryLL.setVisibility(View.GONE);
            nextLevelLy.setVisibility(View.GONE);

            if (result() > 3) {
                if (level < 6) {
                    msg.setText("Felicitation !");
                    nextLevelLy.setVisibility(View.VISIBLE);
                    final SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putInt(Constants.CURRENT_LEVEL, level + 1);
                    editor.apply();
                } else {
                    msg_end.setText("Fin de la partie !");
                    endLevelLL.setVisibility(View.VISIBLE);
                }
            } else {
                msg.setText("Perdue !");
                tryLL.setVisibility(View.VISIBLE);
            }
            Button endLevel = endGameDialog.findViewById(R.id.endLevel);
            endLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    endGameDialog.dismiss();
                    finish();
                }
            });
            Button btnTry = endGameDialog.findViewById(R.id.btnTry);
            btnTry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    endGameDialog.dismiss();
                    finish();
                    Intent intent = new Intent(
                            getApplicationContext(),
                            QuizPlay.class);
                    intent.putExtra(Constants.LEVEL, level);
                    startActivity(intent);
                }
            });
            Button bnNextLevel = endGameDialog.findViewById(R.id.nextLevel);
            bnNextLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    endGameDialog.dismiss();
                    finish();
                    Intent intent = new Intent(
                            getApplicationContext(),
                            QuizPlay.class);
                    intent.putExtra(Constants.LEVEL, level + 1);
                    startActivity(intent);
                }
            });
            Button readMore = endGameDialog.findViewById(R.id.readMore);
            readMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    endGameDialog.dismiss();
                    Intent intent = new Intent(
                            getApplicationContext(),
                            ReadActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    public int result () {
        return scoreQ1 + scoreQ2 + scoreQ3 + scoreQ4 + scoreQ5;
    }

    public void refreshScore(int val) {
        if (nbQ == 0) { scoreQ1 = val; }
        if (nbQ == 1) { scoreQ2 = val; }
        if (nbQ == 2) { scoreQ3 = val; }
        if (nbQ == 3) { scoreQ4 = val; }
        if (nbQ == 4) { scoreQ5 = val; }
    }

    public void refreshScoreUI() {
        updateScore(start1, scoreQ1);
        updateScore(start2, scoreQ2);
        updateScore(start3, scoreQ3);
        updateScore(start4, scoreQ4);
        updateScore(start5, scoreQ5);
    }

    public void updateScore(Button start, int scoreQ) {
        if (scoreQ == 1){
            start.setBackgroundResource(R.drawable.bn_green);
        } else if (scoreQ == 0){
            start.setBackgroundResource(R.drawable.bn_red);
        }
    }

    public void startEvaluation(View view) {
        try {
            evaluateGuiCheck();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}