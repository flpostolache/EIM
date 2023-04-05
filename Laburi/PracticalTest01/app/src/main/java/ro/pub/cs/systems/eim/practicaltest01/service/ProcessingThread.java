package ro.pub.cs.systems.eim.practicaltest01.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

import ro.pub.cs.systems.eim.practicaltest01.Constants;

public class ProcessingThread extends Thread{

    private  Context context;
    private boolean isrunning = true;
    private Random random = new Random();
    private double arithmeticMean;
    private double geometricMean;


    public ProcessingThread(Context context, int firstNumber, int secondNumber) {
        this.context = context;
        arithmeticMean = (firstNumber + secondNumber) / 2;
        geometricMean = Math.sqrt(firstNumber * secondNumber);
    }

    @Override
    public void run() {
        Log.d("SERVICE", "I am logging");
        while (isrunning){
            Log.d("SERVICE", "I am still logging");
            sendMessage();
            sleep();
        }

    }

    public void sendMessage(){
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + arithmeticMean + " " + geometricMean);
        context.sendBroadcast(intent);
    }
    public void sleep(){
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void stopThread(){
        isrunning = false;
    }
}
