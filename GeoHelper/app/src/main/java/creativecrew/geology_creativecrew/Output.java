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
 * Created by S525050 on 9/10/2016.
 */
public class Output extends View {

    private ThreepointOutput threepointOutput;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.0f;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;
    private static final int INVALID_POINTER_ID = -1;

    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;
    public double A ;
    public double B ;
    public double C ;
    public double AB ;
    public double AC ;
    public int angleAB;
    public int angleAC ;
    double strike;
    double dip;

    public Output(Context context, AttributeSet attrs) {
        super(context,attrs);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        float x = getWidth();
        float y = getHeight();
        int radius;
        radius = 100;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setColor(Color.BLUE);
        canvas.drawPaint(paint);


        double lx1 = 0;
        double ly1 = 0;
        double remainder = 0;
        double ratio = 0;
        if (A > B && A > C) {
            if (B > C) {
                lx1 = A - B;
                ly1 = B - C;
            } else {
                lx1 = A - C;
                ly1 = C - B;
            }
        }
        if (B > A && B > C) {
            if (A > C) {
                lx1 = B - A;
                ly1 = A - C;
            } else {
                lx1 = B - C;
                ly1 = C - A;
            }
        }
        if (C > A && C > B) {
            if (A > B) {
                lx1 = C - A;
                ly1 = A - B;
            } else {
                lx1 = C - B;
                ly1 = B - A;
            }
        }


        canvas.save();
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, mScaleFactor, this.mScaleDetector.getFocusX(), this.mScaleDetector.getFocusY());

//            int pointB = (int) (Math.sqrt(AB*AB - ((A*4/10)-(B*4/10)))+x/2);
//            int pointC = (int) (Math.sqrt(AC*AC - ((A*4/10)-(C*4/10)))+x/2);
        // Use Color.parseColor to define HTML colors
        //    paint.setColor(Color.parseColor("#CD5C5C"));
//            canvas.drawCircle(y-(A*2/10), y-(A*2/10), radius, paint);
        //Log.w("my app",String.format("%d",pointB));
      canvas.drawLine(x / 2, 0, x / 2, y, paint);


        double pointx1 = 0;
        double pointy1 = 0;
        double pointx2 = 0;
        double pointy2 = 0;
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(0);
        paint2.setTextSize(30);

        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setColor(Color.RED);
        paint3.setStrokeWidth(0);
        paint3.setTextSize(60);

        canvas.drawPaint(paint2);
        if (angleAB > 0 && angleAB <= 90) {
            int angle1 = 90 - angleAB;
            int angle2 = 90 - angle1;
            double ptx = AB * Math.sin((Math.PI / 180) * (angle2));
            pointx1 = (x / 2 + ptx);
            double pty = AB * Math.sin((Math.PI / 180) * (angle1));
            pointy1 = (y / 2 - pty);
        } else if (angleAB > 90 && angleAB <= 180) {
            int angle1 = 180 - angleAB;
            int angle2 = 90 - angle1;
            double ptx = AB * Math.sin((Math.PI / 180) * (angle1));
            pointx1 = (x / 2 + ptx);
            double pty = AB * Math.sin((Math.PI / 180) * (angle2));
            pointy1 = (y / 2 + pty);

        } else if (angleAB > 180 && angleAB <= 270) {
            int angle1 = 270 - angleAB;
            int angle2 = 90 - angle1;
            double ptx = AB * Math.sin((Math.PI / 180) * (angle2));
            pointx1 = (x / 2 - ptx);
            double pty = AB * Math.sin((Math.PI / 180) * (angle1));
            pointy1 = (y / 2 + pty);

        } else if (angleAB > 270 && angleAB <= 360) {
            int angle1 = 360 - angleAB;
            int angle2 = 90 - angle1;
            double ptx = AB * Math.sin((Math.PI / 180) * (angle1));
            pointx1 = (x / 2 - ptx);
            double pty = AB * Math.sin((Math.PI / 180) * (angle2));
            pointy1 = (y / 2 - pty);
        }
        if (angleAC > 0 && angleAC <= 90) {
            int angle1 = 90 - angleAC;
            int angle2 = 90 - angle1;
            double ptx = AC * Math.sin((Math.PI / 180) * (angle2));
            pointx2 = (x / 2 + ptx);
            double pty = AC * Math.sin((Math.PI / 180) * (angle1));
            pointy2 = (y / 2 - pty);
        } else if (angleAC > 90 && angleAC <= 180) {
            int angle1 = 180 - angleAC;
            int angle2 = 90 - angle1;
            double ptx = AC * Math.sin((Math.PI / 180) * (angle1));
            pointx2 = (x / 2 + ptx);
            double pty = AC * Math.sin((Math.PI / 180) * (angle2));
            pointy2 = (y / 2 + pty);

        } else if (angleAC > 180 && angleAC <= 270) {
            int angle1 = 270 - angleAC;
            int angle2 = 90 - angle1;
            double ptx = AC * Math.sin((Math.PI / 180) * (angle2));
            pointx2 = (x / 2 - ptx);
            double pty = AC * Math.sin((Math.PI / 180) * (angle1));
            pointy2 = (y / 2 + pty);

        } else if (angleAC > 270 && angleAC <= 360) {
            int angle1 = 360 - angleAC;
            int angle2 = 90 - angle1;
            double ptx = AC * Math.sin((Math.PI / 180) * (angle1));
            pointx2 = (x / 2 - ptx);
            double pty = AC * Math.sin((Math.PI / 180) * (angle2));
            pointy2 = (y / 2 - pty);
        }

        ratio = (double) lx1 / ly1;

        double mpx = pointx1 + ratio * (pointx2 - pointx1);
        double mpy = pointy1 + ratio * (pointy2 - pointy1);

        canvas.drawLine(0, y / 2, x, y / 2, paint2);
        Path triangle = new Path();
        triangle.moveTo(x / 2, y / 2);
        triangle.lineTo((float) pointx1, (float) pointy1);


        triangle.moveTo(x / 2, y / 2);
        triangle.lineTo((float) pointx2, (float) pointy2);
        triangle.moveTo((float) pointx1, (float) pointy1);
        triangle.lineTo((float) pointx2, (float) pointy2);
        canvas.drawPath(triangle, paint);
        canvas.drawText("A", x / 2, y / 2, paint2);
        canvas.drawText("B", (float) pointx1, (float) pointy1, paint2);
        canvas.drawText("C", (float) pointx2, (float) pointy2, paint2);

        canvas.drawLine(x / 2, y / 2, (float) mpx, (float) mpy, paint2);

        double distance = Math.sqrt((x / 2 - (float) mpx) * (x / 2 - (float) mpx) + (y / 2 - (float) mpy) * (y / 2 - (float) mpy));
        double slope = (double) ((y / 2 - mpy) / (x / 2 - mpx));
        double ptxx1 = 0;
        double ptyy1 = 0;
        double ptxx2 = 0;
        double ptyy2 = 0;
        double ptxx3 = 0;
        double ptyy3 = 0;
        double ptxx4 = 0;
        double ptyy4 = 0;
        double ptxx5 = 0;
        double ptyy5 = 0;
        double ptxx6 = 0;
        double ptyy6 = 0;

        if (mpx > x / 2 && mpy < y / 2) {
            ptxx1 = pointx1 + Math.abs(distance * Math.cos((Math.atan((slope)))));
            ptyy1 = pointy1 - Math.abs(distance * Math.sin(((Math.atan((slope))))));
            canvas.drawLine((float) pointx1, (float) pointy1, (float) ptxx1, (float) ptyy1, paint2);
            ptxx2 = pointx2 + Math.abs(distance * Math.cos(((Math.atan(((slope)))))));
            ptyy2 = pointy2 - Math.abs(distance * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) pointx2, (float) pointy2, (float) ptxx2, (float) ptyy2, paint2);
            ptxx3 = mpx + Math.abs(distance * Math.cos(((Math.atan(((slope)))))));
            ptyy3 = mpy - Math.abs(distance * Math.sin(((Math.atan(((float) slope))))));
            canvas.drawLine((float) mpx, (float) mpy, (float) ptxx3, (float) ptyy3, paint2);
            ptxx4 = ptxx1 + Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy4 = ptyy1 - Math.abs((B - C) * Math.sin(((Math.atan(((slope)))))));
            canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx4, (float) ptyy4, paint2);
            ptxx5 = ptxx2 + Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy5 = ptyy2 - Math.abs((B - C) * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) ptxx2, (float) ptyy2, (float) ptxx5, (float) ptyy5, paint2);
            // canvas.drawLine((float) (pointx1+ptxx1)/2, (float) (pointy1+ptyy1)/2, (float) (pointx2+ptxx2)/2, (float) (pointy2+ptyy2)/2, paint2);
            canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx2, (float) ptyy2, paint2);

            ptxx6 = ptxx3 + Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy6 = ptyy3 - Math.abs((B - C) * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) ptxx3, (float) ptyy3, (float) ptxx6, (float) ptyy6, paint2);
            if (C < B) {
                canvas.drawLine((float) ptxx2, (float) ptyy2, (float) ptxx4, (float) ptyy4, paint2);
            } else {
                canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx5, (float) ptyy5, paint2);
            }
        } else if (mpx > x / 2 && mpy > y / 2) {
            ptxx1 = pointx1 + Math.abs(distance * Math.cos((Math.atan((slope)))));
            ptyy1 = pointy1 + Math.abs(distance * Math.sin(((Math.atan((slope))))));
            canvas.drawLine((float) pointx1, (float) pointy1, (float) ptxx1, (float) ptyy1, paint2);
            ptxx2 = pointx2 + Math.abs(distance * Math.cos(((Math.atan(((slope)))))));
            ptyy2 = pointy2 + Math.abs(distance * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) pointx2, (float) pointy2, (float) ptxx2, (float) ptyy2, paint2);
            ptxx3 = mpx + Math.abs(distance * Math.cos(((Math.atan(((slope)))))));
            ptyy3 = mpy + Math.abs(distance * Math.sin(((Math.atan(((float) slope))))));
            canvas.drawLine((float) mpx, (float) mpy, (float) ptxx3, (float) ptyy3, paint2);
            ptxx4 = ptxx1 + Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy4 = ptyy1 + Math.abs((B - C) * Math.sin(((Math.atan(((slope)))))));
            canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx4, (float) ptyy4, paint2);
            ptxx5 = ptxx2 + Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy5 = ptyy2 + Math.abs((B - C) * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) ptxx2, (float) ptyy2, (float) ptxx5, (float) ptyy5, paint2);
            // canvas.drawLine((float) (pointx1+ptxx1)/2, (float) (pointy1+ptyy1)/2, (float) (pointx2+ptxx2)/2, (float) (pointy2+ptyy2)/2, paint2);
            canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx2, (float) ptyy2, paint2);

            ptxx6 = ptxx3 + Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy6 = ptyy3 + Math.abs((B - C) * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) ptxx3, (float) ptyy3, (float) ptxx6, (float) ptyy6, paint2);
            if (C < B) {
                canvas.drawLine((float) ptxx2, (float) ptyy2, (float) ptxx4, (float) ptyy4, paint2);
            } else {
                canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx5, (float) ptyy5, paint2);
            }
        } else if (mpx < x / 2 && mpy > y / 2) {
            ptxx1 = pointx1 - Math.abs(distance * Math.cos((Math.atan((slope)))));
            ptyy1 = pointy1 + Math.abs(distance * Math.sin(((Math.atan((slope))))));
            canvas.drawLine((float) pointx1, (float) pointy1, (float) ptxx1, (float) ptyy1, paint2);
            ptxx2 = pointx2 - Math.abs(distance * Math.cos(((Math.atan(((slope)))))));
            ptyy2 = pointy2 + Math.abs(distance * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) pointx2, (float) pointy2, (float) ptxx2, (float) ptyy2, paint2);
            ptxx3 = mpx - Math.abs(distance * Math.cos(((Math.atan(((slope)))))));
            ptyy3 = mpy + Math.abs(distance * Math.sin(((Math.atan(((float) slope))))));
            canvas.drawLine((float) mpx, (float) mpy, (float) ptxx3, (float) ptyy3, paint2);
            ptxx4 = ptxx1 - Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy4 = ptyy1 + Math.abs((B - C) * Math.sin(((Math.atan(((slope)))))));
            canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx4, (float) ptyy4, paint2);
            ptxx5 = ptxx2 - Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy5 = ptyy2 + Math.abs((B - C) * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) ptxx2, (float) ptyy2, (float) ptxx5, (float) ptyy5, paint2);
            //canvas.drawLine((float) (pointx1+ptxx1)/2, (float) (pointy1+ptyy1)/2, (float) (pointx2+ptxx2)/2, (float) (pointy2+ptyy2)/2, paint2);
            canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx2, (float) ptyy2, paint2);

            ptxx6 = ptxx3 - Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy6 = ptyy3 + Math.abs((B - C) * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) ptxx3, (float) ptyy3, (float) ptxx6, (float) ptyy6, paint2);
            if (C < B) {
                canvas.drawLine((float) ptxx2, (float) ptyy2, (float) ptxx4, (float) ptyy4, paint2);
            } else {
                canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx5, (float) ptyy5, paint2);
            }
        } else if (mpx < x / 2 && mpy < y / 2) {
            ptxx1 = pointx1 - Math.abs(distance * Math.cos((Math.atan((slope)))));
            ptyy1 = pointy1 - Math.abs(distance * Math.sin(((Math.atan((slope))))));
            canvas.drawLine((float) pointx1, (float) pointy1, (float) ptxx1, (float) ptyy1, paint2);
            ptxx2 = pointx2 - Math.abs(distance * Math.cos(((Math.atan(((slope)))))));
            ptyy2 = pointy2 - Math.abs(distance * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) pointx2, (float) pointy2, (float) ptxx2, (float) ptyy2, paint2);
            ptxx3 = mpx - Math.abs(distance * Math.cos(((Math.atan(((slope)))))));
            ptyy3 = mpy - Math.abs(distance * Math.sin(((Math.atan(((float) slope))))));
            canvas.drawLine((float) mpx, (float) mpy, (float) ptxx3, (float) ptyy3, paint2);
            ptxx4 = ptxx1 - Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy4 = ptyy1 - Math.abs((B - C) * Math.sin(((Math.atan(((slope)))))));
            canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx4, (float) ptyy4, paint2);
            ptxx5 = ptxx2 - Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy5 = ptyy2 - Math.abs((B - C) * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) ptxx2, (float) ptyy2, (float) ptxx5, (float) ptyy5, paint2);
            //canvas.drawLine((float) (pointx1+ptxx1)/2, (float) (pointy1+ptyy1)/2, (float) (pointx2+ptxx2)/2, (float) (pointy2+ptyy2)/2, paint2);
            canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx2, (float) ptyy2, paint2);

            ptxx6 = ptxx3 - Math.abs((B - C) * Math.cos(((Math.atan(((slope)))))));
            ptyy6 = ptyy3 - Math.abs((B - C) * Math.sin(((Math.atan((((slope))))))));
            canvas.drawLine((float) ptxx3, (float) ptyy3, (float) ptxx6, (float) ptyy6, paint2);
            if (C < B) {
                canvas.drawLine((float) ptxx2, (float) ptyy2, (float) ptxx4, (float) ptyy4, paint2);
            } else {
                canvas.drawLine((float) ptxx1, (float) ptyy1, (float) ptxx5, (float) ptyy5, paint2);
            }
        }
        double alpha=(90-angleAB)*Math.PI/180;
        double pbx=AB*(float)Math.cos(alpha);
        double pby=AB*(float)Math.sin(alpha);
        alpha=(90-angleAC)*Math.PI/180;
        double pcx=AC*(float)Math.cos(alpha);
        double pcy=AC*(float)Math.sin(alpha);
        double pa1z=A;
        double pa1x=(pcx-pbx)/(C-B)*(pa1z-B)+(pbx);
        double pa1y=(pcy-pby)/(C-B)*(pa1z-B)+(pby);
        strike=(float)Math.atan(pa1y/pa1x)*180/(float)Math.PI;
        strike=90-strike;
        System.out.println(strike);
//       threepointOutput.strikevalue.setText((int) strike);
//        threepointOutput.dipvalue.setText((int) dip);
        double a=(B-A)/(B-C)*Math.sqrt((pbx-pcx)*(pbx-pcx)+(pby-pcy)*(pby-pcy));
        double b=(B-C)*a/(B-A);
        dip=Math.abs((float)Math.atan((B-C)/b)*180/(float)Math.PI);
        Path text = new Path();
        text.moveTo(x/2,y-20);
        canvas.drawText("Strike :"+" "+Math.round(strike)+(char)0x00B0,(x/2)+30,y-300,paint2);
       canvas.drawText("dip :"+" "+Math.round(dip)+(char)0x00B0,(x/2)+30,y-270,paint2);
        canvas.drawText("A : "+A,(x/2)+30,y-240,paint2 );
        canvas.drawText("B : "+B,(x/2)+30,y-210,paint2 );
        canvas.drawText("C : "+C,(x/2)+30,y-180,paint2 );
        canvas.drawText("AB : "+AB,(x/2)+30,y-150,paint2 );
        canvas.drawText("AC : "+AC,(x/2)+30,y-120,paint2 );



        canvas.drawText("N  ",(x/2+2),0,paint3 );
        canvas.drawText("S  ",(x/2+2),y,paint3 );
        canvas.drawText("E  ",x,y/2-2,paint3 );
        canvas.drawText("W  ",0,y/2-2,paint3 );



        System.out.println(dip);
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


