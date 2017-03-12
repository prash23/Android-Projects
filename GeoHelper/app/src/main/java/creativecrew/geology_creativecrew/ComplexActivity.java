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
public class ComplexActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    private Toolbar mToolbar;
    Button button;
    EditText side;
    EditText angABC;
    EditText angACD;
    Double knownSide;
    Double angle1;
    Double angle2;
    Double radianOfAngle1;
    Double radianOfAngle2;
    Double sideCD;
    Double h;
    Spinner dropdown;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("SIMPLE TRIGONOMETRIC PROBLEM");
        button = (Button) findViewById(R.id.clickme);
        angABC = (EditText) findViewById(R.id.enterABC);
        angACD = (EditText) findViewById(R.id.enterACD);
        side = (EditText) findViewById(R.id.enterBC);

        dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"ft","in", "mi", "km"};
       adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(side.getText().toString().length() == 0) {
                   Toast.makeText(getApplicationContext(),"Please enter side BC", Toast.LENGTH_LONG).show();
                   side.setError("Please enter side BC");
                   return;
               }
               else {
                   knownSide = Double.parseDouble(side.getText().toString());
                   if (knownSide==0){
                       Toast.makeText(getApplicationContext(),"Side cannot be zero", Toast.LENGTH_LONG).show();
                       side.setError("Side cannot be zero");
                       return;
                   }
               }

               if(angABC.getText().toString().length() == 0) {
                   Toast.makeText(getApplicationContext(),"Please enter angle ABC", Toast.LENGTH_LONG).show();
                   angABC.setError("Please enter angle ABC");
                   return;
               }
               else {
                   angle1 = Double.parseDouble(angABC.getText().toString());
                   if (angle1==0 || angle1 >=90){
                       Toast.makeText(getApplicationContext(),"Please enter an angle between 0 and 90", Toast.LENGTH_LONG).show();
                       angABC.setError("Please enter an angle between 0 and 90");
                       return;
                   }
               }

               if(angACD.getText().toString().length() == 0) {
                   Toast.makeText(getApplicationContext(),"Please enter angle ACD", Toast.LENGTH_LONG).show();
                   angACD.setError("Please enter angle ACD");
                   return;
               }
               else {
                   angle2 = Double.parseDouble(angACD.getText().toString());
                   if (angle2==0 || angle2 >=90){
                       Toast.makeText(getApplicationContext(),"Please enter an angle between 0 and 90", Toast.LENGTH_LONG).show();
                       angACD.setError("Please enter an angle between 0 and 90");
                       return;
                   }
               }
               if (angle1 >= angle2)
               {
                   Toast.makeText(getApplicationContext(),"Angle of ACD should be greater than angle of ABC", Toast.LENGTH_LONG).show();
                   angACD.setError("Please enter angle ACD");
                   return;
               }

               radianOfAngle1 = Math.toRadians(angle1);
               radianOfAngle2 = Math.toRadians(angle2);

               h = ((knownSide*Math.tan(radianOfAngle1)*Math.tan(radianOfAngle2))/(Math.tan(radianOfAngle2)-Math.tan(radianOfAngle1)));
               final String unit_selected = dropdown.getSelectedItem().toString();
               Intent intent = new Intent(getApplicationContext(), TestComplex.class);
//               intent.putExtra("ans", h);
//               intent.putExtra("angleABC1",angle1);
//               intent.putExtra("angleACD1", angle2);
//               intent.putExtra("sideKS",knownSide);
                 intent.putExtra("units",unit_selected);
               intent.putExtra("angleABC",angABC.getText().toString());
               intent.putExtra("angleACD",angACD.getText().toString());
               intent.putExtra("side",side.getText().toString());
               startActivityForResult(intent,REQUEST_CODE);


           }
       });

    }
}
