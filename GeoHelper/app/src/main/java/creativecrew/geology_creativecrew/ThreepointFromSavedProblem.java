package creativecrew.geology_creativecrew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by S525050 on 10/29/2016.
 */
public class ThreepointFromSavedProblem extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    private Toolbar mToolbar;
    TextInputEditText pointA, pointB, pointC, sideAB, sideAC, angleAB, angleAC;
    Button submit;
    TextInputLayout input_pointA, input_pointB, input_pointC, input_sideAB, input_sideAC, input_angleAB, input_angleAC;
    public Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threepoint);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("THREEPOINT PROBLEM");
        initialiaze();

        it = getIntent();
        savedProblem();
    }
    @Override
    public boolean onSupportNavigateUp(){
        Intent it = new Intent(ThreepointFromSavedProblem.this,Categories.class);
        startActivity(it);
        super.finish();
        // or call onBackPressed()
        return true;
    }

    private void initialiaze() {
        pointA = (TextInputEditText) findViewById(R.id.pointA);
        pointB = (TextInputEditText) findViewById(R.id.pointB);
        pointC = (TextInputEditText) findViewById(R.id.pointC);
        sideAB = (TextInputEditText) findViewById(R.id.sideAB);
        sideAC = (TextInputEditText) findViewById(R.id.sideAC);
        angleAB = (TextInputEditText) findViewById(R.id.angleAB);
        angleAC = (TextInputEditText) findViewById(R.id.angleAC);
        input_pointA = (TextInputLayout) findViewById(R.id.input_layout_pointA);
        input_pointB = (TextInputLayout) findViewById(R.id.input_layout_pointB);
        input_pointC = (TextInputLayout) findViewById(R.id.input_layout_pointC);
        input_sideAB = (TextInputLayout) findViewById(R.id.input_layout_sideAB);
        input_sideAC = (TextInputLayout) findViewById(R.id.input_layout_sideAC);
        input_angleAB = (TextInputLayout) findViewById(R.id.input_layout_angleAB);
        input_angleAC = (TextInputLayout) findViewById(R.id.input_layout_angleAC);


        submit = (Button) findViewById(R.id.threepoint_submit);

        pointA.setFilters(new InputFilter[]{filter1});
        pointB.setFilters(new InputFilter[]{filter1});
        pointC.setFilters(new InputFilter[]{filter1});
        sideAB.setFilters(new InputFilter[]{filter1});
        sideAC.setFilters(new InputFilter[]{filter1});
        angleAB.setFilters(new InputFilter[]{filter2});
        angleAC.setFilters(new InputFilter[]{filter2});


        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void submit() {
        if (!(validate())) {
            return;
        } else {

            Intent it = new Intent(ThreepointFromSavedProblem.this, ThreepointOutput.class);
            it.putExtra("pointAValue",pointA.getText().toString());
            it.putExtra("pointBValue",pointB.getText().toString());
            it.putExtra("pointCValue",pointC.getText().toString());
            it.putExtra("sideABValue",sideAB.getText().toString());
            it.putExtra("sideACValue",sideAC.getText().toString());
            it.putExtra("angleABValue",angleAB.getText().toString());
            it.putExtra("angleACValue",angleAC.getText().toString());
            startActivityForResult(it,REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            pointA.setText((data.getStringExtra("pointAValue")));
            pointB.setText((data.getStringExtra("pointBValue")));
            pointC.setText((data.getStringExtra("pointCValue")));
            sideAB.setText((data.getStringExtra("sideABValue")));
        }
    }



    private boolean validate() {
        CoordinatorLayout threepointView = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        if (pointA.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar
                    .make(threepointView, R.string.error, Snackbar.LENGTH_LONG);
            snackbar.show();
            input_pointA.requestFocus();
            return false;
        }
        if (pointB.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar
                    .make(threepointView, R.string.error, Snackbar.LENGTH_LONG);
            snackbar.show();
            input_pointB.requestFocus();
            return false;
        }
        if (pointC.getText().toString().isEmpty() ) {
            Snackbar snackbar = Snackbar
                    .make(threepointView, R.string.error, Snackbar.LENGTH_LONG);
            snackbar.show();
            input_pointC.requestFocus();
            return false;
        }
        if (sideAB.getText().toString().isEmpty() ) {
            Snackbar snackbar = Snackbar
                    .make(threepointView, R.string.error, Snackbar.LENGTH_LONG);
            snackbar.show();
            input_sideAB.requestFocus();
            return false;
        }
        if (sideAC.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar
                    .make(threepointView, R.string.error, Snackbar.LENGTH_LONG);
            snackbar.show();
            input_sideAC.requestFocus();
            return false;
        }

        if (angleAB.getText().toString().isEmpty() || (angleAB.getText().toString().length() > 360)) {
            Snackbar snackbar = Snackbar
                    .make(threepointView, R.string.error, Snackbar.LENGTH_LONG);
            snackbar.show();
            input_angleAB.requestFocus();
            return false;
        }
        if (angleAC.getText().toString().isEmpty() || (angleAB.getText().toString().length() > 360)) {
            Snackbar snackbar = Snackbar
                    .make(threepointView, R.string.error, Snackbar.LENGTH_LONG);
            snackbar.show();
            input_angleAC.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    InputFilter filter1 = new InputFilter() {
        final int maxDigitsBeforeDecimalPoint = 4;
        final int maxDigitsAfterDecimalPoint = 2;

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            StringBuilder builder = new StringBuilder(dest);
            builder.replace(dstart, dend, source
                    .subSequence(start, end).toString());
            if (!builder.toString().matches(
                    "(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?"

            )) {
                if (source.length() == 0)
                    return dest.subSequence(dstart, dend);
                return "";
            }

            return null;

        }
    };

    InputFilter filter2 = new InputFilter() {
        final int maxDigitsBeforeDecimalPoint = 3;
        final int maxDigitsAfterDecimalPoint = 2;

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            StringBuilder builder = new StringBuilder(dest);
            builder.replace(dstart, dend, source
                    .subSequence(start, end).toString());
            if (!builder.toString().matches(
                    "(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?"

            )) {
                if (source.length() == 0)
                    return dest.subSequence(dstart, dend);
                return "";
            }

            return null;

        }
    };



    public void savedProblem()
    {
        if (getIntent().hasExtra("pointAValue")) {
            pointA.setText(it.getStringExtra("pointAValue"));
            pointB.setText(it.getStringExtra("pointBValue"));
            pointC.setText(it.getStringExtra("pointCValue"));
            sideAB.setText(it.getStringExtra("sideABValue"));
            sideAC.setText(it.getStringExtra("sideACValue"));
            angleAB.setText(it.getStringExtra("angleABValue"));
            angleAC.setText(it.getStringExtra("angleACValue"));
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras ");
        }
    }
}


