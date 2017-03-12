package creativecrew.geology_creativecrew;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static creativecrew.geology_creativecrew.R.id.notation1;

//import static creativecrew.geology_creativecrew.R.id.paces;

public class GeologistPaces extends AppCompatActivity {

//    protected String inputDirections = "N5S";
//    protected String inputPaces = "19";
    protected  String notationAngle;
    protected String notationDirections;
    ArrayList<Integer> drawingAngle = new ArrayList<Integer>();
    ArrayList<String> pace = new ArrayList<String>();
    ArrayList<String> drawingDirection = new ArrayList<String>();
    Paint paint = new Paint();
    ArrayList<String> nAngle = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geologist_paces);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) mToolbar.findViewById(R.id.actionbar_textview);
        title.setText("PACES");

        System.out.println("Try now");

        Intent submitInputText = getIntent();
        String notation1 = submitInputText.getStringExtra("N1");
        String notation2 = submitInputText.getStringExtra("N2");
        String notation3 = submitInputText.getStringExtra("N3");
        String notation4 = submitInputText.getStringExtra("N4");
        String notation5 = submitInputText.getStringExtra("N5");

        String pace1 = submitInputText.getStringExtra("P1");
        String pace2 = submitInputText.getStringExtra("P2");
        String pace3 = submitInputText.getStringExtra("P3");
        String pace4 = submitInputText.getStringExtra("P4");
        String pace5 = submitInputText.getStringExtra("P5");

//        Button home = (Button) findViewById(R.id.homeButton);



//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(GeologistPaces.this, Categories.class));
//            }});
        //System.out.println("Rajesh Try now" + notation2.isEmpty()+"--");




       //int[] drawingAngle = new int[5];
        nAngle.add(notation1);

            if(notation2.isEmpty()) {
                System.out.println("notation2 == '' ");
            }
        else{
                nAngle.add(notation2);

            }
        if(notation3.isEmpty()) {
            System.out.println("notation3 == '' ");
        }
        else
        {
            nAngle.add(notation3);

        }

        if(notation4.isEmpty()) {
            System.out.println("notation4 == '' ");
        }
        else
        {
            nAngle.add(notation4);

        }

        if(notation5.isEmpty()) {
            System.out.println("notation5 == '' ");
        }
        else
        {
            nAngle.add(notation5);

        }

        for(String n : nAngle)
        {
            System.out.println("Notation" +n);
        }


        pace.add(pace1);

        if(pace2.isEmpty() == false) {
            pace.add(pace2);

        }
        if(pace3.isEmpty() == false) {
            pace.add(pace3);

        }
        if(pace4.isEmpty() == false) {
            pace.add(pace4);

        }

        if(pace5.isEmpty() == false) {
            pace.add(pace5);

        }


        for(String p : pace)
        {
            System.out.println("Paces"+p);
        }



        for(String nang : nAngle) {
            String[] splitStringStrike = (nang.split("(?<=\\d)|(?=\\d)"));
            Pattern patternStrike = Pattern.compile("[0-9]+");
            Matcher matcherStrike = patternStrike.matcher(nang);

            // Find all matches
            String matchStrike = " ";
            while (matcherStrike.find()) {
                // Get the matching string
                matchStrike = matcherStrike.group();
                System.out.println("strike match" + matchStrike + " ");

            }

            notationAngle = matchStrike;
            notationDirections = splitStringStrike[0] + splitStringStrike[splitStringStrike.length - 1];
            drawingAngle.add(Integer.parseInt(notationAngle));
            drawingDirection.add(notationDirections);

            System.out.println("Angle = " + notationAngle + " Directions = " + notationDirections);

            // }
        }


        // Frame for drawing
        FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
        coordinateView myDrawing = new coordinateView(this);
        frame.addView(myDrawing);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp(){
        super.finish();
        // or call onBackPressed()
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
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

            canvas.save();
            ArrayList<Integer> angle = new ArrayList<Integer>();

            for(int i = 0; i<drawingDirection.size();i++) {
                if(drawingDirection.get(i).equalsIgnoreCase("NE"))
                {
                  angle.add(drawingAngle.get(i));
                }
                else if (drawingDirection.get(i).equalsIgnoreCase("NW")) {
                    angle.add(360 - drawingAngle.get(i)); //x
                } else if (drawingDirection.get(i).equalsIgnoreCase(("SW"))) {
                    angle.add(drawingAngle.get(i) + 180);//x+90
                } else if (drawingDirection.get(i).equalsIgnoreCase("SE")) {
                    angle.add(180 - drawingAngle.get(i));//360 - x; // 43+ x = 360 => x = 227
                }
            }


            float widthX = getWidth();
            System.out.println("Width"+widthX);
            float heightY = getHeight();
            System.out.println("Height"+heightY);
            float startX = getWidth()/2 ;
            float startY = getHeight() / 2;

            path.moveTo(startX, startY);


            int angle1;




            float endX   = (float) (startX + Integer.parseInt(pace.get(0))*20 * Math.cos((angle.get(0)+270) * Math.PI / 180));
            float endY   = (float) (startY + Integer.parseInt(pace.get(0))*20 * Math.sin((angle.get(0)+270) * Math.PI / 180));

            if(endX > widthX || endX < 0 || endY < 0 || endY > heightY)
            {
                Toast.makeText(getApplicationContext(), "The path goes beyond the screen, Please enter values which limit to the screen", Toast.LENGTH_LONG).show();
                finish();
//                startActivity(new Intent(GeologistPaces.this, PacesInput.class));
            }
            path.lineTo(endX,endY);

          //  int[] angle = new int[5];

            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            paint.setPathEffect(new DashPathEffect(new float[]{(float) 10, (float) 10}, 0));
           canvas.drawPath(path, paint);
            int[] c = {Color.BLACK,Color.GREEN,Color.BLUE,Color.MAGENTA};



            for(int i=1;i<angle.size();i++) {

                if(endX > widthX || endX < 0 || endY < 0 || endY > heightY)
                {
                    Toast.makeText(getApplicationContext(), "The path goes beyond the screen, Please enter values which limit to the screen", Toast.LENGTH_LONG).show();
                    finish();
//                    startActivity(new Intent(GeologistPaces.this, PacesInput.class));
                }

                Path path = new Path();
                angle1 = angle.get(i);
                path.moveTo(endX,endY);

                path.lineTo((float) (endX + (Integer.parseInt(pace.get(i)) *20) * Math.cos((angle1 + 270) * Math.PI / 180)), (float) (endY + (Integer.parseInt(pace.get(i)) *20) * Math.sin((angle1 + 270) * Math.PI / 180)));

                endX = (float) (endX + (Integer.parseInt(pace.get(i)) *20) * Math.cos((angle1 + 270) * Math.PI / 180));
                endY = (float) (endY + (Integer.parseInt(pace.get(i)) *20) * Math.sin((angle1 + 270) * Math.PI / 180));
//                if(endX > widthX || endX < 0 || endY < 0 || endY > heightY)
//                {
//                    Toast.makeText(getApplicationContext(), "The path goes beyond the screen, Please enter values which limit to the screen", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(GeologistPaces.this, PacesInput.class));
//                }
                paint.setColor(c[i-1]);
                paint.setStrokeWidth(10);
                paint.setStyle(Paint.Style.STROKE);
                paint.setPathEffect(new DashPathEffect(new float[]{(float) 10, (float) 10}, 0));
                canvas.drawPath(path, paint);

               }

            //setLayerType(View., null);
            //his.invalidate();

            canvas.restore();


        }


    }

}