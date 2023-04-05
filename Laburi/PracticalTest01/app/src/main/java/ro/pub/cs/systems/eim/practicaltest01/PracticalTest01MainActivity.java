package ro.pub.cs.systems.eim.practicaltest01;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practicaltest01.service.PracticalTest01Service;

public class PracticalTest01MainActivity extends AppCompatActivity {


    private TextView press_me_text;
    private TextView press_me_too_text;
    private Button press_me;
    private Button press_me_too;
    int serviceStatus = 0;
    private IntentFilter intentFilter= new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MessageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    private class Button_listen implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int first_but = Integer.parseInt(press_me_text.getText().toString());
            int second_but = Integer.parseInt(press_me_too_text.getText().toString());
            switch (view.getId()){

                case R.id.button2:
                    second_but += 1;
                    press_me_too_text.setText(String.valueOf(second_but));
                    break;
                case R.id.button1:
                    first_but++;
                    press_me_text.setText(String.valueOf(first_but));
                    break;
                case R.id.button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    int click_number = Integer.parseInt(press_me_text.getText().toString()) + Integer.parseInt(press_me_too_text.getText().toString());
                    intent.putExtra(Constants.NUMBER_OF_USAGES, click_number);
                    startActivityForResult(intent, Constants.REQUEST_CODE);
                    break;
            }
            if (second_but + first_but > 10 && serviceStatus == 0) {
                Log.d("DA", "AM Intrat sa pornim serviciul");
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
                intent.putExtra("press_me", first_but);
                intent.putExtra("press_me_too", second_but);
                getApplicationContext().startService(intent);
                serviceStatus = 1;
            }
        }
    }
    private final Button_listen listen = new Button_listen();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        press_me_text = findViewById(R.id.textView);
        press_me_too_text = findViewById(R.id.textView1);
        press_me = findViewById(R.id.button1);
        press_me.setOnClickListener(listen);
        press_me_too = findViewById(R.id.button2);
        press_me_too.setOnClickListener(listen);

        Button next_activity = findViewById(R.id.button);
        next_activity.setOnClickListener(listen);
        for (int i = 0; i < Constants.actionTypes.length; i++){
            intentFilter.addAction(Constants.actionTypes[i]);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.PRESS_ME_TEXT, press_me_text.getText().toString());
        savedInstanceState.putString(Constants.PRESS_ME_TOO_TEXT, press_me_too_text.getText().toString());
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.PRESS_ME_TEXT)) {
            press_me_text.setText(savedInstanceState.getString(Constants.PRESS_ME_TEXT));
        }
        if (savedInstanceState.containsKey(Constants.PRESS_ME_TOO_TEXT)) {
            press_me_too_text.setText(savedInstanceState.getString(Constants.PRESS_ME_TOO_TEXT));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int result_code, Intent intent) {
        super.onActivityResult(requestCode, result_code, intent);
        if (requestCode == Constants.REQUEST_CODE) {
            press_me_text.setText("0");
            press_me_too_text.setText("0");
            Toast.makeText(this, "The activity returned with result " + result_code, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Service.class);
        stopService(intent);
        serviceStatus = 0;
        super.onDestroy();

    }
}