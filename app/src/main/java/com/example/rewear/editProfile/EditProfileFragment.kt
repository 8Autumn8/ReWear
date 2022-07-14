package com.example.rewear.editProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rewear.R
import com.example.rewear.objects.UserData

class EditProfileFragment: Fragment(), EditProfileContract.View {
    private var presenter: EditProfileContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = EditProfilePresenter(this)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get the current user
        val currentUser =
            presenter?.getCurrentUser(Integer.parseInt(requireArguments().getString("user_id").toString()))
                ?: return

        val newFirstName = view.findViewById<EditText>(R.id.updateAccountFirstName)
        val newLastName = view.findViewById<EditText>(R.id.updateAccountLastName)
        val newUsername = view.findViewById<EditText>(R.id.updateAccountUsername)
        val newPassword = view.findViewById<EditText>(R.id.updateAccountPassword)
        val newConfirmPassword = view.findViewById<EditText>(R.id.updateAccountConfirmPassword)

        // change the EditText hints to the current user information
        newFirstName.hint = currentUser.first_name
        newLastName.hint = currentUser.last_name
        newUsername.hint = currentUser.username
        newUsername.hint = currentUser.password


        // and make sure that the new password and confirmed password match if the user
        // decides to change that
        view.findViewById<Button>(R.id.updateAccountButton).setOnClickListener {
            val newFirstNameText = newFirstName.text.toString()
            val newLastNameText = newLastName.text.toString()
            val newUsernameText = newUsername.text.toString()
            val newPasswordText = newPassword.text.toString()

            // if one password field is filled out but not the other, tell the user
            if ((newPasswordText != "" && newConfirmPassword.text.toString() == "") ||
                (newPasswordText == "" && newConfirmPassword.text.toString() != "")) {
                Toast.makeText(context,
                "To update password, make sure that the password field and confirm password fields match",
                Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            presenter!!.updateUser(
                UserData( currentUser.user_id,
                    if (newFirstNameText != "") newFirstNameText else currentUser.first_name,
                    if (newLastNameText != "") newLastNameText else currentUser.last_name,
                    if (newUsernameText != "") newUsernameText else currentUser.username,
                    if (newPasswordText != "") newPasswordText else currentUser.password
                )
            )

            // go back to the main activity which I don't know how lmfaooooo my brain is *gone*
        }
    }
}