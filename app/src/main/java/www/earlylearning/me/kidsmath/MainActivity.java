package www.earlylearning.me.kidsmath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private  int level = 1;
    private  int countofQuestion=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnSubmitOnClick(View view){

        level = ((Spinner) findViewById(R.id.spinnerLevel)).getSelectedItemPosition() + 1;
        countofQuestion = Integer.parseInt(((Spinner) findViewById(R.id.spinnerCount)).getSelectedItem().toString());

        Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
        intent.putExtra("bShowQuestion",true);
        intent.putExtra("iLevel",level);
        intent.putExtra("iCountofQuestion",countofQuestion);
        startActivity(intent);
        //finish();
    }
}
