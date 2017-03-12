package creativecrew.geology_creativecrew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimplePlanView extends AppCompatActivity {
    private Toolbar mToolbar;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



            setContentView(R.layout.activity_simple_plan_view);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
            title.setText("SIMPLE PLAN VIEW");
            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            Button quadrantOutput = (Button) findViewById(R.id.outputButton);

            Intent typeOfProblem = getIntent();
            final String buttonClicked = typeOfProblem.getStringExtra("text2");

            client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


            quadrantOutput.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
           if (buttonClicked.equals("Quadrant")) {

                            final EditText inputText = (EditText) findViewById(R.id.inputText);
                            String text = inputText.getText().toString();

                            if(text.toString().length() == 0)
                            {
                                Toast.makeText(getApplicationContext(), "Please enter value to proceed", Toast.LENGTH_LONG).show();
                                return;
                            }
                           else {
                                String patternString1 = "^[NS][0-9]{2}[WE],[0-9]{2}[NS][EW]$";
                                //   Pattern pattern1 = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
                                Pattern pattern1 = Pattern.compile(patternString1, Pattern.CASE_INSENSITIVE);
                                Matcher matcher1 = pattern1.matcher(text);
                                boolean matches1 = matcher1.matches();
                                System.out.println("my text" + text);
                                System.out.println(matches1);
                                if (matches1 == false) {
                                    Toast.makeText(getApplicationContext(), "Please enter value to proceed Ex: N45E,18SE", Toast.LENGTH_LONG).show();
                                    return;

                                } else {
                                    Intent submitButtonIntent = new Intent(SimplePlanView.this, QuadrantOutput.class);
                                    submitButtonIntent.putExtra("text", text);
                                    submitButtonIntent.putExtra("typesofproblem", buttonClicked);
                                    System.out.println("my simple plan view intent" + buttonClicked);
                                    startActivity(submitButtonIntent);
                                }
                            }

                        } else if (buttonClicked.equals("Azimuth")) {


               final EditText inputText = (EditText) findViewById(R.id.inputText);
               String text = inputText.getText().toString();

               if(text.toString().length() == 0)
               {
                   Toast.makeText(getApplicationContext(), "Please enter value to proceed", Toast.LENGTH_LONG).show();
                   return;
               }
               else {
                   String patternString1 = "^[0-9]{2}[0-9]?,[0-9]{2}[NS][EW]$";
                   //   Pattern pattern1 = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
                   Pattern pattern1 = Pattern.compile(patternString1, Pattern.CASE_INSENSITIVE);
                   Matcher matcher1 = pattern1.matcher(text);
                   boolean matches1 = matcher1.matches();
                   System.out.println("my text" + text);
                   System.out.println(matches1);
                   if (matches1 == false) {
                       Toast.makeText(getApplicationContext(), "Please enter value to proceed Ex: 05,18SE", Toast.LENGTH_LONG).show();
                       return;

                   } else {
                       Intent submitButtonIntent = new Intent(SimplePlanView.this, AzimuthOutput.class);
                       submitButtonIntent.putExtra("text", text);
                       submitButtonIntent.putExtra("typesofproblem", buttonClicked);
                       System.out.println("my simple plan view intent" + buttonClicked);

                       startActivity(submitButtonIntent);
                   }
               }


                        } else if (buttonClicked.equals("Dip/Dip Quadrant")) {

               final EditText inputText = (EditText) findViewById(R.id.inputText);
               String text = inputText.getText().toString();

               if(text.toString().length() == 0)
               {
                   Toast.makeText(getApplicationContext(), "Please enter value to proceed", Toast.LENGTH_LONG).show();
                   return;
               }
               else {
                   String patternString1 = "^[0-9]{2},[NS][0-9]{2}[EW]$";
                   //   Pattern pattern1 = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
                   Pattern pattern1 = Pattern.compile(patternString1, Pattern.CASE_INSENSITIVE);
                   Matcher matcher1 = pattern1.matcher(text);
                   boolean matches1 = matcher1.matches();
                   System.out.println("my text" + text);
                   System.out.println(matches1);
                   if (matches1 == false) {
                       Toast.makeText(getApplicationContext(), "Please enter value to proceed Ex: 18,S45E", Toast.LENGTH_LONG).show();
                       return;

                   } else {
                       Intent submitButtonIntent = new Intent(SimplePlanView.this, DDQuadrantOutput.class);
                       submitButtonIntent.putExtra("text", text);
                       submitButtonIntent.putExtra("typesofproblem", buttonClicked);
                       System.out.println("my simple plan view intent" + buttonClicked);
                       startActivity(submitButtonIntent);
                   }
               }



                        } else if (buttonClicked.equals("Dip/Dip Azimuth")) {
               final EditText inputText = (EditText) findViewById(R.id.inputText);
               String text = inputText.getText().toString();

               if(text.toString().length() == 0)
               {
                   Toast.makeText(getApplicationContext(), "Please enter value to proceed", Toast.LENGTH_LONG).show();
                   return;
               }
               else {

               String patternString1 = "^[0-9]{2},[0-9]{2}[0-9]?$";
               //   Pattern pattern1 = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
               Pattern pattern1 = Pattern.compile(patternString1, Pattern.CASE_INSENSITIVE);
               Matcher matcher1 = pattern1.matcher(text);
               boolean matches1 = matcher1.matches();
               System.out.println("my text" + text);
               System.out.println(matches1);
               if (matches1 == false) {
                   Toast.makeText(getApplicationContext(), "Please enter value to proceed Ex: 18,135", Toast.LENGTH_LONG).show();
                   return;

               } else {

                   Intent submitButtonIntent = new Intent(SimplePlanView.this, DDAzimuthOutput.class);
                   submitButtonIntent.putExtra("text", text);
                   submitButtonIntent.putExtra("typesofproblem", buttonClicked);
                   System.out.println("my simple plan view intent" + buttonClicked);
                   startActivity(submitButtonIntent);
               }
           }


                        } else {
                            Intent geologypaces = getIntent();
                            EditText inputText = (EditText) findViewById(R.id.inputText);
                            String text = inputText.getText().toString();
                            Intent submitButtonIntent = new Intent(SimplePlanView.this, PacesInput.class);
                            submitButtonIntent.putExtra("text", text);
                            submitButtonIntent.putExtra("typesofproblem", buttonClicked);
                            System.out.println("my simple plan view intent" + buttonClicked);
                            startActivity(submitButtonIntent);

                        }
                    }
                });
                }




    }



