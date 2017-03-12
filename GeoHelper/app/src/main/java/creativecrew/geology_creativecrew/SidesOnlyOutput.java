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

public class SidesOnlyOutput extends AppCompatActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sides_only_output);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE TRIGONOMETRIC PROBLEM");
        TextView textView = (TextView) findViewById(R.id.enterBC);
        TextView textView1 = (TextView) findViewById(R.id.dispA);
        TextView textView2 = (TextView) findViewById(R.id.dispB);


        Intent intent = getIntent();
        Double angleBC = intent.getDoubleExtra("angle",0.0);
        Double angleBC_degrees =Math.toDegrees(angleBC);
        Double answer = Math.floor(angleBC_degrees*100)/100;
        String dataString = intent.getStringExtra("units");
        Double displayA = intent.getDoubleExtra("valA",0.0);
        Double displayB = intent.getDoubleExtra("valB",0.0);

        textView1.setText(displayA.toString()+" "+dataString);
        textView2.setText(displayB.toString()+" "+dataString);
        textView.setText(answer.toString()+(char) 0x00B0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        RelativeLayout simplePlanOutput = (RelativeLayout) findViewById(R.id.sidesonly_triangle);
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
