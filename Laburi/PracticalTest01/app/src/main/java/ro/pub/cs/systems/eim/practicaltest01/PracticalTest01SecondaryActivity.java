package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private class Button_listen implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button3:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.button4:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    };

    private Button_listen listen = new Button_listen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        TextView view = findViewById(R.id.textView2);
        Button ok_button = findViewById(R.id.button3);
        Button cancel_button = findViewById(R.id.button4);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER_OF_USAGES)) {
            int numberOfClicks = intent.getIntExtra(Constants.NUMBER_OF_USAGES, -1);
            view.setText(String.valueOf(numberOfClicks));
        }
        ok_button.setOnClickListener(listen);
        cancel_button.setOnClickListener(listen);
    }
}