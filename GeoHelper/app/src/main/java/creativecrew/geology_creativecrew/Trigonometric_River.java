package creativecrew.geology_creativecrew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by S525050 on 10/8/2016.
 */
public class Trigonometric_River extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    private Toolbar mToolbar;
    Button button;
    EditText side;
    EditText angle;
    Double side1;
    Double angle1;
    Double perpendicular;
    Double radian;
    Spinner dropdown;
    ArrayAdapter<String> adapter;
    //Double a = (Double) angle;
    //Double unknownSide = Math.tan(angle)*side;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.trigonometric_river);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE TRIGONOMETRIC PROBLEM");

        button = (Button) findViewById(R.id.Answer);
        side = (EditText) findViewById(R.id.enterB);

        angle = (EditText) findViewById(R.id.enterBC);

        dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"ft","in", "mi", "km"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(side.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(),"Please enter side B", Toast.LENGTH_LONG).show();
                    side.setError("Please enter side B");
                    return;
                }
                else {
                    side1 = Double.parseDouble(side.getText().toString());
                    if(side1 == 0)
                    {
                        Toast.makeText(getApplicationContext(),"Side cannot be zero", Toast.LENGTH_LONG).show();
                        side.setError("Side cannot be zero");
                        return;
                    }
                }

                if(angle.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter angle BC", Toast.LENGTH_LONG).show();
                    angle.setError("Please enter angle BC");
                    return;
                }
                else {
                    angle1 = Double.parseDouble(angle.getText().toString());

                    if(angle1 <=0 || angle1 >=90)
                    {
                        Toast.makeText(getApplicationContext(), "Please enter an angle between 0 and 90", Toast.LENGTH_LONG).show();
                        angle.setError("Please enter an angle between 0 and 90");
                        return;
                    }
                }
                //radian = (angle1*3.1415)/180;
                radian = Math.toRadians(angle1);
                final String unit_selected = dropdown.getSelectedItem().toString();
                perpendicular = (Double) side1*Math.tan(radian);


//                Intent intent = new Intent(Trigonometric_River.this,Trigonometry_RiverOutput.class);
//                intent.putExtra("sv", perpendicular);
//                intent.putExtra("valB",side1);
//                intent.putExtra("valBC",angle1);
//                intent.putExtra("units",unit_selected);
//                startActivity(intent);



                Intent intent = new Intent(Trigonometric_River.this,TestSimpletrigOutput.class);
                intent.putExtra("side1", side.getText().toString());
                intent.putExtra("angle1", angle.getText().toString());
                intent.putExtra("units",unit_selected);
                startActivityForResult(intent,REQUEST_CODE);




            }
        });



    }
}
