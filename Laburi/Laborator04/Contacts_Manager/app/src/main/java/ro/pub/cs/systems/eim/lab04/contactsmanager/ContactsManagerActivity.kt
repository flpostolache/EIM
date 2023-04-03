package ro.pub.cs.systems.eim.lab04.contactsmanager

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ContactsManagerActivity : AppCompatActivity() {

    lateinit var nameEditText: EditText
    lateinit var phoneEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var addressEditText: EditText
    lateinit var jobTitleEditText: EditText
    lateinit var companyEditText: EditText
    lateinit var websiteEditText: EditText
    lateinit var imEditText: EditText

    lateinit var showHideAdditionalFieldsButton: Button
    lateinit var saveButton: Button
    lateinit var cancelButton: Button
    lateinit var additionalFieldsContainer: LinearLayout
    private var listener : Button_listener = Button_listener()
    private val CONTACTS_MANAGER_REQUEST_CODE = 2017

    private inner class  Button_listener : View.OnClickListener {
        override fun onClick(p0: View?) {
            when(p0?.id){
                R.id.button -> {
                    if (showHideAdditionalFieldsButton.text == "Show Additional Fields") {
                        showHideAdditionalFieldsButton.text = "Hide Additional Fields"
                        additionalFieldsContainer.visibility = View.VISIBLE
                    }
                    else {
                        showHideAdditionalFieldsButton.text = "Show Additional Fields"
                        additionalFieldsContainer.visibility = View.GONE
                    }
                }
                R.id.button1 -> {
                    val name: String = nameEditText.text.toString()
                    val phone = phoneEditText.text.toString()
                    val email = emailEditText.text.toString()
                    val address = addressEditText.text.toString()
                    val jobTitle = jobTitleEditText.text.toString()
                    val company = companyEditText.text.toString()
                    val website = websiteEditText.text.toString()
                    val im = imEditText.text.toString()
                    val intent = Intent(ContactsContract.Intents.Insert.ACTION)
                    intent.type = ContactsContract.RawContacts.CONTENT_TYPE;

                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone)
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email)
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address)
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle)
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company)
                    val contactData = ArrayList<ContentValues>()
                    val websiteRow = ContentValues()
                    websiteRow.put(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE
                    )
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website)
                    contactData.add(websiteRow)
                    val imRow = ContentValues()
                    imRow.put(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE
                    )
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im)
                    contactData.add(imRow)
                    intent.putParcelableArrayListExtra(
                        ContactsContract.Intents.Insert.DATA, contactData
                    )
                    //startActivity(intent)
                    intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                    startActivityForResult(intent, CONTACTS_MANAGER_REQUEST_CODE);
                }
                R.id.button2 -> {
                    setResult(RESULT_CANCELED, Intent())
                    finish()
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        phoneEditText = findViewById(R.id.editTextTextPersonName2)
        val intent = getIntent()
        if ( intent != null) {
            val phone = intent.getStringExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY")
            if ( phone != null) {
                phoneEditText.setText(phone)
            } else {
                Toast.makeText(this, "Phone number received from intent is null!", Toast.LENGTH_LONG).show();
            }
        }
        nameEditText = findViewById(R.id.editTextTextPersonName)
        emailEditText = findViewById(R.id.editTextTextPersonName3)
        addressEditText = findViewById(R.id.editTextTextPersonName4)
        jobTitleEditText = findViewById(R.id.editTextTextPersonName5)
        companyEditText = findViewById(R.id.editTextTextPersonName6)
        websiteEditText = findViewById(R.id.editTextTextPersonName7)
        imEditText = findViewById(R.id.editTextTextPersonName8)

        showHideAdditionalFieldsButton = findViewById(R.id.button)
        saveButton = findViewById(R.id.button1)
        cancelButton = findViewById(R.id.button2)
        additionalFieldsContainer = findViewById(R.id.invis_vis)

        showHideAdditionalFieldsButton.setOnClickListener(listener)
        cancelButton.setOnClickListener(listener)
        saveButton.setOnClickListener(listener)

    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        when (requestCode) {
            CONTACTS_MANAGER_REQUEST_CODE -> {
                setResult(resultCode, Intent())
                finish()
            }
        }
    }
}