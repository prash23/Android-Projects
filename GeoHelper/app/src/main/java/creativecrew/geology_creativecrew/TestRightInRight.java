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

import static creativecrew.geology_creativecrew.ThreepointOutput.verifyStoragePermissions;

public class TestRightInRight extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    private Toolbar mToolbar;
    public String side1,side2,side3,units;
    public TestRightInRightOutput rightOutput;
    private int flag = 0;
    public Intent it;

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testrightinright);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("DRILL PROBLEM");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        it = getIntent();

        if(getIntent().hasExtra("valA"))
        {
            side1 = it.getStringExtra("valA");
            side2 = it.getStringExtra("valB");
            side3 = it.getStringExtra("valC");
            units =it.getStringExtra("units");
        }
        else
        {
            throw  new IllegalArgumentException("Activity cannot find  extras ");
        }
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
                verifyStoragePermissions(TestRightInRight.this);
                List<String> saveValues = new ArrayList<>();
                saveValues.add(it.getStringExtra("valA"));
                saveValues.add(it.getStringExtra("valB"));
                saveValues.add(it.getStringExtra("valC"));
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


        new AlertDialog.Builder(TestRightInRight.this).setView(formElementsView)
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
            File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GeoHelper/RightAngledTriangle");
            directory.mkdirs();
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
            File directory = new File(getExternalFilesDir("/GeoHelper/RightAngledTriangle"),s1+".txt");
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

    @Override
    protected void onResume() {
        rightOutput = (TestRightInRightOutput) findViewById(R.id.outputView);
        rightOutput.sideA = Double.parseDouble(side1);
        rightOutput.sideB = Double.parseDouble(side2);
        rightOutput.sideC = Double.parseDouble(side3);
        rightOutput.units = units;

        super.onResume();
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
        Intent intent  = new Intent();
        intent.putExtra("valA",side1);
        intent.putExtra("valB",side2);
        intent.putExtra("valC",side3);
        setResult(RESULT_OK,intent);
        super.finish();
    }



}
