package com.example.rewear.createUser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.R
import com.example.rewear.login.LoginActivity

class CreateUserActivity : AppCompatActivity(), CreateUserContract.View {

    private var presenter: CreateUserContract.Presenter? = null
    var userExistence: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val btnCreateUser = findViewById<Button>(R.id.createAccountButton)

        presenter = CreateUserPresenter(this)

        btnCreateUser.setOnClickListener {
            val fName = findViewById<EditText>(R.id.createAccountFirstName).text.toString()
            val lName = findViewById<EditText>(R.id.createAccountLastName).text.toString()
            val username = findViewById<EditText>(R.id.createAccountUsername).text.toString()
            val password = findViewById<EditText>(R.id.createAccountPassword).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.createAccountConfirmPassword).text.toString()

            // Form checks
            if (fName == "" || lName == "" || username == "" || password == "" || confirmPassword == "") {
                Toast.makeText(applicationContext,
                    "One or more fields have not been filled out",
                    Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            else if (!password.equals(confirmPassword)) {
                Toast.makeText(
                    applicationContext,
                    "Make sure your passwords match exactly",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            // Create the user and add them to the database
            (presenter as CreateUserPresenter).addUser(fName,lName,username,password)

            // tell user if it was not added to database.

            if (userExistence == false) {
                Toast.makeText(
                    applicationContext,
                    "Unable to create user. Please try again later.",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                // switch to the login screen
                val intent = Intent(this@CreateUserActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun returnCheckUserExist(userExists: Boolean) {
        userExistence = userExists
    }
}