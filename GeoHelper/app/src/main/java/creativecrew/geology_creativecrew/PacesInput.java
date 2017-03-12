package creativecrew.geology_creativecrew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by S525026 on 11/14/2016.
 */
public class PacesInput extends AppCompatActivity {
    int count = 1;

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        // or call onBackPressed()
        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    public void finish()
    {
        Intent it  = new Intent();
//        it.putExtra("pointAValue",pointA);
//        it.putExtra("pointBValue",pointB);
//        it.putExtra("pointCValue",pointC);
//        it.putExtra("sideABValue",sideAB);
//        it.putExtra("sideACValue",sideAC);
//        it.putExtra("angleABValue",angleAB);
//        it.putExtra("angleACValue",angleAC);
//        setResult(RESULT_OK,it);
        super.finish();
    }
    String patternString1 = "^[NS][0-9][0-9]?[0-9]?[EW]$";
    Pattern pattern1 = Pattern.compile(patternString1, Pattern.CASE_INSENSITIVE);

    String patternString2 = "^[1-9][0-9]?$";
    Pattern pattern2 = Pattern.compile(patternString2, Pattern.CASE_INSENSITIVE);

    protected void onCreate(Bundle savedInstanceState) {
        boolean matches1;
        boolean matches2;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pacesinput);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("PACES");

        Button reset = (Button)findViewById(R.id.reset);
        Button submit = (Button) findViewById(R.id.submit);
        Button addLabel = (Button) findViewById(R.id.addButton);




        final EditText notation1 = (EditText) findViewById(R.id.notation1);
        final EditText notation2 = (EditText) findViewById(R.id.notation2);
        final EditText notation3 = (EditText) findViewById(R.id.notation3);
        final EditText notation4 = (EditText) findViewById(R.id.notation4);
        final EditText notation5 = (EditText) findViewById(R.id.notation5);

        final EditText pace1 = (EditText) findViewById(R.id.pace1);
        final EditText pace2 = (EditText) findViewById(R.id.pace2);
        final EditText pace3 = (EditText) findViewById(R.id.pace3);
        final EditText pace4 = (EditText) findViewById(R.id.pace4);
        final EditText pace5 = (EditText) findViewById(R.id.pace5);

        final TextView p1 = (TextView) findViewById(R.id.p1);
        final TextView p2 = (TextView) findViewById(R.id.p2);
        final TextView p3 = (TextView) findViewById(R.id.p3);
        final TextView p4 = (TextView) findViewById(R.id.p4);
        final TextView p5 = (TextView) findViewById(R.id.p5);

        final TextView leg1 = (TextView) findViewById(R.id.leg1);
        final TextView leg2 = (TextView) findViewById(R.id.leg2);
        final TextView leg3 = (TextView) findViewById(R.id.leg3);
        final TextView leg4 = (TextView) findViewById(R.id.leg4);
        final TextView leg5 = (TextView) findViewById(R.id.leg5);


        final TextView a1 = (TextView) findViewById(R.id.a1);
        final TextView a2 = (TextView) findViewById(R.id.a2);
        final TextView a3 = (TextView) findViewById(R.id.a3);
        final TextView a4 = (TextView) findViewById(R.id.a4);
        final TextView a5 = (TextView) findViewById(R.id.a5);


        notation2.setVisibility(View.GONE);
        notation3.setVisibility(View.GONE);
        notation4.setVisibility(View.GONE);
        notation5.setVisibility(View.GONE);

        pace2.setVisibility(View.GONE);
        pace3.setVisibility(View.GONE);
        pace4.setVisibility(View.GONE);
        pace5.setVisibility(View.GONE);

        p2.setVisibility(View.GONE);
        p3.setVisibility(View.GONE);
        p4.setVisibility(View.GONE);
        p5.setVisibility(View.GONE);

        a2.setVisibility(View.GONE);
        a3.setVisibility(View.GONE);
        a4.setVisibility(View.GONE);
        a5.setVisibility(View.GONE);

        leg2.setVisibility(View.GONE);
        leg3.setVisibility(View.GONE);
        leg4.setVisibility(View.GONE);
        leg5.setVisibility(View.GONE);




        addLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leg5.getVisibility() == View.VISIBLE)
                {
                    Toast.makeText(getApplicationContext(), "Maximum legs reached",Toast.LENGTH_LONG).show();
                }

                if(leg4.getVisibility() == View.VISIBLE)
                {
                    leg5.setVisibility(View.VISIBLE);
                    //d5.setVisibility(View.VISIBLE);
                    //spinner5.setVisibility(View.VISIBLE);
                    a5.setVisibility(View.VISIBLE);
                    notation5.setVisibility(View.VISIBLE);
                    p5.setVisibility(View.VISIBLE);
                    pace5.setVisibility(View.VISIBLE);
                    count = count + 1;
                }

                if(leg3.getVisibility() == View.VISIBLE)
                {
                    leg4.setVisibility(View.VISIBLE);
                    //d4.setVisibility(View.VISIBLE);
                    //spinner4.setVisibility(View.VISIBLE);
                    a4.setVisibility(View.VISIBLE);
                    notation4.setVisibility(View.VISIBLE);
                    p4.setVisibility(View.VISIBLE);
                    pace4.setVisibility(View.VISIBLE);
                    count = count + 1;
                }


                if(leg2.getVisibility() == View.VISIBLE )
                {
                    leg3.setVisibility(View.VISIBLE);
                    //d3.setVisibility(View.VISIBLE);
                    //spinner3.setVisibility(View.VISIBLE);
                    a3.setVisibility(View.VISIBLE);
                    notation3.setVisibility(View.VISIBLE);
                    p3.setVisibility(View.VISIBLE);
                    pace3.setVisibility(View.VISIBLE);
                    count = count + 1;
                }



                if(leg1.getVisibility() == View.VISIBLE)
                {
                    leg2.setVisibility(View.VISIBLE);
//                    d2.setVisibility(View.VISIBLE);
//                    spinner2.setVisibility(View.VISIBLE);
                    a2.setVisibility(View.VISIBLE);
                    notation2.setVisibility(View.VISIBLE);
                    p2.setVisibility(View.VISIBLE);
                    pace2.setVisibility(View.VISIBLE);

                }




            }
        });



    reset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pace1.setText("");
            pace1.setText("");
            pace2.setText("");
            pace3.setText("");
            pace4.setText("");
            pace5.setText("");
            notation1.setText("");
            notation2.setText("");
            notation3.setText("");
            notation4.setText("");
            notation5.setText("");
        }
    });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String[] notationArray = new String[5];
                String[] p = new String[5];

                String n1  = notation1.getText().toString();
                String n2 = notation2.getText().toString();
                String n3 = notation3.getText().toString();
                String n4 = notation3.getText().toString();
                String n5 = notation4.getText().toString();

                notationArray[0] = n1;
                notationArray[1] = n2;
                notationArray[2] = n3;
                notationArray[3] = n4;
                notationArray[4] = n5;

                String pp1 = pace1.getText().toString();
                String pp2 = pace2.getText().toString();
                String pp3 = pace3.getText().toString();
                String pp4 = pace4.getText().toString();
                String pp5 = pace5.getText().toString();


                p[0] = pp1;
                p[1] = pp2;
                p[2] = pp3;
                p[3] = pp4;
                p[4] = pp5;






                    if (notation1.getVisibility() == View.VISIBLE && n1.toString().length() == 0) {
                        System.out.println("Notation 1 == 0");
                        Toast.makeText(getApplicationContext(), "Please enter first notation value to proceed", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else if(n1.toString().length() != 0)
                    {
                        System.out.println("Notation 1 != 0");
                        Matcher matcher1 = pattern1.matcher(n1);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a proper first bearing to proceed for ex:S45E", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    if (pp1.toString().length() == 0) {
                        System.out.println("Pace1 == 0");
                        Toast.makeText(getApplicationContext(), "Please enter first pace value to proceed", Toast.LENGTH_LONG).show();
                        return;
                    }

                    else if(pp1.toString().length() != 0)
                    {
                        Matcher matcher1 = pattern2.matcher(pp1);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a pace value from 1 - 99 to proceed for ex:5", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    if (notation2.getVisibility() == View.VISIBLE && n2.toString().length() == 0) {



                        Toast.makeText(getApplicationContext(), "Please enter second notation value to proceed", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else if(n2.toString().length() != 0){
                        Matcher matcher1 = pattern1.matcher(n2);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a proper second bearing to proceed for ex:S45E", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    if (pace2.getVisibility() == View.VISIBLE && pp2.toString().length() == 0) {


                        Toast.makeText(getApplicationContext(), "Please enter second pace value to proceed", Toast.LENGTH_LONG).show();
                        return;
                    }

                    else if(pp2.toString().length() != 0)
                    {
                        Matcher matcher1 = pattern2.matcher(pp2);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a pace value from 1 - 99 to proceed for ex:5", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    if (notation3.getVisibility() == View.VISIBLE && n3.toString().length() == 0) {



                        Toast.makeText(getApplicationContext(), "Please enter third notation value to proceed", Toast.LENGTH_LONG).show();
                        return;
                    }
                   else if(n3.toString().length() != 0){

                        Matcher matcher1 = pattern1.matcher(n3);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a proper third bearing to proceed for ex:S45E", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                     if (pace3.getVisibility() == View.VISIBLE &&  pp3.toString().length() == 0) {


                        Toast.makeText(getApplicationContext(), "Please enter third pace value to proceed", Toast.LENGTH_LONG).show();
                        return;
                    }

                   else if(pp3.toString().length() != 0)
                    {
                        Matcher matcher1 = pattern2.matcher(pp3);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a pace value from 1 - 99 to proceed for ex:5", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                     if (notation4.getVisibility() == View.VISIBLE &&  n4.toString().length() == 0) {


                    Toast.makeText(getApplicationContext(), "Please enter fourth notation value to proceed", Toast.LENGTH_LONG).show();
                    return;
                }
                    else if( n4.toString().length() != 0)
                    {
                        Matcher matcher1 = pattern1.matcher(n4);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a proper fourth bearing to proceed for ex:S45E", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                 if (pace4.getVisibility() == View.VISIBLE && pp4.toString().length() == 0) {


                    Toast.makeText(getApplicationContext(), "Please enter fourth pace value to proceed", Toast.LENGTH_LONG).show();
                    return;
                }
                    else if(pp4.toString().length() != 0){
                        Matcher matcher1 = pattern2.matcher(pp4);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a pace value from 1 - 99 to proceed for ex:5", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                     if (notation5.getVisibility() == View.VISIBLE &&  n5.toString().length() == 0) {


                        Toast.makeText(getApplicationContext(), "Please enter fifth notation value to proceed", Toast.LENGTH_LONG).show();
                        return;
                    }

                    else if(n5.toString().length() != 0)
                    {
                        Matcher matcher1 = pattern1.matcher(n5);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a proper fifth bearing to proceed for ex:S45E", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                     if (pace5.getVisibility() == View.VISIBLE && pp5.toString().length() == 0) {

                        Toast.makeText(getApplicationContext(), "Please enter fifth pace value to proceed", Toast.LENGTH_LONG).show();
                        return;
                    }



                    else if(pp5.toString().length() != 0){
                        Matcher matcher1 = pattern2.matcher(pp5);
                        if (matcher1.matches() == false)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a pace value from 1 - 99 to proceed for ex:5", Toast.LENGTH_LONG).show();
                            return;
                        }}
//                        String patternString1 = "^[NS][0-9][0-9]?[0-9]?[EW]$";
//                        Pattern pattern1 = Pattern.compile(patternString1, Pattern.CASE_INSENSITIVE);
                        //Pattern pattern1 = Pattern.compile(patternString1);
//                        Matcher matcher1 = pattern1.matcher(n1);
//                        Matcher matcher2 = pattern1.matcher(n2);
//                        Matcher matcher3 = pattern1.matcher(n3);
//                        Matcher matcher4 = pattern1.matcher(n4);
//                        Matcher matcher5 = pattern1.matcher(n5);

                        //matcher1.matches();

//                        String patternString2 = "^[1-9][0-9]?$";
//                        Pattern pattern2 = Pattern.compile(patternString2, Pattern.CASE_INSENSITIVE);
                        //Pattern pattern2 = Pattern.compile(patternString2);
//                        Matcher m1 = pattern2.matcher(p1);
//                        Matcher m2 = pattern2.matcher(p2);
//                        Matcher m3 = pattern2.matcher(p3);
//                        Matcher m4 = pattern2.matcher(p4);
//                        Matcher m5 = pattern2.matcher(p5);
//                        //matcher1.matches();

//                        boolean match1 = matcher1.matches()  && matcher2.matches() && matcher3.matches()
//                                && matcher4.matches()  && matcher5.matches() ;
//
//                        boolean match2 = m1.matches()  && m2.matches() && m3.matches()
//                                && m4.matches() && m5.matches() ;

//                        if (matcher1.matches() == false || matcher2.matches() == false || matcher3.matches() == false
//                                || matcher4.matches() == false || matcher5.matches() == false) {
//                            Toast.makeText(getApplicationContext(), "Please enter a proper bearing to proceed for ex:S45E", Toast.LENGTH_LONG).show();
//                            return;
//
//                        }
//
//                        if (m1.matches() == false || m2.matches() == false || m3.matches() == false
//                                || m4.matches() == false || m5.matches() == false) {
//                            Toast.makeText(getApplicationContext(), "Please enter a pace value from 1- 99 range for ex:5", Toast.LENGTH_LONG).show();
//                            return;
//                        }

                System.out.println("ALmost submitting");

                            Intent submitButtonIntent = new Intent(PacesInput.this, GeologistPaces.class);

                           // if (notation1.getVisibility() == View.VISIBLE && pace1.getVisibility() == View.VISIBLE) {
                                submitButtonIntent.putExtra("N1", notation1.getText().toString());
                                submitButtonIntent.putExtra("P1", pace1.getText().toString());
                            //}

                            //if (notation2.getVisibility() == View.VISIBLE && pace2.getVisibility() == View.VISIBLE)
                            //{
                                submitButtonIntent.putExtra("N2", notation2.getText().toString());
                            submitButtonIntent.putExtra("P2", pace2.getText().toString());
                        //}

//                            if (notation3.getVisibility() == View.VISIBLE && pace3.getVisibility() == View.VISIBLE) {
                                submitButtonIntent.putExtra("N3", notation3.getText().toString());
                                submitButtonIntent.putExtra("P3", pace3.getText().toString());
  //                          }
//
  //                          if (notation4.getVisibility() == View.VISIBLE && pace4.getVisibility() == View.VISIBLE) {
                                submitButtonIntent.putExtra("N4", notation4.getText().toString());
                                submitButtonIntent.putExtra("P4", pace4.getText().toString());
    //                        }

     //                       if (notation5.getVisibility() == View.VISIBLE && pace5.getVisibility() == View.VISIBLE) {
                                submitButtonIntent.putExtra("N5", notation5.getText().toString());
                                submitButtonIntent.putExtra("P5", pace5.getText().toString());
       //                     }

                System.out.println("data Sent, before start activity");
                            startActivity(submitButtonIntent);
                        }




        });
            }
}
