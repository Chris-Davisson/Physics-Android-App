package com.example.lab7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class BounceMass extends View{

    public float vel = 0;
    public float acc = 0;
    public int finalWidth = 30000;
    public int finalLength = 40000;
    public int mCoilNum;
    public String mShape = "Picture";
    public float mStiffness;
    public int mDisplace;
    public float pos = 0 - (mDisplace * 1000);


    public BounceMass(Context context) {
        super(context);
    }

    public BounceMass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BounceMass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //White Backgroud
        canvas.drawRGB(255, 255, 255);
        int blackInt = Color.argb(255,0,0,0);

        //Black Paint
        Paint black = new Paint();
        black.setColor(blackInt);
        black.setStyle(Paint.Style.STROKE);


        //Scaling
        int width = canvas.getWidth() ;
        int height = canvas.getHeight();
        canvas.scale((float) width / finalWidth , (float) height / finalLength);

        //Translate
        canvas.translate(15000 , 20000);

        //Box
        Rect weight = new Rect(-2000,(int)(-2000 + (pos* 1000)),2000,(int)(2000 + (pos * 1000)));


        switch (mShape){
            case ("Rectangle"):
                canvas.drawRect(weight , black);
                break;
            case("RoundedRectangle"):
                RectF a1 = new RectF(-2000,(int)(-2000 + (pos* 1000)),2000,(int)(2000 + (pos * 1000)));
                canvas.drawRect( a1 , black);
                break;
            case("Circle"):
                canvas.drawCircle(0,(int)(-800 + (pos* 1000)),2000, black);
                break;
            case("Picture"):
                Drawable smiley = getResources().getDrawable(R.drawable.smiley);
                smiley.setBounds(weight);
                smiley.draw(canvas);
                break;
            default:
                canvas.drawRect(weight , black);
        }



        int cen =  (int)((18000 + (pos * 1000)) / mCoilNum + 1);
        canvas.translate(0 , -2000);


        for(int x = 0; x < mCoilNum ; x++){
            RectF oval = new RectF(-1000, -cen + (pos * 1000), 1000 , cen + (pos * 1000));
            canvas.translate(0,-cen);
            canvas.drawOval(oval , black);

        }
    }



    public void physics(float delT){
        delT = delT / 1000f;
        acc = (float) ((9.80665f) -(mStiffness * pos));
        vel = (acc * delT) + vel;
        pos = pos + (vel * delT);
    }



}
