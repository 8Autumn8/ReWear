package com.example.rewear.editProfile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.MainActivity
import com.example.rewear.R
import com.example.rewear.objects.UserData

class EditProfileActivity: AppCompatActivity(), EditProfileContract.View {
    private var presenter: EditProfileContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        presenter = EditProfilePresenter(this)

        // get the current user
        val currentUser =
            presenter?.getCurrentUser(Integer.parseInt(intent.getStringExtra("user_id")!!))
                ?: return

        val newFirstName = findViewById<EditText>(R.id.updateAccountFirstName)
        val newLastName = findViewById<EditText>(R.id.updateAccountLastName)
        val newUsername = findViewById<EditText>(R.id.updateAccountUsername)
        val newPassword = findViewById<EditText>(R.id.updateAccountPassword)
        val newConfirmPassword = findViewById<EditText>(R.id.updateAccountConfirmPassword)

        // change the EditText hints to the current user information
        newFirstName.hint = currentUser.first_name
        newLastName.hint = currentUser.last_name
        newUsername.hint = currentUser.username
        newPassword.hint = currentUser.password

        // and make sure that the new password and confirmed password match if the user
        // decides to change that
        findViewById<Button>(R.id.updateAccountButton).setOnClickListener {
            val newFirstNameText = newFirstName.text.toString()
            val newLastNameText = newLastName.text.toString()
            val newUsernameText = newUsername.text.toString()
            val newPasswordText = newPassword.text.toString()
            val newConfirmPasswordText = newConfirmPassword.text.toString()

            // if one password field is filled out but not the other, tell the user
            // also i'm so sorry this is an atrocious boolean statement but it's understandable :)
            if (!(newPasswordText == "" && newConfirmPasswordText == "")) {
                if (newPasswordText != newConfirmPasswordText ||
                    newPasswordText == "" ||
                    newConfirmPasswordText == ""
                ) {
                    Toast.makeText(
                        applicationContext,
                        "To update password, make sure that the password field and confirm password fields match",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
            }

            try {
                // this crashes :)
                presenter!!.updateUser(
                    UserData( currentUser.user_id,
                        if (newFirstNameText != "") newFirstNameText else currentUser.first_name,
                        if (newLastNameText != "") newLastNameText else currentUser.last_name,
                        if (newUsernameText != "") newUsernameText else currentUser.username,
                        if (newPasswordText != "") newPasswordText else currentUser.password
                    )
                )
            }catch (exception: Exception) {
                Log.e("Updating user info:", exception.toString())
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user_id", currentUser.user_id.toString())
            intent.putExtra("CURR_FRAG", "CLOSET")
            startActivity(intent)
        }
    }
}