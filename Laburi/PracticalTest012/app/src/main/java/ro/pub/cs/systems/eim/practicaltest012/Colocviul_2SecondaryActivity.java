package ro.pub.cs.systems.eim.practicaltest012;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

public class Colocviul_2SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.TERMS)) {
            String ecuation = intent.getStringExtra(Constants.TERMS);
            if (Objects.equals(ecuation, "")) {
                setResult(-1, null);
                finish();
            }
            else {
                String[] numbers = ecuation.split("\\+");
                int result = 0;
                for (int i = 0; i < numbers.length; i++){
                    result += Integer.parseInt(numbers[i]);
                }
                Log.d("DA", "The result is " + result);
                setResult(result, null);
                finish();
            }
        }
    }
}