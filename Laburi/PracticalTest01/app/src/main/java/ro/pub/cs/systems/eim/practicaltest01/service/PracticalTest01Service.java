package ro.pub.cs.systems.eim.practicaltest01.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import ro.pub.cs.systems.eim.practicaltest01.Constants;

public class PracticalTest01Service extends Service {


    private ProcessingThread processingThread;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int firstNumber = intent.getIntExtra(Constants.PRESS_ME_TEXT, -1);
        int secondNumber = intent.getIntExtra(Constants.PRESS_ME_TOO_TEXT, -1);
        processingThread = new ProcessingThread(this, firstNumber, secondNumber);
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
