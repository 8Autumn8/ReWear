package com.example.rewear.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.R
import com.example.rewear.addClothes.AddClothesActivity


class LoginActivity : AppCompatActivity(),  LoginContract.View{
    //Declare MVP
    private var presenter: LoginContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        //MVP Initialized
        presenter = LoginPresenter(this)

        // set on-click listener
        btnLogin.setOnClickListener {
            // your code to perform when the user clicks on the button
            val userField = (findViewById<View>(R.id.txtUserNameLogin) as EditText).getText().toString()
            val pwdField = (findViewById<View>(R.id.pwdLogin) as EditText).getText().toString()

            // TODO: Notify user if any fields are blank or if given user & password combo is incorrect.

            if ((presenter as LoginPresenter).verifyUser(userField, pwdField)){
                val intent = Intent(this, AddClothesActivity::class.java)
                startActivity(intent)
            }

        }

    }

}

