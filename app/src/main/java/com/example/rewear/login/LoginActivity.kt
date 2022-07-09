package com.example.rewear.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.addClothes.AddClothesActivity
import com.example.rewear.R


class LoginActivity : AppCompatActivity(),  LoginContract.View{
    //Declare MVP
    private var presenter: LoginContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById(R.id.btnLogin) as Button

        //MVP Initialized
        presenter = LoginPresenter(this)

        // set on-click listener
        btnLogin.setOnClickListener {
            // your code to perform when the user clicks on the button
            val userField = (findViewById<View>(R.id.txtUserNameLogin) as EditText).getText().toString()
            val pwdField = (findViewById<View>(R.id.pwdLogin) as EditText).getText().toString()
            if ((presenter as LoginPresenter).verifyUser(userField.toString(),pwdField.toString())){
                val intent = Intent(this, AddClothesActivity::class.java)
                startActivity(intent)
            }

        }

    }

}

