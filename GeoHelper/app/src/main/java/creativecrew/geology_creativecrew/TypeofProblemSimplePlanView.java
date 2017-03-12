package creativecrew.geology_creativecrew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TypeofProblemSimplePlanView extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeof_problem_simple_plan_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE PLAN VIEW");
        final Button quadrant = (Button) findViewById(R.id.Quadrant);
        final Button azimuth = (Button) findViewById(R.id.Azimuth);
        final Button dipQuadrant = (Button) findViewById(R.id.DDQuadrant);
        final Button dipAzimuth = (Button) findViewById(R.id.DDAzimuth);
        final Button geologypaces = (Button) findViewById(R.id.Paces);

        quadrant.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String buttonClicked = quadrant.getText().toString();
                Intent quadrant = new Intent(TypeofProblemSimplePlanView.this, SimplePlanView.class);
                quadrant.putExtra("text2",buttonClicked);
                startActivity(quadrant);
            }
        });
        azimuth.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String buttonClicked = azimuth.getText().toString();
                Intent quadrant = new Intent(TypeofProblemSimplePlanView.this, SimplePlanView.class);
                quadrant.putExtra("text2",buttonClicked);
                startActivity(quadrant);
            }
        });
        dipQuadrant.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String buttonClicked = dipQuadrant.getText().toString();
                Intent quadrant = new Intent(TypeofProblemSimplePlanView.this, SimplePlanView.class);
                quadrant.putExtra("text2",buttonClicked);
                startActivity(quadrant);
            }
        });
        dipAzimuth.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String buttonClicked = dipAzimuth.getText().toString();
                Intent quadrant = new Intent(TypeofProblemSimplePlanView.this, SimplePlanView.class);
                quadrant.putExtra("text2",buttonClicked);
                startActivity(quadrant);
            }
        });
        geologypaces.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String buttonClicked = geologypaces.getText().toString();
                Intent quadrant = new Intent(TypeofProblemSimplePlanView.this, PacesInput.class);
                quadrant.putExtra("text2",buttonClicked);
                startActivity(quadrant);
            }
        });
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_typeof_problem_simple_plan_view);
//    }
//
//    public void quadrantOnClick(View view) {
//        //Submit button on click
//        Button button = (Button) view;
//
//        String buttonClicked = button.getText().toString();
//
//        Intent submitButtonIntent = new Intent(TypeofProblemSimplePlanView.this, SimplePlanView.class);
//
//        submitButtonIntent.putExtra("text2",buttonClicked);
//        startActivity(submitButtonIntent);
//
//    }

//    public void quadrantOnClick(View view) {
//        //Submit button on click
//        Button submitButton = (Button) view;
//        Intent submitButtonIntent = new Intent(TypeofProblemSimplePlanView.this, SimplePlanView.class);
//        startActivity(submitButtonIntent);
//
//    }
}
