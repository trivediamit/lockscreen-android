package com.simpleapps.mysimplelockscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LockScreenActivity extends AppCompatActivity {

    private int count = 0;

    private final String TAG = "LockScreenActivity";

    private TextView tvTime, tvDate;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH);

    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onStart() {
        super.onStart();

        count = 0;

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0)
                    tvTime.setText(simpleDateFormat.format(new Date()));
            }
        };

        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    public void onStop() {
        super.onStop();
        if (broadcastReceiver != null)
            unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        tvTime = (TextView) findViewById(R.id.lock_screen_tv_time);
        tvTime.setText(simpleDateFormat.format(new Date()));

        tvDate = (TextView) findViewById(R.id.lock_screen_tv_date);
        tvDate.setText(simpleDateFormat2.format(new Date()));
    }

    public void unlockPhone(View view) {
        this.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                count = 0;
                Log.d(TAG, "Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE):
                Log.d(TAG, "Action was MOVE");
                if (count == 8) {
                    this.finish();
                }
                Log.e(TAG, String.valueOf(count));
                count++;
                return true;
            case (MotionEvent.ACTION_UP):
                Log.d(TAG, "Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(TAG, "Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(TAG, "Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
