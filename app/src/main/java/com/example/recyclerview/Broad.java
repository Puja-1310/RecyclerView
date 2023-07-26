package com.example.recyclerview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Broad extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction()))
        {
            Toast.makeText(context, "Battery is Low!!", Toast.LENGTH_LONG).show();
        }

    }
}
