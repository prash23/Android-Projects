package creativecrew.geology_creativecrew;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SimplePlanViewOutput extends AppCompatActivity {

    protected String inputVariable = "137";
    protected  int angle;
    protected String directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_plan_view_output);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //\d is the character class for digits;
        String[] splitString = (inputVariable.split("(?<=\\d)|(?=\\d)"));

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(inputVariable);

        // Find all matches
        String match = " ";
        while (matcher.find()) {
            // Get the matching string
             match = matcher.group();
            System.out.println("my match"+match+" ");
        }

        angle = Integer.parseInt(match);
        directions = splitString[0]+splitString[splitString.length-1];
        // Frame for drawing
        FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
        coordinateView myDrawing = new coordinateView(this);
        frame.addView(myDrawing);

    }


    private class coordinateView extends View {

        Path path = new Path();

        public coordinateView(Context context) {
            super(context);
        }



        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onDraw(Canvas canvas){
           super.onDraw(canvas);






            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(8);
            canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight(),paint);
            canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,paint);
            paint.setStrokeWidth(3);
            paint.setColor(Color.BLUE);



            //angle
            int x = angle;

            if (directions.equals("NW")){
                x = x;
            }
            else if(directions.equals(("SW"))){
                x = x+90;
            }
            else if(directions.equals("SE")){
                x = 360 - x; // 43+ x = 360 => x = 227
            }

            //actual line
            canvas.drawLine(getWidth()/2,getHeight()/2,getWidth()/2 + (float)(200* (Math.cos((270*(Math.PI /180)) + (x*(Math.PI /180))))), getHeight()/2 + (float)(200*Math.sin((270*(Math.PI /180))+(x*Math.PI /180))),paint);
            //opposite line
           canvas.drawLine(getWidth()/2,getHeight()/2,getWidth()/2 + (float)(200* (Math.cos((270*(Math.PI /180)) + ((x+180)*(Math.PI /180))))), getHeight()/2 + (float)(200*Math.sin((270*(Math.PI /180))+((x+180)*Math.PI /180))),paint);

            //object
            x = x+90;
            canvas.drawLine(getWidth()/2,getHeight()/2,getWidth()/2 + (float)(100* (Math.cos((270*(Math.PI /180)) + (x*(Math.PI /180))))), getHeight()/2 + (float)(100*Math.sin((270*(Math.PI /180))+(x*Math.PI /180))),paint);
            // set notations
            paint.setTextSize(50);
            canvas.drawTextOnPath("N45S", path, 100, 100, paint);
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            this.invalidate();

// set notations
            paint.setTextSize(50);
            canvas.drawTextOnPath("N45S", path, 100, 100, paint);
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            this.invalidate();

        }




    }

    }

