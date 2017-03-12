package creativecrew.geology_creativecrew;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by Ritwik on 12/7/2016.
 */

public class TestAcuteOutput extends  View{

    double angleEA, angleBC, sideB, sideD, answer, angleAC, sideA, sideP, sideX;
    String units;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.0f;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;
    private static final int INVALID_POINTER_ID = -1;

    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;
    public TestAcuteOutput(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScaleDetector = new ScaleGestureDetector(context, new TestAcuteOutput.ScaleListener());
    }
    protected void onDraw(Canvas canvas){

//        angleEA = 23;
//        angleBC = 29.5;
//        sideB = 1000;
//        sideD = 100;

        canvas.save();
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, mScaleFactor, this.mScaleDetector.getFocusX(), this.mScaleDetector.getFocusY());
        angleAC = 90-angleBC+angleEA;
        sideA = (Math.sin(Math.toRadians(angleBC))/Math.sin(Math.toRadians(angleAC)))*sideB;
        sideP = (Math.sin(Math.toRadians(angleEA)))*sideA;
        sideX = sideP/Math.tan(Math.toRadians(angleEA));
        System.out.println(sideA);
        System.out.println(sideP);
        System.out.println(sideX);
        super.onDraw(canvas);
        float x = getWidth();
        float y = getHeight();
        int radius;
        radius = 100;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);


//        canvas.drawLine(x / 2, 0, x / 2, y, paint);
//        canvas.drawLine(0,y/2,x,y/2,paint);


        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(4);
        paint2.setTextSize(60);
        canvas.drawPaint(paint2);


        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setColor(Color.RED);
        paint3.setStrokeWidth(0);
        paint3.setTextSize(30);
        canvas.drawPaint(paint3);

        Path triangle = new Path();
        triangle.moveTo(x / 4, y / 2);
        triangle.lineTo((float) (x/4+sideP),y/2);
        triangle.moveTo((float) (x/4+sideP),y/2);
       // triangle.lineTo((float) (x/4+sideP), (float) (y/2-sideX));
        triangle.moveTo((float) (x/4+sideP), (float) (y/2-sideX));

        //canvas.drawText("A", (float) (x/4+sideP/3), (float) (y/2-sideX/2), paint3);

        triangle.lineTo((float) (x/4+sideB),  y/2);
        triangle.moveTo((float) (x/4+sideB),  y/2);
        canvas.drawText("C",(float) (x/4+sideB-30), (float) (y/2-50), paint3);

        //canvas.drawText(String.valueOf(sideB)+units, (float) (x/4+sideB/3),  y/2+30, paint3);

        triangle.lineTo((float) (x/4+sideP),y/2);
        triangle.moveTo((float) (x/4+sideP), (float) (y/2-sideX));
        triangle.lineTo(x / 4, y / 2);
        triangle.moveTo(x/4,y/2);
        triangle.lineTo(x/4, (float) (y/2+sideD));
        triangle.moveTo(x/4,y/2);
        triangle.lineTo(x/4,(float) (y/2-sideA));
        double a = Math.round(sideA);
       // canvas.drawText(String.valueOf(a)+units, x/4-120, (float) (y/2-sideA/2), paint3);
        canvas.drawText("E", x/4+1, (float) (y/2-sideA/2), paint3);
        double d =Math.round(sideD);
       // canvas.drawText(String.valueOf(d)+units, x/4-120, (float) (y/2+sideD/2), paint3);
        canvas.drawText("D", x/4+1, (float) (y/2+sideD/2), paint3);
        double eb = 90 - angleEA;
        canvas.drawText("A",(float) (x/4+sideP), (float) (y/2-sideX), paint3);
        canvas.drawText("B",(float) (x/4+sideB/2),y/2-4, paint3);
       // canvas.drawText(String.valueOf(eb)+(char)0x00B0, x / 4+14, y / 2-10, paint3);
        //canvas.drawText(String.valueOf(angleBC)+(char)0x00B0, (float) (x/4+sideB-100),  y/2-10, paint3);
       // canvas.drawText(, (float) (x/4+sideB-100),  y/2-10, paint3);
        double f = a+d;
        canvas.drawText("Flagpole height :" + String.valueOf(f)+units, x/2-120, y-350, paint3);
        canvas.drawText("Angle EA :" + String.valueOf(angleEA)+(char)0x00B0, x/2-120, y-320, paint3);
        canvas.drawText("Angle BC :" + String.valueOf(angleBC)+(char)0x00B0, x/2-120, y-290, paint3);
        canvas.drawText("Length B :" + String.valueOf(sideB)+units, x/2-120, y-260, paint3);
        canvas.drawText("Length D :" + String.valueOf(sideD)+units, x/2-120, y-230, paint3);
        canvas.drawPath(triangle, paint2);




canvas.restore();

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mScaleDetector.onTouchEvent(ev);

        final int action = ev.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = ev.getX();
                final float y = ev.getY();

                mLastTouchX = x;
                mLastTouchY = y;
                mActivePointerId = ev.getPointerId(0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final int pointerIndex = ev.findPointerIndex(mActivePointerId);
                final float x = ev.getX(pointerIndex);
                final float y = ev.getY(pointerIndex);

                // Only move if the ScaleGestureDetector isn't processing a gesture.
                if (!mScaleDetector.isInProgress()) {
                    final float dx = x - mLastTouchX;
                    final float dy = y - mLastTouchY;

                    mPosX += dx;
                    mPosY += dy;

                    invalidate();
                }

                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {
                final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = ev.getX(newPointerIndex);
                    mLastTouchY = ev.getY(newPointerIndex);
                    mActivePointerId = ev.getPointerId(newPointerIndex);
                }
                break;
            }
        }

        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }

}
