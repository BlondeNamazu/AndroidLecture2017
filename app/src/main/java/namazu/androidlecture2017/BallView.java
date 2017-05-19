package namazu.androidlecture2017;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 淳貴 on 2017/05/17.
 */
public class BallView extends View {
    Ball ball;
    private final int BALLRANGE = 10;
    private String str[] = new String[4];
    public BallView(Context context){
        super(context);

        //ball = new Ball((int)this.getWidth(),(int)this.getHeight(),BALLRANGE);
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(ball == null) ball = new Ball(canvas.getWidth()/2,canvas.getHeight()/2,BALLRANGE);
        ball.move();
        ball.onDraw(canvas,str);
        invalidate();
    }
    public void getSensor(SensorEvent sensorEvent){

        str[0] = "重力センサー値:";
        str[1] = "\nX軸:" + sensorEvent.values[SensorManager.DATA_X];
        str[2] = "\nY軸:" + sensorEvent.values[SensorManager.DATA_Y];
        str[3] = "\nZ軸:" + sensorEvent.values[SensorManager.DATA_Z];
        //if(value!=null)value.setText(str);
        if(ball == null) return;
        float ax = sensorEvent.values[SensorManager.DATA_X];
        float ay = sensorEvent.values[SensorManager.DATA_Y];
        ax /= 6.0;
        ay /= 6.0;
        ball.addAccel(ax,ay);
    }
}
