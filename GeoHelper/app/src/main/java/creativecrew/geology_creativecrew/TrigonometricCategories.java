package creativecrew.geology_creativecrew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrigonometricCategories extends AppCompatActivity {
    private Toolbar mToolbar;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigonometric_categories);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("TRIGONOMETRIC PROBLEMS");
        button1 = (Button) findViewById(R.id.riverProblem);
        button2 = (Button) findViewById(R.id.complex);
        button3 = (Button) findViewById(R.id.rightAngled);
        button4 = (Button) findViewById(R.id.flagpole);
//        button5 = (Button) findViewById(R.id.sides);
//        button6 = (Button) findViewById(R.id.tower);
//        button7 = (Button) findViewById(R.id.pyth);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Trigonometric_River.class);
                startActivity(intent1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), ComplexActivity.class);
                startActivity(intent2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(),RightAngledTriangleInAnotherTriangle.class);
                startActivity(intent3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), FlagpoleProblem.class);
                startActivity(intent4);
            }
        });
//
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent5 = new Intent(getApplicationContext(), SidesOnly.class);
//                startActivity(intent5);
//            }
//        });
//
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent6 = new Intent(getApplicationContext(), TowerProblem.class);
//                startActivity(intent6);
//            }
//        });
//
//        button7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent7 = new Intent(getApplicationContext(),Pythagorean.class);
//                startActivity(intent7);
//            }
//        });
    }
}
