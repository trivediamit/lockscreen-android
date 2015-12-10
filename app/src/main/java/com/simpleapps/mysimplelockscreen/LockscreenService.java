package com.simpleapps.mysimplelockscreen;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class LockscreenService extends Service {
    public LockscreenService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        registerReceiver(new MyReceiver(), new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(new MyReceiver(), new IntentFilter(Intent.ACTION_SCREEN_OFF));

        return 1;
    }
}
