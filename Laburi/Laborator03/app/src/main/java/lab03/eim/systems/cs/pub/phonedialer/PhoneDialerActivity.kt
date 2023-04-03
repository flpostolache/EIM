package lab03.eim.systems.cs.pub.phonedialer

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.location.GnssAntennaInfo.Listener
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class PhoneDialerActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CALL_PHONE = 1
    private var textView: TextView? = null
    inner class Button_listener : View.OnClickListener {
        override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.imageButton4 -> {
                    textView?.text = textView?.text.toString().dropLast(1)
                }
                R.id.imageButton2 -> {
                    finish()
                }
                R.id.imageButton3 -> {
                    if (ContextCompat.checkSelfPermission(p0.context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                            p0.context as Activity,
                            arrayOf(android.Manifest.permission.CALL_PHONE), PERMISSION_REQUEST_CALL_PHONE)
                    } else {
                        val intent: Intent = Intent(Intent.ACTION_CALL)
                        intent.data = Uri.parse("tel:" + textView?.text.toString())
                        startActivity(intent)
                    }
                }
                else -> {
                    val buton: Button = findViewById(p0?.id!!)
                    textView?.text = textView?.text.toString().plus(buton.text)
                }
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_dialer)
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        textView = findViewById(R.id.textView)

        val listener: Button_listener = Button_listener()
        val buton1: Button = findViewById(R.id.button13)
        buton1.setOnClickListener(listener)
        val buton2: Button = findViewById(R.id.button14)
        buton2.setOnClickListener(listener)
        val buton3: Button = findViewById(R.id.button15)
        buton3.setOnClickListener(listener)
        val buton4: Button = findViewById(R.id.button16)
        buton4.setOnClickListener(listener)
        val buton5: Button = findViewById(R.id.button17)
        buton5.setOnClickListener(listener)
        val buton6: Button = findViewById(R.id.button18)
        buton6.setOnClickListener(listener)
        val buton7: Button = findViewById(R.id.button19)
        buton7.setOnClickListener(listener)
        val buton8: Button = findViewById(R.id.button20)
        buton8.setOnClickListener(listener)
        val buton9: Button = findViewById(R.id.button21)
        buton9.setOnClickListener(listener)
        val buton10: Button = findViewById(R.id.button22)
        buton10.setOnClickListener(listener)
        val buton11: Button = findViewById(R.id.button23)
        buton11.setOnClickListener(listener)
        val buton12: Button= findViewById(R.id.button24)
        buton12.setOnClickListener(listener)
        val buton13: ImageButton = findViewById(R.id.imageButton4)
        buton13.setOnClickListener(listener)
        val buton14: ImageButton = findViewById(R.id.imageButton2)
        buton14.setOnClickListener(listener)
        val buton15: ImageButton = findViewById(R.id.imageButton3)
        buton15.setOnClickListener(listener)
    }
}