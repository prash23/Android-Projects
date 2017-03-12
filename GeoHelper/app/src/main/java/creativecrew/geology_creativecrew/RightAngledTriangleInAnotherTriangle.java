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
public class RightAngledTriangleInAnotherTriangle extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    private Toolbar mToolbar;
    Button button;
    EditText side1;
    EditText side2;
    EditText side3;
    Double knownSide1;
    Double knownSide2;
    Double knownSide3;
    Double angle1;
    Double unknownSide;
    ArrayAdapter<String> adapter;
    Spinner dropdown;
    public Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_angled_triangle_in_another_triangle);
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
        side3 = (EditText) findViewById(R.id.enterC);

        dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"ft","in", "mi", "km"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(side1.getText().toString().length() == 0) {
                   Toast.makeText(getApplicationContext(), "Please enter side A", Toast.LENGTH_LONG).show();
                   side1.setError("Please enter side A");
                   return;
               }
               else {
                   knownSide1 = Double.parseDouble(side1.getText().toString());

                   if(knownSide1 == 0)
                   {
                       Toast.makeText(getApplicationContext(), "side cannot be zero", Toast.LENGTH_LONG).show();
                       side1.setError("side cannot be zero");
                       return;
                   }
               }


               if(side2.getText().toString().length() == 0) {
                   Toast.makeText(getApplicationContext(), "Please enter side B", Toast.LENGTH_LONG).show();
                   side2.setError("Please enter side B");
                   return;
               }

               else {
                   knownSide2 = Double.parseDouble(side2.getText().toString());

                   if (knownSide2 == 0)
                   {
                       Toast.makeText(getApplicationContext(), "Side cannot be zero", Toast.LENGTH_LONG).show();
                       side2.setError("Side cannot be zero");
                       return;
                   }
               }


               if(side3.getText().toString().length() == 0) {
                   Toast.makeText(getApplicationContext(), "Please enter side C", Toast.LENGTH_LONG).show();
                   side3.setError("Please enter side C");
                   return;
               }
               else {
                   knownSide3 = Double.parseDouble(side3.getText().toString());
                   if (knownSide3 == 0)
                   {
                       Toast.makeText(getApplicationContext(), "Side cannot be zero", Toast.LENGTH_LONG).show();
                       side3.setError("Side cannot be zero");
                       return;
                   }

               }

               if (knownSide1 < knownSide2){
                   Toast.makeText(getApplicationContext(), "Side A should be greater than Side B ", Toast.LENGTH_LONG).show();
                   return;
               }


               unknownSide = (knownSide2*knownSide3)/(knownSide1-knownSide2);
               angle1 = Math.atan(knownSide2/unknownSide);
               final String unit_selected = dropdown.getSelectedItem().toString();

               Intent intent = new Intent(getApplicationContext(), TestRightInRight.class);
//               intent.putExtra("angle", angle1);
//               intent.putExtra("valA",knownSide1);
//               intent.putExtra("valB",knownSide2);
//               intent.putExtra("valC",knownSide3);
             intent.putExtra("units",unit_selected);

               intent.putExtra("valA",side1.getText().toString());
               intent.putExtra("valB",side2.getText().toString());
               intent.putExtra("valC",side3.getText().toString());
               startActivityForResult(intent,REQUEST_CODE);

               }
       });
    }

}
