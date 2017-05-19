package namazu.androidlecture2017;

import java.util.List;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager manager;
    private TextView values;
    private Ball ball;
    private BallView ballView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ballView  = new BallView(this);
        //setContentView(R.layout.activity_main);
        setContentView(ballView);

        //values = (TextView)findViewById(R.id.value_id);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        // Listenerの登録解除
        manager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // Listenerの登録
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_GRAVITY);
        if(sensors.size() > 0) {
            Sensor s = sensors.get(0);
            manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if(event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            ballView.getSensor(event);
        }

    }
}
