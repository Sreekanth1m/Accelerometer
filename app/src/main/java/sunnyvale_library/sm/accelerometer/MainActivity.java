package sunnyvale_library.sm.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener {
    String[] fortunes = new String[3];
    String[] fortunes1 = {"a","b","c"};
    private SensorManager manager;
    private Sensor accelerometer;
    long previousTime=0;
    float prevX, prevY, prevZ;
    TextView textView,textView2;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fortunes[0] = "You will receive a surprise";
        fortunes[1] = "You will have a great dinner";
        fortunes[2] = "You will meet systerious stranger";
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        prevX=prevY=prevZ=0;
        textView = (TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
      //  InputStream is = getResources().openRawResource(R.raw.Ball_8_icon);
       // BitmapFactory.decodeStream(is);
        //imageView = (ImageView) getResources().openRawResource(R.raw.Ball_8_icon);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if(mySensor.getType()==Sensor.TYPE_ACCELEROMETER){

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long currentTime = System.currentTimeMillis();

            if((currentTime - previousTime) > 100) {

                long difference = currentTime - previousTime;
                previousTime=currentTime;
                float distance = (x+y+z-prevX-prevY-prevZ);
                long speed = (long) distance/difference;
                if (speed > 5) {
                    textView2.setText(fortunes[(int) (Math.random() * 3)]);
                }
            }
            prevX=x;
            prevY=y;
            prevZ=z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
