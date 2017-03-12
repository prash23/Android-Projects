package creativecrew.geology_creativecrew;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by S525050 on 10/12/2016.
 */
public class ThreepointOutput extends AppCompatActivity {
    private static final String TAG = "ERROR";
    private Output output_threepoint;
    public String pointA, pointB, pointC, sideAB, sideAC, angleAB, angleAC;
    private Toolbar mToolbar;
    private int flag = 0;
    TextView strikevalue,dipvalue;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threepoint_output);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("THREEPOINT PROBLEM");



        Intent it = getIntent();
        if (getIntent().hasExtra("pointAValue")) {
            pointA = it.getStringExtra("pointAValue");
            pointB = it.getStringExtra("pointBValue");
            pointC = it.getStringExtra("pointCValue");
            sideAB = it.getStringExtra("sideABValue");
            sideAC = it.getStringExtra("sideACValue");
            angleAB = it.getStringExtra("angleABValue");
            angleAC = it.getStringExtra("angleACValue");
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras ");
        }

    }

    @Override
    protected void onResume() {
        output_threepoint = (Output) findViewById(R.id.outputView);
        output_threepoint.A = Double.parseDouble(pointA);
        output_threepoint.B = Double.parseDouble(pointB);
        output_threepoint.C = Double.parseDouble(pointC);
        output_threepoint.AB = Double.parseDouble(sideAB);
        output_threepoint.AC = Double.parseDouble(sideAC);
        output_threepoint.angleAB = Integer.parseInt((angleAB));
        output_threepoint.angleAC = Integer.parseInt((angleAC));
//        strikevalue.setText((int) output_threepoint.strike);
//        dipvalue.setText((int) output_threepoint.dip);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        final CoordinatorLayout threepointView = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        switch (item.getItemId()) {
            case R.id.save:
                verifyStoragePermissions(ThreepointOutput.this);
                List<String> saveValues = new ArrayList<>();
                saveValues.add(String.valueOf(output_threepoint.A));
                saveValues.add(String.valueOf(output_threepoint.B));
                saveValues.add(String.valueOf(output_threepoint.C));
                saveValues.add(String.valueOf(output_threepoint.AB));
                saveValues.add(String.valueOf(output_threepoint.AC));
                saveValues.add(String.valueOf(output_threepoint.angleAB));
                saveValues.add(String.valueOf(output_threepoint.angleAC));
                alertFormElements(saveValues);
               if (flag == 1)
               {
                   Snackbar snackbar = Snackbar
                           .make(threepointView, "The output is saved", Snackbar.LENGTH_LONG);
                   snackbar.show();
               }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void alertFormElements(final List<String> saveValues) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.form_elements,null, false);


        new AlertDialog.Builder(ThreepointOutput.this).setView(formElementsView)
                .setTitle("Enter File name")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            final EditText fileNameAlert = (EditText) formElementsView.findViewById(R.id.fileName);
                           if (createDirectoryAndSaveFile(fileNameAlert.getText().toString(),saveValues))
                           {
                               flag = 1;
                           }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dialog.cancel();
                    }

                }).show();
    }



    private boolean createDirectoryAndSaveFile(String s1,List<String> data) throws IOException {
        if (checkExternalMedia())
        {
            File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GeoHelper/threepoint");
            directory.mkdirs();
            System.out.println("this should not work");
            File file = new File(directory, s1+".txt");
            file.createNewFile();
            try {
                OutputStream fOut = new FileOutputStream(file);
                OutputStreamWriter oswName = new OutputStreamWriter(fOut);
                String newline = "\r\n";
                for(String s : data)
                {
                    oswName.write(s);
                    oswName.write(newline);

                    oswName.flush();
                }
                oswName.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        else
        {
            File directory = new File(getExternalFilesDir("/GeoHelper/threepoint"),s1+".txt");
            try {
                OutputStream fOut = new FileOutputStream(directory);
                OutputStreamWriter oswName = new OutputStreamWriter(fOut);
                String newline = "\r\n";
                for(String s : data)
                {
                    oswName.write(s);
                    oswName.write(newline);

                    oswName.flush();
                }
                oswName.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
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
        it.putExtra("pointAValue",pointA);
        it.putExtra("pointBValue",pointB);
        it.putExtra("pointCValue",pointC);
        it.putExtra("sideABValue",sideAB);
        it.putExtra("sideACValue",sideAC);
        it.putExtra("angleABValue",angleAB);
        it.putExtra("angleACValue",angleAC);
        setResult(RESULT_OK,it);
        super.finish();
    }

    private boolean checkExternalMedia() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
          return true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
           return false;
        } else {
            // Can't read or write
           return false;
        }
    }
}
