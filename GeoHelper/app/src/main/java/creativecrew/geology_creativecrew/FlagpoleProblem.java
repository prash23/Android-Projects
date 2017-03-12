package creativecrew.geology_creativecrew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FlagpoleProblem extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    private Toolbar mToolbar;
    Button button;
    EditText angle1;
    EditText angle2;
    EditText side1;
    EditText side2;
    Double angleEA;
    Double angleBC;
    Double sideB;
    Double lengthD;
    Double angleAC;
    Double sideA;
    Double height;
    Spinner dropdown;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flagpole_problem);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE TRIGONOMETRIC PROBLEM");
        button = (Button) findViewById(R.id.clickme);
        angle1 = (EditText) findViewById(R.id.enterEA);
        angle2 = (EditText) findViewById(R.id.enterBC);
        side1 = (EditText) findViewById(R.id.enterB);
        side2 = (EditText) findViewById(R.id.enterD);

        dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"ft","in", "mi", "km"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(angle1.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter angle EA", Toast.LENGTH_LONG).show();
                    angle1.setError("Please enter angle EA");
                    return;
                }
                else
                {
                    angleEA = Double.parseDouble(angle1.getText().toString());

                    if (angleEA <=0 || angleEA >=90)

                    {
                            Toast.makeText(getApplicationContext(), "Please enter an angle between 0 and 90", Toast.LENGTH_LONG).show();
                            angle1.setError("Please enter an angle between 0 and 90");
                            return;
                    }

                }

                if(angle2.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter angle BC", Toast.LENGTH_LONG).show();
                    angle2.setError("Please enter angle BC");
                    return;
                }
                else {
                    angleBC = Double.parseDouble(angle2.getText().toString());

                    if (angleBC <= 0 || angleBC >= 90)
                    {

                        Toast.makeText(getApplicationContext(), "Please enter  an angle between 0 and 90", Toast.LENGTH_LONG).show();
                        angle2.setError("Please enter an angle between 0 and 90");
                        return;

                    }
                }


                if(side1.getText().toString().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Please enter side B", Toast.LENGTH_LONG).show();
                    side1.setError("Please enter side B");
                    return;
                }


                else
                {
                    sideB = Double.parseDouble(side1.getText().toString());

                    if(sideB == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Side cannot be zero", Toast.LENGTH_LONG).show();
                        side1.setError("Side cannot be zero");
                        return;
                    }
                }


                if(side2.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter length of D", Toast.LENGTH_LONG).show();
                    side2.setError("Please enter length of D");
                    return;
                }
                else
                {
                    lengthD = Double.parseDouble(side2.getText().toString());

                    if (lengthD == 0 )
                    {
                        Toast.makeText(getApplicationContext(), "Side cannot be zero", Toast.LENGTH_LONG).show();
                        side2.setError("Side cannot be zero");
                        return;
                    }
                }

                angleAC = 90-angleBC+angleEA;
                sideA = (Math.sin(Math.toRadians(angleBC))/Math.sin(Math.toRadians(angleAC)))*sideB;
                height = sideA+lengthD;
                final String unit_selected = dropdown.getSelectedItem().toString();
                Intent intent = new Intent(FlagpoleProblem.this, TestAcute.class);
//                intent.putExtra("polehgt",height);
                intent.putExtra("valEA",angle1.getText().toString());
                intent.putExtra("valBC",angle2.getText().toString());
                intent.putExtra("valB",side1.getText().toString());
                intent.putExtra("valD",side2.getText().toString());
               intent.putExtra("units",unit_selected);

                startActivityForResult(intent,REQUEST_CODE);
            }
        });

    }
}
