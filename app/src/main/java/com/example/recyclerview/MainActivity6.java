package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {
    private BatteryReceiver batteryReceiver;
    private TextView batteryLevelTextView;
    private ImageView chargingImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        batteryLevelTextView = findViewById(R.id.batteryLevelTextView);
        chargingImageView = findViewById(R.id.chargingImageView);

        batteryReceiver = new BatteryReceiver();

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver,intentFilter);

    }

    protected void onDestroy(){
        super.onDestroy();

        unregisterReceiver(batteryReceiver);
    }
    public class BatteryReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPercentage = (level / (float) scale) * 100;

//            batteryLevelTextView.setText("Battery Level: " + batteryPercentage + "%");

//            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
//            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
//                    status == BatteryManager.BATTERY_STATUS_FULL;

            if (batteryPercentage < 80){
                Toast.makeText(context, "Battery level is under 20% ", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context, "Battery: " + batteryPercentage + "%", Toast.LENGTH_LONG).show();
            }
        }
    }
}