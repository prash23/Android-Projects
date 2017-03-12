package creativecrew.geology_creativecrew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FlagpoleProblemOutput extends AppCompatActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flagpole_problem_output);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE TRIGONOMETRIC PROBLEM");

        TextView textView1 = (TextView) findViewById(R.id.enterH);
        TextView textView2 = (TextView) findViewById(R.id.dispEA);
        TextView textView3 = (TextView) findViewById(R.id.dispBC);
        TextView textView4 = (TextView) findViewById(R.id.dispB);
        TextView textView5 = (TextView) findViewById(R.id.dispD);

        Intent intent = getIntent();
        String dataString = intent.getStringExtra("units");
        Double height = intent.getDoubleExtra("polehgt",0.0);
        Double answer = Math.floor(height*100)/100;
        Double EA = intent.getDoubleExtra("valEA",0.0);
        Double BC = intent.getDoubleExtra("valBC",0.0);
        Double B = intent.getDoubleExtra("valB",0.0);
        Double D = intent.getDoubleExtra("valD",0.0);

        textView1.setText(answer.toString()+" "+dataString);
        textView2.setText(EA.toString()+ (char) 0x00B0);
        textView3.setText(BC.toString()+(char) 0x00B0);
        textView4.setText(B.toString()+" "+dataString);
        textView5.setText(D.toString()+" "+dataString);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        RelativeLayout simplePlanOutput = (RelativeLayout) findViewById(R.id.flagpole_triangle);
        switch (item.getItemId()) {
            case R.id.save:
                Snackbar snackbar = Snackbar
                        .make(simplePlanOutput, "The output is saved", Snackbar.LENGTH_LONG);
                snackbar.show();

                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
