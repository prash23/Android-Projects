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

public class TowerProblem extends AppCompatActivity {
    private Toolbar mToolbar;
    Button button;
    EditText angle1;
    EditText side1;
    EditText length;
    Double angleDA;
    Double sideA;
    Double lengthC;
    Double sideB;
    Double height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower_problem);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE TRIGONOMETRIC PROBLEM");
        button = (Button) findViewById(R.id.clickme);
        angle1 = (EditText) findViewById(R.id.enterDA);
        side1 = (EditText) findViewById(R.id.enterB);
        length = (EditText) findViewById(R.id.enterC);


        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"feet","inches", "miles", "kilometers"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        final String unit_selected = dropdown.getSelectedItem().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(angle1.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter angle DA", Toast.LENGTH_LONG).show();
                    angle1.setError("Please enter angle DA");
                    return;
                }
                else {
                    angleDA = Double.parseDouble(angle1.getText().toString());
                    if(angleDA <=0 || angleDA>=90)
                    {
                        Toast.makeText(getApplicationContext(), "Please enter an angle between 0 and 90", Toast.LENGTH_LONG).show();
                        angle1.setError("Please enter an angle between 0 and 90 ");
                        return;
                    }
                }

                if(side1.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter side A", Toast.LENGTH_LONG).show();
                    side1.setError("Please enter side A");
                    return;
                }
                else {
                    sideA = Double.parseDouble(side1.getText().toString());
                    if (sideA == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Side cannot be zero", Toast.LENGTH_LONG).show();
                        side1.setError("Side cannot be zero");
                        return;
                    }
                }

                if(length.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter length C", Toast.LENGTH_LONG).show();
                    length.setError("Please enter length C");
                    return;
                }
                else {
                    lengthC = Double.parseDouble(length.getText().toString());
                    if (lengthC==0)
                    {
                        Toast.makeText(getApplicationContext(), "Length cannot be zero", Toast.LENGTH_LONG).show();
                        length.setError("Length cannot be zero");
                        return;
                    }
                }

                sideB = sideA * Math.tan(Math.toRadians(angleDA));
                height = lengthC + sideB;

                Intent intent = new Intent(getApplicationContext(), TowerProblemOutput.class);
                intent.putExtra("hgt",height);
                intent.putExtra("valDA",angleDA);
                intent.putExtra("valA",sideA);
                intent.putExtra("valC",lengthC);
                intent.putExtra("units",unit_selected);
                startActivity(intent);





            }
        });




    }
}
