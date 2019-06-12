package com.example.vote;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class yyy extends AppCompatActivity {
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private static String str[]={"壹","贰","叁"};
    private static int pics[]={R.mipmap.f_f_fengmian,R.mipmap.s_f_fengmian,R.mipmap.t_f_fengmian};
    private TextView text;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yyy);

        text=(TextView)findViewById(R.id.txtlabel);
        img=(ImageView)findViewById(R.id.imageView);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
    }

    protected void onResume(){
        super.onResume();
        if(sensorManager !=null){//注册监听器
            sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        //第一个参数是Listener，第二个是传感器类型，第三个是传感器频率
        }
    }

    protected void onStop(){
        super.onStop();
        if(sensorManager !=null){//取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
    //重力感应监听
    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //传感器改变时执行此方法
            float[] values = event.values;
            float x = values[0];//x轴重力加速度，向右为正
            float y = values[1];//前为正
            float z = values[2];//上为正
            int medumValue = 10;
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = 10;
                handler.sendMessage(msg);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    Handler handler=new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 10:
                    java.util.Random r=new java.util.Random();
                    int num=Math.abs(r.nextInt())%3;
                    text.setText(str[num]);
                    img.setImageResource(pics[num]);
                    break;
            }
        }
    };
}
