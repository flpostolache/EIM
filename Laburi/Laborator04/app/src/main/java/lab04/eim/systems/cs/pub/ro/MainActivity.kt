package lab04.eim.systems.cs.pub.ro

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var buttonnew: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buton1: Button = findViewById(R.id.button)
        buton1.setOnClickListener {
            val view:LinearLayout = findViewById(R.id.invis_vis)
            if (buton1.text == "Show Additional Fields") {
                buton1.text = "Hide Additional Fields"
                view.visibility = View.VISIBLE
            }
            else {
                buton1.text = "Show Additional Fields"
                view.visibility = View.GONE
            }

        }
        val button2 : Button = findViewById(R.id.button1)
        button2.setOnClickListener {

        }
        val button3 : Button = findViewById(R.id.button2)
        button3.setOnClickListener{
            finish()
        }
    }
}
