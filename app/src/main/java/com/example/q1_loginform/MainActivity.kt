package com.example.q1_loginform

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {


    private val sharedPrefFile = "userInformation"
    //These variables use for sharedpreferences keys.
    val user_name = "username"
    val full_name = "fullname"
    val _email = "email"
    val _password = "password"
    val _gender = "gender"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fullname = findViewById<EditText>(R.id.eT1)
        val username = findViewById<EditText>(R.id.eT2)
        val email = findViewById<EditText>(R.id.eT3)
        val password = findViewById<EditText>(R.id.eT4)
        val retypePassword = findViewById<EditText>(R.id.eT5)

        val female = findViewById<RadioButton>(R.id.radioButton)
        val male = findViewById<RadioButton>(R.id.radioButton2)


        val register = findViewById<Button>(R.id.button)
        val showinfo = findViewById<Button>(R.id.button2)
        val hideinfo = findViewById<Button>(R.id.button3)

        val textFulName = findViewById<TextView>(R.id.textView2)
        val textUserName = findViewById<TextView>(R.id.textView3)
        val textEmail = findViewById<TextView>(R.id.textView4)
        val textPassword = findViewById<TextView>(R.id.textView5)
        val textGender = findViewById<TextView>(R.id.textView6)

        val sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        showinfo.setOnClickListener {
            textFulName.run { visibility = View.VISIBLE ; text = sharedPreferences.getString(full_name,"username") }
            textUserName.run { visibility = View.VISIBLE ; text = sharedPreferences.getString(user_name,"username") }
            textEmail.run { visibility = View.VISIBLE ; text = sharedPreferences.getString(_email,"email") }
            textPassword.run { visibility = View.VISIBLE ; text = sharedPreferences.getString(_password,"password") }
            textGender.run { visibility = View.VISIBLE ; text = sharedPreferences.getString(_gender,"Gender") }
            hideinfo.visibility = View.VISIBLE
        }

        hideinfo.setOnClickListener {
            textFulName.visibility = View.INVISIBLE
            textUserName.visibility = View.INVISIBLE
            textEmail.visibility = View.INVISIBLE
            textPassword.visibility = View.INVISIBLE
            textGender.visibility = View.INVISIBLE
            hideinfo.visibility = View.INVISIBLE
        }

        register.setOnClickListener {

            //variable intializese for sharedpreferences
            val Gen = if (female.isChecked) "Female" else if (male.isChecked) "Male" else ""

            if (fullname.text.isNullOrBlank() || username.text.isNullOrBlank() || email.text.isNullOrBlank() || password.text.isNullOrBlank() || retypePassword.text.isNullOrBlank() )
                Toast.makeText(this,"please fill all the inputs.",Toast.LENGTH_LONG).show()
            else if (Gen == "")
                Toast.makeText(this,"please select Gender.",Toast.LENGTH_LONG).show()
            else if (password.text.toString() != retypePassword.text.toString())
                Toast.makeText(this,"password and retype are not same",Toast.LENGTH_LONG).show()
            else {

                val editor = sharedPreferences.edit()
                editor.run {
                    putString(full_name, fullname.text.toString())
                    putString(user_name, username.text.toString())
                    putString(_email, email.text.toString())
                    putString(_password, password.text.toString())
                    putString(_gender, Gen)
                }
                    .apply()
                Toast.makeText(this,"registered",Toast.LENGTH_LONG).show()
            }

        }


    }


}