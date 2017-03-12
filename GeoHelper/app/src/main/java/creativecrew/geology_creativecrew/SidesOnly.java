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

public class SidesOnly extends AppCompatActivity {
    private Toolbar mToolbar;
    Button button;
    EditText side1;
    EditText side2;
    Double sideA;
    Double sideB;
    Double angleBC;
    Spinner dropdown;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sides_only);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE TRIGONOMETRIC PROBLEM");
        button = (Button) findViewById(R.id.clickme);
        side1 = (EditText) findViewById(R.id.enterA);
        side2 = (EditText) findViewById(R.id.enterB);

        dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"feet","inches", "miles", "kilometers"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(side1.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter side A", Toast.LENGTH_LONG).show();
                    side1.setError("Please enter Side A");
                    return;
                }
                else
                {
                    sideA = Double.parseDouble(side1.getText().toString());

                    if(sideA == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Side cannot be zero", Toast.LENGTH_LONG).show();
                        side1.setError("Side cannot be zero");
                        return;
                    }
                }

                if(side2.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter Side B", Toast.LENGTH_LONG).show();
                    side2.setError("Please enter Side B");
                    return;
                }
                else {
                    sideB = Double.parseDouble(side2.getText().toString());

                    if (sideB == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Side cannot be zero", Toast.LENGTH_LONG).show();
                        side2.setError("Side cannot be zero");
                        return;
                    }
                }

                angleBC = Math.asin(sideA/sideB);
                final String unit_selected = dropdown.getSelectedItem().toString();

                Intent intent = new Intent(getApplicationContext(), SidesOnlyOutput.class);
                intent.putExtra("angle",angleBC);
                intent.putExtra("valA",sideA);
                intent.putExtra("valB",sideB);
                intent.putExtra("units",unit_selected);
                startActivity(intent);


            }
        });


    }
}
