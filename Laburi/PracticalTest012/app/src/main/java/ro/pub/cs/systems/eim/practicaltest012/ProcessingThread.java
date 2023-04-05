package ro.pub.cs.systems.eim.practicaltest012;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread {

    private Context context;
    private int suma;
    private boolean isrunning = true;
    public ProcessingThread(Context context, int number) {
        this.context = context;
        this.suma = number;
    }

    @Override
    public void run() {
        Log.d("SERVICE", "I am logging");
            Log.d("SERVICE", "I am still logging");
            sleep();
            sendMessage();
            while (isrunning){

            }
    }

    public void sendMessage(){
        Intent intent = new Intent();
        intent.setAction(Constants.ACTIUNE);
        intent.putExtra(Constants.MESAJ, new Date(System.currentTimeMillis()) + ". Sum is: " + suma);
        context.sendBroadcast(intent);
    }
    public void sleep(){
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void stopThread(){
        isrunning = false;
    }
}
