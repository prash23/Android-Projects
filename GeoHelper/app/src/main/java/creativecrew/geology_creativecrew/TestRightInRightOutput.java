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
public class TestRightInRightOutput extends View {

    double sideA, sideB, sideC, sideX, answer, sideCD, sideD, sideY ;
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

    public TestRightInRightOutput(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScaleDetector = new ScaleGestureDetector(context, new TestRightInRightOutput.ScaleListener());
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

    protected void onDraw(Canvas canvas){

//        sideA = 1050;
//        sideB = 790;
//        sideC = 2000;
//        sideA/=10;
//        sideB/= 10;
//        sideC/= 10;
//        if(sideA>500||sideB>500||sideC>500){
//            sideA/=2;
//        sideB/= 2;
//        sideC/= 2;
//        }

        sideX = (sideB*sideC)/(sideA-sideB);
        answer = Math.atan(sideB/sideX);
        System.out.println(Math.toDegrees(answer));
        sideCD = sideA/(Math.tan(answer));
        sideD = sideCD - sideB;
        sideY = sideB/Math.tan(Math.round(answer));
        System.out.println(sideCD);


        //               unknownSide = (knownSide2*knownSide3)/(knownSide1-knownSide2);
//               angle1 = Math.atan(knownSide2/unknownSide);


        super.onDraw(canvas);
        float x = getWidth();
        float y = getHeight();
        canvas.save();
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, mScaleFactor, this.mScaleDetector.getFocusX(), this.mScaleDetector.getFocusY());
//        int radius;
//        radius = 100;
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(2);
//        paint.setColor(Color.BLACK);
//        canvas.drawPaint(paint);


//        canvas.drawLine(x / 2, 0, x / 2, y, paint);
//        canvas.drawLine(0,y/2,x,y/2,paint);
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(10);
        paint2.setTextSize(15);
        canvas.drawPaint(paint2);

        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setColor(Color.RED);
        paint3.setStrokeWidth(0);
        paint3.setTextSize(30);
        canvas.drawPaint(paint3);

        Paint paint4 = new Paint();
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setColor(Color.RED);
        paint4.setStrokeWidth(0);
        paint4.setTextSize(50);
        canvas.drawPaint(paint4);

        Path triangle = new Path();
        triangle.moveTo(x / 4, y / 2);
        triangle.lineTo((float) (x/4+sideCD),y/2);
        triangle.moveTo(x / 4, y / 2);
        triangle.lineTo(x / 4, (float) (y/2-sideA));
        triangle.moveTo(x / 4, (float) (y/2-sideA));
        triangle.lineTo((float) (x/4+sideCD),y/2);
        triangle.moveTo((float) (x/4+sideC),y/2);
        triangle.lineTo((float) (x/4+(sideC)), (float) (y/2-sideB));
        canvas.drawText("D", (float) (x/4+sideCD-30),y/2-8, paint4);
        canvas.drawText("C", (float) (x/4+sideC/2), y/2-8, paint4);
        canvas.drawText("A", x/4+4, (float) (y/2-sideA/2), paint4);
        canvas.drawText("B", (float) (x/4+(sideC+4)), (float) (y/2-sideB/2), paint4);


        canvas.drawText("Angle D : " + String.valueOf(Math.round(Math.toDegrees(answer))) + (char)0x00B0, x / 2 - 120, y - 150, paint3);
        canvas.drawText("Side A : " + String.valueOf(sideA) + units, x / 2 - 120, y - 120, paint3);
        canvas.drawText("Side B : " + String.valueOf(sideB) + units, x / 2 - 120, y - 90, paint3);
        canvas.drawText("Side C : " + String.valueOf(sideC) + units, x / 2 - 120, y - 60, paint3);
        canvas.drawPath(triangle, paint2);

        canvas.restore();


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
