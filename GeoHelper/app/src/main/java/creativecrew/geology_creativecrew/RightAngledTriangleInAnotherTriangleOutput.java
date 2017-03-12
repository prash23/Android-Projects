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

import org.w3c.dom.Text;

public class RightAngledTriangleInAnotherTriangleOutput extends AppCompatActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_angled_triangle_in_another_triangle_output);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE TRIGONOMETRIC PROBLEM");
        TextView textView = (TextView) findViewById(R.id.enterDE);
        TextView textView1 = (TextView) findViewById(R.id.dispA);
        TextView textView2 = (TextView) findViewById(R.id.dispB);
        TextView textView3 = (TextView) findViewById(R.id.dispC);

        Intent intent = getIntent();
        Double angle1 = intent.getDoubleExtra("angle",0.0);
        Double displayA = intent.getDoubleExtra("valA",0.0);
        Double displayB = intent.getDoubleExtra("valB",0.0);
        Double displayC = intent.getDoubleExtra("valC",0.0);

        Double final_answer = Math.toDegrees(angle1);
        Double answer = Math.floor(final_answer*100)/100;
        String dataString = intent.getStringExtra("units");
        textView.setText(answer.toString()+(char) 0x00B0);
        textView1.setText(displayA.toString()+" "+dataString);
        textView2.setText(displayB.toString()+" "+dataString);
        textView3.setText(displayC.toString()+" "+dataString);
        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        RelativeLayout simplePlanOutput = (RelativeLayout) findViewById(R.id.rightangle_triangle);
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
