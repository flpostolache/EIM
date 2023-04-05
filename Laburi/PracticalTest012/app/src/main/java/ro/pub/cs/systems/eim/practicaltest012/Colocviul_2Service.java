package ro.pub.cs.systems.eim.practicaltest012;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Colocviul_2Service extends Service {
    private ProcessingThread processingThread;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int suma = intent.getIntExtra(Constants.SUMA, -1);
        processingThread = new ProcessingThread(this, suma);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

}
