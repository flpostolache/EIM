package lab03.eim.systems.cs.pub.phonedialer

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PhoneDialerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_dialer)
        val buton1: Button = findViewById(R.id.button13) as Button
        buton1.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton1.text)
        }
        val buton2: Button = findViewById(R.id.button14) as Button
        buton2.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton2.text)
        }
        val buton3: Button = findViewById(R.id.button15) as Button
        buton3.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton3.text)
        }
        val buton4: Button = findViewById(R.id.button16) as Button
        buton4.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton4.text)
        }
        val buton5: Button = findViewById(R.id.button17) as Button
        buton5.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton5.text)
        }
        val buton6: Button = findViewById(R.id.button18) as Button
        buton6.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton6.text)
        }
        val buton7: Button = findViewById(R.id.button19) as Button
        buton7.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton7.text)
        }
        val buton8: Button = findViewById(R.id.button20) as Button
        buton8.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton8.text)
        }
        val buton9: Button = findViewById(R.id.button21) as Button
        buton9.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton9.text)
        }
        val buton10: Button = findViewById(R.id.button22) as Button
        buton10.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton10.text)
        }
        val buton11: Button = findViewById(R.id.button23) as Button
        buton11.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton11.text)
        }
        val buton12: Button= findViewById(R.id.button24) as Button
        buton12.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().plus(buton12.text)
        }

        val buton13: ImageButton = findViewById(R.id.imageButton4) as ImageButton
        buton13.setOnClickListener {
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = textView.text.toString().dropLast(1)
        }
        val buton14: ImageButton = findViewById(R.id.imageButton2) as ImageButton
        buton13.setOnClickListener {
            finish()
        }
        val buton15: ImageButton = findViewById(R.id.imageButton3) as ImageButton
        buton13.setOnClickListener {
            /*val textView: TextView = findViewById(R.id.textView) as TextView
            val intent: Intent = Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + textView.getText().toString()));
            startActivity(intent);*/
        }
    }
}