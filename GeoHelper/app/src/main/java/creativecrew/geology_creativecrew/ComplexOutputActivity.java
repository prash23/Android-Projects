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

public class ComplexOutputActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_output);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE TRIGONOMETRIC PROBLEM");


        TextView textView1 = (TextView) findViewById(R.id.dispBC);
        TextView textView2 = (TextView) findViewById(R.id.dispABC);
       TextView textView3 = (TextView) findViewById(R.id.dispACD);
        TextView textView4 = (TextView) findViewById(R.id.valueAD);
        Intent intent = getIntent();
        Double h = intent.getDoubleExtra("ans", 0.0);
        Double h1 = intent.getDoubleExtra("angleABC1",0.0);
        Double h2 = intent.getDoubleExtra("angleACD1",0.0);
        Double h3 = intent.getDoubleExtra("sideKS",0.0);
        Double answer = Math.floor(h*100)/100;
        String dataString = intent.getStringExtra("units");

        textView1.setText(h1.toString()+" "+dataString);
        textView2.setText(h2.toString()+ (char) 0x00B0);
        textView3.setText(h3.toString()+ (char) 0x00B0);
        textView4.setText(answer.toString()+" "+dataString);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        RelativeLayout simplePlanOutput = (RelativeLayout) findViewById(R.id.complex_triangle);
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
