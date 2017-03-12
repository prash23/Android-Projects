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

public class TestSimpleTrig extends View {

    double B,angleB;
    double answer;
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

    public TestSimpleTrig(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScaleDetector = new ScaleGestureDetector(context, new TestSimpleTrig.ScaleListener());
    }
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        float x = getWidth();
        float y = getHeight();
        canvas.save();
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, mScaleFactor, this.mScaleDetector.getFocusX(), this.mScaleDetector.getFocusY());
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
        paint2.setStrokeWidth(2);
        paint2.setTextSize(15);
        canvas.drawPaint(paint2);

        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setColor(Color.RED);
        paint3.setStrokeWidth(0);
        paint3.setTextSize(30);
        canvas.drawPaint(paint3);


//        B=100;
//        angleB=60;
        Path triangle = new Path();
        triangle.moveTo(x / 2, y / 2);
        triangle.lineTo((float) (x/2+(B*10)),y/2);
        double radian = Math.toRadians(angleB);

        double perpendicular = (Double) (B*10*Math.tan(radian));
        triangle.moveTo(x / 2, y / 2);
        triangle.lineTo(x/2,y/2-(float)perpendicular );
        triangle.moveTo(x/2,y/2-(float)perpendicular);
        triangle.lineTo((float) (x/2+(B*10)),y/2 );
        canvas.drawPath(triangle, paint2);
        canvas.drawText("A", x / 2, y / 2, paint3);
        canvas.drawText(String.valueOf(B)+units, (float) (((x/2)+(B*10)/4)),y/2+25,paint3);
        canvas.drawText("B", (float) (x/2+(B*10)),y/2, paint3);
        canvas.drawText("C", x/2,y/2-(float)perpendicular , paint3);
        answer = Math.round(perpendicular);
        canvas.drawText(String.valueOf(answer/10)+units,  x/2,y/2-(float)perpendicular/2 , paint3);
        canvas.drawText(String.valueOf(angleB)+(char)0x00B0, (float) (x/2+(B*10)-120),y/2-4 , paint3);
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