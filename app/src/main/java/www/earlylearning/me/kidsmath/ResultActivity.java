package www.earlylearning.me.kidsmath;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 21/10/2017.
 */

public class ResultActivity extends AppCompatActivity {

    ArrayList<QuestionAnswer> listQuestionAnswer = new ArrayList<QuestionAnswer>();
    int level = 1; int countofQuestion = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_result);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            level = extras.getInt("iLevel");
            countofQuestion = extras.getInt("iCountofQuestion");
            int iCorrect = 0; int iWrong = 0;
            listQuestionAnswer = (ArrayList<QuestionAnswer>)getIntent().getSerializableExtra("Result");
            for(int i=1;i<=listQuestionAnswer.size();i++)
            {
                if(listQuestionAnswer.get(i-1).GetCorrectAnswer().equals(listQuestionAnswer.get(i-1).GetUserAnswer())) {
                    iCorrect++;
                }
                else{
                    iWrong++;
                }
            }
            TextView tvSuccess = (TextView)findViewById(R.id.tvCorrect);
            tvSuccess.setText(String.format("Correct Answers: %d", iCorrect));

            TextView tvWrong = (TextView)findViewById(R.id.tvWrong);
            tvWrong.setText(String.format("Wrong Answers: %d", iWrong));


            ((RatingBar)findViewById(R.id.ratingBar)).setRating(Math.round(iCorrect  * (5.0 /(iWrong + iCorrect))));

        }


    }

    public void BacktoHomeOnClick(View view){
        Intent intent = new Intent(ResultActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void ViewAnswersOnClick(View view){
        Intent intent = new Intent(ResultActivity.this,QuestionActivity.class);
        intent.putExtra("bShowAnswer",true);
        intent.putExtra("QuestionAnswer",listQuestionAnswer);
        intent.putExtra("iLevel",level);
        intent.putExtra("iCountofQuestion",countofQuestion);
        startActivity(intent);
        finish();
    }
}
