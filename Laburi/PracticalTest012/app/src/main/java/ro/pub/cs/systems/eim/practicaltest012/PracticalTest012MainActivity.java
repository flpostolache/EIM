package ro.pub.cs.systems.eim.practicaltest012;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest012MainActivity extends AppCompatActivity {

    private Button add;
    private EditText numar;
    private TextView istoric;
    private Button compute;
    private int serviceStatus = 0;
    private int suma = -1;
    private int modified = 1;
    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MessageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra(Constants.MESAJ), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test012_main);


        add = findViewById(R.id.Add);
        numar = findViewById(R.id.NextTerm);
        istoric = findViewById(R.id.Allterms);
        compute = findViewById(R.id.Compute);

        add.setOnClickListener(it -> {
            String numar_string = numar.getText().toString();
            try {
                int nr = Integer.parseInt(numar_string);
                String prev_istoric = istoric.getText().toString();
                if (prev_istoric.equals("")) {
                    istoric.setText(numar_string);
                }else {
                    istoric.setText(prev_istoric + "+" + numar_string);
                }
                modified = 1;

            }catch (Exception e) {
                Toast.makeText(this, "THE STRING IS NOT A NUMBER", Toast.LENGTH_LONG).show();
            }
            numar.setText("");
        });
        compute.setOnClickListener(it -> {
            if (modified == 1){
                Intent intent = new Intent(getApplicationContext(), Colocviul_2SecondaryActivity.class);
                intent.putExtra(Constants.TERMS, istoric.getText().toString());
                startActivityForResult(intent, Constants.REQUEST_CODE);
                modified = 0;
            } else {
                Toast.makeText(this, "The result is" + suma, Toast.LENGTH_LONG).show();
            }
        });

        intentFilter.addAction(Constants.ACTIUNE);
    }

    @Override
    protected void onActivityResult(int requestCode, int result_code, Intent intent) {
        super.onActivityResult(requestCode, result_code, intent);
        if (requestCode == Constants.REQUEST_CODE) {
            suma = result_code;
            if (suma > Constants.THRESHOLD && serviceStatus == 0) {
                Log.d("DA", "AM Intrat sa pornim serviciul");
                Intent intent2 = new Intent(getApplicationContext(), Colocviul_2Service.class);
                intent2.putExtra(Constants.SUMA, suma);
                getApplicationContext().startService(intent2);
                serviceStatus = 1;
            }
            Toast.makeText(this, "The activity returned with result " + result_code, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.ISTORIC, istoric.getText().toString());
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.ISTORIC)) {
            istoric.setText(savedInstanceState.getString(Constants.ISTORIC));
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviul_2Service.class);
        stopService(intent);
        serviceStatus = 0;
        super.onDestroy();

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
}