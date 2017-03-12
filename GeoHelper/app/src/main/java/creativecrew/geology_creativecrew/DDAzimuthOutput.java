package creativecrew.geology_creativecrew;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class DDAzimuthOutput extends AppCompatActivity {
    protected  String inputVariable;
    protected  int strikeAngle;
    protected String strikeDirections;
    protected  int dipAngle;
    protected  String dipDirections;
    private Toolbar mToolbar;
    private int position = 0;
    Paint paint = new Paint();
    Path path1 = new Path();

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_plan_view_output);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("Dip/Dip Azimuth");

        Spinner s = (Spinner) findViewById(R.id.spinner1);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int pos, long id) {

                if (position == pos) {
                    System.out.println("Item not changed so no need to refresh activity");
                } else {

                    if (pos == 0) {
                        position = 0;
                        System.out.println("In Spinner Working Pos = 0");

                    } else if (pos == 1) {
                        position = 1;
                        System.out.println("In Spinner Working Pos = 1");

                    } else if (pos == 2) {
                        position = 2;
                        System.out.println("In Spinner Working Pos = 2");

                    } else if (pos == 3) {
                        position = 3;
                        System.out.println("In Spinner Working Pos = 3");

                    } else if (pos == 4) {
                        position = 4;
                        System.out.println("In Spinner Working Pos = 4");


                    }


                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


            }});



        Intent submitInputText = getIntent();
        inputVariable = submitInputText.getStringExtra("text");

        String[] inputDirectionAngle = inputVariable.split(",");
        System.out.println("inputvariable after spliting"+inputDirectionAngle[0]+"dip "+inputDirectionAngle[1]);

        dipAngle = Integer.parseInt(inputDirectionAngle[1]);


        // Frame for drawing
        FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
        coordinateView myDrawing = new coordinateView(this);
        frame.addView(myDrawing);


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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        CoordinatorLayout simplePlanOutput = (CoordinatorLayout) findViewById(R.id.simplePlanOutput);
        switch (item.getItemId()) {
            case R.id.save:
                Snackbar snackbar = Snackbar
                        .make(simplePlanOutput, "output saved successfully", Snackbar.LENGTH_LONG);
                snackbar.show();

                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private class coordinateView extends View {

        Path path = new Path();

        public coordinateView(Context context) {
            super(context);
        }





        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);



            paint.setColor(Color.RED);
            paint.setStrokeWidth(3);
            canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), paint);
            canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
            paint.setColor(Color.BLACK);

            //Strike line

            int strikeA = dipAngle + 90;

            // Dip line

            int dipA = 0;
            dipA = dipAngle;

            //Dip
            // x = x+90;

            if (position == 1) {
                canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2 + (float) (60 * (Math.cos((270 * (Math.PI / 180)) + (dipA * (Math.PI / 180))))), getHeight() / 2 + (float) (60 * Math.sin((270 * (Math.PI / 180)) + (dipA * Math.PI / 180))), paint);
            }
            int triangle = strikeA;
            if(strikeA > 180){
                 triangle = strikeA+180;
            }

            if(position == 2 || position == 3)
            {

                // Paint paint1 = new Paint();
                float startX = getWidth()/2 ;
                float startY = getHeight() / 2;

                path.moveTo(startX, startY);


                int angle1 = triangle;




                float endX   = (float) (startX + 30 * Math.cos((triangle+270) * Math.PI / 180));
                float endY   = (float) (startY + 30 * Math.sin((triangle+270) * Math.PI / 180));

                path.lineTo(endX,endY);

                //  int[] angle = new int[5];



                //String[] c = {"RED","GREEN","BLUE","YELLOW"};



                for(int i=1;i<4;i++) {

                    int pace = 60;
                    if( i == 3)
                    {
                        pace = 30;
                    }
                    angle1 = angle1 + 120;


                    path.lineTo((float) (endX + (pace) * Math.cos((angle1 + 270) * Math.PI / 180)), (float) (endY + pace * Math.sin((angle1 + 270) * Math.PI / 180)));

                    endX = (float) (endX + (pace) * Math.cos((angle1 + 270) * Math.PI / 180));
                    endY = (float) (endY + (pace) * Math.sin((angle1 + 270) * Math.PI / 180));


                }



                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(3);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                //paint.setPathEffect(new DashPathEffect(new float[]{(float) 10, (float) 10}, 0));
                canvas.drawPath(path, paint);



                //paint.setColor(Color.BLUE);




            }
            if(position == 4)
            {
                if(position == 4)
                {
                    float startX = getWidth()/2 ;
                    float startY = getHeight() / 2;

                    path.moveTo(startX, startY);


                    int angle1 = triangle;




                    float endX   = (float) (startX + 30 * Math.cos((triangle+270) * Math.PI / 180));
                    float endY   = (float) (startY + 30 * Math.sin((triangle+270) * Math.PI / 180));

                    path.lineTo(endX,endY);

                    //  int[] angle = new int[5];



                    //String[] c = {"RED","GREEN","BLUE","YELLOW"};



                    for(int i=1;i<5;i++) {

                        int pace = 60;
                        if( i == 4)
                        {
                            pace = 30;
                        }
                        angle1 = angle1 + 90;


                        path.lineTo((float) (endX + (pace) * Math.cos((angle1 + 270) * Math.PI / 180)), (float) (endY + pace * Math.sin((angle1 + 270) * Math.PI / 180)));

                        endX = (float) (endX + (pace) * Math.cos((angle1 + 270) * Math.PI / 180));
                        endY = (float) (endY + (pace) * Math.sin((angle1 + 270) * Math.PI / 180));


                    }



                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(3);
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                    //paint.setPathEffect(new DashPathEffect(new float[]{(float) 10, (float) 10}, 0));
                    canvas.drawPath(path, paint);
                }

            }

                //actual line
                canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2 + (float) (200 * (Math.cos((270 * (Math.PI / 180)) + (strikeA * (Math.PI / 180))))), getHeight() / 2 + (float) (200 * Math.sin((270 * (Math.PI / 180)) + (strikeA * Math.PI / 180))), paint);
                //opposite line
                canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2 + (float) (200 * (Math.cos((270 * (Math.PI / 180)) + ((strikeA + 180) * (Math.PI / 180))))), getHeight() / 2 + (float) (200 * Math.sin((270 * (Math.PI / 180)) + ((strikeA + 180) * Math.PI / 180))), paint);






                // set notations
                paint.setTextSize(50);
            canvas.drawTextOnPath(inputVariable.toUpperCase(), path1, 450, 1600, paint);
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                this.invalidate();


            }
        }




    }



