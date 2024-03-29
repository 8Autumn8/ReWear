package com.example.rewear.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.MainActivity
import com.example.rewear.R
import com.example.rewear.createUser.CreateUserActivity


class LoginActivity : AppCompatActivity(),  LoginContract.View{
    //Declare MVP
    private var presenter: LoginContract.Presenter? = null
    private var uid = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //MVP Initialized
        presenter = LoginPresenter(this)

        // set on-click listener
        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            // your code to perform when the user clicks on the button
            val userField = findViewById<EditText>(R.id.txtUserNameLogin).text.toString()
            val pwdField = findViewById<EditText>(R.id.pwdLogin).text.toString()
            // supposed to return a number

            (presenter as LoginPresenter).verifyUser(userField, pwdField)
            if (uid != -1){
                val intent = Intent(this, MainActivity::class.java)
                // save the currently logged in user for later use in the app.
                intent.putExtra("user_id", uid)
                intent.putExtra("CURR_FRAG", "CLOSET")
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext,
                "Cannot log in. Try again, or create a new user.",
                Toast.LENGTH_LONG).show()
            }
        }

        findViewById<TextView>(R.id.createAccount).setOnClickListener {
            startActivity(Intent(this, CreateUserActivity::class.java))
        }

    }

    override fun getContext(): Context{
        return this
    }

    override fun returnVerifiedUserID(userID: Int) {
        uid = userID
    }

}

