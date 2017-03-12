package creativecrew.geology_creativecrew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private static final int CREATE_REQUEST_CODE = 40;
    private static final int OPEN_REQUEST_CODE = 41;
    private static final int SAVE_REQUEST_CODE = 42;
    public String filename;
    public List<String> recentProblem = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("GEO HELPER");
        Button btn_catgories = (Button) findViewById(R.id.button_categories);
        final TextView openRecent = (TextView) findViewById(R.id.openRecent);

        btn_catgories.setOnClickListener(new Button.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent categories_page = new Intent(HomeActivity.this,Categories.class);
                startActivity(categories_page);
            }
        });
        openRecent.setOnClickListener(new TextView.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                openRecent();
            }
        });
    }

    private void openRecent() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        startActivityForResult(intent, OPEN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

       if (requestCode == OPEN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
           filename = data.getDataString();
           int lastIndex = 0;
           int count = 0;
           String trimmedString1, trimmedString2, trimmedString3;

           trimmedString1 = filename.replace("%3A", File.separator);
           trimmedString2 = trimmedString1.replace("%2F", File.separator);
           trimmedString3 = trimmedString2.replace("%2F", File.separator);

           while (lastIndex != -1) {

               lastIndex = trimmedString3.indexOf(File.separator, lastIndex);

               if (lastIndex != -1) {
                   count++;
                   lastIndex += File.separator.length();
               }
           }
           String trimmedString;
           String problemType = "";
           trimmedString = trimmedString3.substring(trimmedString3.indexOf("/com"));
           if (trimmedString.contains("threepoint")) {
               problemType = "threepoint";
           }
           else if (trimmedString.contains("RightAngledTriangle")) {
               problemType = "RightAngledTriangle";
           }
           else if (trimmedString.contains("AcuteTriangle")) {
               problemType = "AcuteTriangle";
           }
           else if (trimmedString.contains("Complex")) {
               problemType = "Complex";
           }
           else if (trimmedString.contains("riverproblem")) {
               problemType = "riverproblem";
           }
           String[] temp;
           temp = trimmedString.split(File.separator, count);
           for (String x : temp) {
               if (x.contains(".txt")) {
                   System.out.println(x);
                   try {

                       createDirectoryAndSaveFile(x, problemType);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
       }
       else
       {
           RelativeLayout rView = (RelativeLayout) findViewById(R.id.rView);
           Snackbar error = Snackbar.make(rView,"Please select a file",Snackbar.LENGTH_LONG);
           error.show();
       }


        super.onActivityResult(requestCode, resultCode, data);

    }

    private void createDirectoryAndSaveFile(String s1,String type) throws IOException {
        File file = null;
        if(type.equals("threepoint"))
        {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GeoHelper/threepoint",s1);
            if (!(file.isFile()))
            {
                file = new File(getExternalFilesDir("/GeoHelper/threepoint"),s1);
            }
        }
        else if(type.equals("RightAngledTriangle"))
        {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GeoHelper/RightAngledTriangle",s1);
            if (!(file.isFile()))
            {
                file = new File(getExternalFilesDir("/GeoHelper/RightAngledTriangle"),s1);
            }
        }
        else if(type.equals("AcuteTriangle"))
        {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GeoHelper/AcuteTriangle",s1);
            if (!(file.isFile()))
            {
                file = new File(getExternalFilesDir("/GeoHelper/AcuteTriangle"),s1);
            }
        }
        else if(type.equals("Complex"))
        {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GeoHelper/Complex",s1);
            if (!(file.isFile()))
            {
                file = new File(getExternalFilesDir("/GeoHelper/Complex"),s1);
            }
        }
        else if(type.equals("riverproblem"))
        {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GeoHelper/riverproblem",s1);
            if (!(file.isFile()))
            {
                file = new File(getExternalFilesDir("/GeoHelper/riverproblem"),s1);
            }
        }
        if (!(file == null))
        {

            try {
                InputStream fOut = new FileInputStream(file);
                InputStreamReader oswName = new InputStreamReader(fOut);
                BufferedReader buffreader = new BufferedReader ( oswName ) ;

                String readString = buffreader.readLine ( ) ;
                while ( readString != null ) {
                    recentProblem.add(readString);
                    readString = buffreader.readLine ( ) ;
                }
                System.out.println("i am working "+ recentProblem);
                oswName.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (type.equals("threepoint"))
            {
                Intent it = new Intent(HomeActivity.this, ThreepointFromSavedProblem.class);
                it.putExtra("pointAValue",recentProblem.get(0));
                it.putExtra("pointBValue",recentProblem.get(1));
                it.putExtra("pointCValue",recentProblem.get(2));
                it.putExtra("sideABValue",recentProblem.get(3));
                it.putExtra("sideACValue",recentProblem.get(4));
                it.putExtra("angleABValue",recentProblem.get(5));
                it.putExtra("angleACValue",recentProblem.get(6));
                startActivity(it);
            }
            else if (type.equals("RightAngledTriangle"))
            {
                Intent it = new Intent(HomeActivity.this, RightAngleTriangleFromSavedProblem.class);
                System.out.println(recentProblem.get(0));
                it.putExtra("valA",recentProblem.get(0));
                it.putExtra("valB",recentProblem.get(1));
                it.putExtra("valC",recentProblem.get(2));
                startActivity(it);
            }
            else if (type.equals("AcuteTriangle"))
            {
                Intent it = new Intent(HomeActivity.this, FlagPoleFromSavedProblem.class);
                System.out.println(recentProblem.get(0));
                it.putExtra("valEA",recentProblem.get(0));
                it.putExtra("valBC",recentProblem.get(1));
                it.putExtra("valB",recentProblem.get(2));
                it.putExtra("valD",recentProblem.get(2));
                startActivity(it);
            }
            else if (type.equals("Complex"))
            {
                Intent it = new Intent(HomeActivity.this, ComplexActivityFromSavedProblem.class);
                System.out.println(recentProblem.get(0));
                it.putExtra("side",recentProblem.get(0));
                it.putExtra("angleABC",recentProblem.get(1));
                it.putExtra("angleACD",recentProblem.get(2));
                startActivity(it);
            }
            else if (type.equals("riverproblem"))
            {
                Intent it = new Intent(HomeActivity.this, TrigonometricRiverFromSavedProblem.class);
                System.out.println(recentProblem.get(0));
                it.putExtra("side1",recentProblem.get(0));
                it.putExtra("angle1",recentProblem.get(1));
                startActivity(it);
            }
        }
        else
        {
            RelativeLayout rView = (RelativeLayout) findViewById(R.id.rView);
            Snackbar error = Snackbar.make(rView,"File is invalid",Snackbar.LENGTH_LONG);
            error.show();
        }
    }
}
