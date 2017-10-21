package www.earlylearning.me.kidsmath;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 21/10/2017.
 */

public class QuestionActivity extends AppCompatActivity {

    ArrayList<QuestionAnswer>  listQuestionAnswer = new ArrayList<QuestionAnswer>();
    int currentQuestionID = 1;
    int level = 1;
    int countofQuestion = 1;
    boolean bShowAnswer = false;
    boolean bShowQuestion = false;

    Button btnNext = null;
    Button btnPrevious = null;
    EditText etUserAnser = null;
    TextView tbHeader = null;
    TextView tvQuestion = null;
    TextView tvCorrectAnswer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        btnNext = (Button)findViewById(R.id.btnNext);
        btnPrevious = (Button)findViewById(R.id.btnPrevious);
        etUserAnser = (EditText) findViewById(R.id.etUserAnswer);
        tbHeader = (TextView) findViewById(R.id.tvHeader);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvCorrectAnswer = (TextView) findViewById(R.id.tvCorrectAnswer);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bShowQuestion = extras.getBoolean("bShowQuestion");
            bShowAnswer = extras.getBoolean("bShowAnswer");
        }

        if(bShowQuestion)
        {
            level = extras.getInt("iLevel");
            countofQuestion = extras.getInt("iCountofQuestion");

            currentQuestionID = 1;

            GenerateQuestionAnswerList(level,countofQuestion);

            ShowQuestionAnswer();

            btnPrevious.setVisibility(View.INVISIBLE);
        }else if(bShowAnswer)
        {
            level = extras.getInt("iLevel");
            countofQuestion = extras.getInt("iCountofQuestion");

            currentQuestionID = 1;

            listQuestionAnswer = (ArrayList<QuestionAnswer>)getIntent().getSerializableExtra("QuestionAnswer");

            ShowQuestionAnswer();

            etUserAnser.setFocusable(false);

            btnPrevious.setVisibility(View.INVISIBLE);
        }
    }

    private void ShowQuestionAnswer()
    {
        tbHeader.setText(String.format("Question: %d/%d - Level: %d ",currentQuestionID, countofQuestion, level));

        tvQuestion.setText(listQuestionAnswer.get(currentQuestionID - 1).GetQuestion());

        etUserAnser.setText(listQuestionAnswer.get(currentQuestionID - 1).GetUserAnswer());

        tvCorrectAnswer.setText("");
        if(bShowAnswer
                && !listQuestionAnswer.get(currentQuestionID-1).GetCorrectAnswer().equals(listQuestionAnswer.get(currentQuestionID-1).GetUserAnswer())) {
            tvCorrectAnswer.setText(String.format("Correct Answer: %s",listQuestionAnswer.get(currentQuestionID - 1).GetCorrectAnswer()));
        }
    }

    private void GenerateQuestionAnswerList(int level, int countofQuestion)
    {
        int addNum1 = 0; int addNum2=0;int total = 0;
        for (int i = 1; i <= countofQuestion; i++) {
            QuestionAnswer qa = new QuestionAnswer();
            qa.SetQId(i);
            switch (level) {

                case 1:
                    addNum1 = GenerateRandomNumber(1,10);
                    addNum2 = GenerateRandomNumber(1,10);
                    total = addNum1 + addNum2;
                    qa.SetQuestion(String.format("%d + %d = ", addNum1, addNum2));
                    qa.SetCorrectAnswer(String.valueOf(total));

                    break;
                case 2:

                    addNum1 = GenerateRandomNumber(1,100);
                    addNum2 = GenerateRandomNumber(1,100);
                    total = addNum1 + addNum2;
                    qa.SetQuestion(String.format("%d + %d = ", addNum1, addNum2));
                    qa.SetCorrectAnswer(String.valueOf(total));
                    break;
                case 3:
                    addNum1 = GenerateRandomNumber(1,1000);
                    addNum2 = GenerateRandomNumber(1,1000);
                    total = addNum1 + addNum2;
                    qa.SetQuestion(String.format("%d + %d = ", addNum1, addNum2));
                    qa.SetCorrectAnswer(String.valueOf(total));
                    break;

            }
            listQuestionAnswer.add(qa);
        }
    }

    private int GenerateRandomNumber(int min, int max)
    {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private void SaveUserAnswer()
    {
        if (etUserAnser.getText().toString().trim().length() > 0) {
            listQuestionAnswer.get(currentQuestionID - 1).SetUserAnswer(etUserAnser.getText().toString().trim());
            etUserAnser.setText("");
        }

    }

    public void NextQuestion(View view){

        SaveUserAnswer();
        currentQuestionID++;
        ShowQuestionAnswer();
        if(currentQuestionID >= countofQuestion)
        {
            btnNext.setVisibility(View.INVISIBLE);
        }
        else
        {
            btnPrevious.setVisibility(View.VISIBLE);
        }
    }

    public void PreviousQuestion(View view){

        SaveUserAnswer();
        currentQuestionID--;
        ShowQuestionAnswer();
        if(currentQuestionID<=1)
        {
            btnPrevious.setVisibility(View.INVISIBLE);
        }
        else
        {
            btnNext.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_submit)
        {
            SaveUserAnswer();
            Intent intent = new Intent(QuestionActivity.this,ResultActivity.class);
            intent.putExtra("Result",listQuestionAnswer);
            intent.putExtra("iLevel",level);
            intent.putExtra("iCountofQuestion",countofQuestion);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
