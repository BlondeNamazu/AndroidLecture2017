package namazu.androidlecture2017;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.widget.TextView;

import java.io.Console;

/**
 * Created by 淳貴 on 2017/05/17.
 */
public class Ball {
    float x,y;
    int r;
    double vx,vy;
    Paint paint;
    float maxX,maxY;
    float minX,minY;
    final float FIELDSIZE = 200;
    final float MARGIN = 50;
    final float E = -0.6f;

    public Ball(float x,float y,int r){
        this.x = x;
        this.y = y;
        this.r = r;
        /*
        maxX = x + FIELDSIZE;
        maxY = y + FIELDSIZE;
        minX = x - FIELDSIZE;
        minY = y - FIELDSIZE;
        */
        maxX = 2*x - MARGIN;
        maxY = 2*y - MARGIN;
        minX = MARGIN;
        minY = MARGIN;
        vx = 0.0;
        vy = 0.0;
        paint = new Paint();

    }
    public void onDraw(Canvas canvas,String[] str){
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new Rect((int) minX - r, (int) minY - r, (int) maxX + r, (int) maxY + r), paint);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(x, y, r, paint);
        paint.setColor(Color.BLACK);
        if(str[0]==null) return;
        paint.setTextSize(30);
        for(int i=0;i<4;i++)canvas.drawText(str[i],minX,(i+2)*minY,paint);
        //Log.d("WhereIsBall","x="+x+",y="+y);
    }
    public void move(){
        x += vx;
        y += vy;
        if(x<minX && vx <0) vx *= E;
        if(x>maxX && vx >0) vx *= E;
        if(y<minY && vy < 0) vy *= E;
        if(y>maxY && vy > 0) vy *= E;
    }
    public void addAccel(float ax,float ay){
        vx -= ax;
        vy += ay;
    }
}
