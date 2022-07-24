package com.example.rewear.viewUser

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rewear.R
import com.example.rewear.editProfile.EditProfileActivity
import com.example.rewear.login.LoginActivity
import com.example.rewear.objects.UserData
import com.example.rewear.profile.ProfileContract
import com.example.rewear.profile.ProfilePresenter
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), ProfileContract.View {
    private var presenter: ProfileContract.Presenter? = null
    private var currentUser: UserData? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = ProfilePresenter(this)
        (presenter as ProfilePresenter).getCurrentUser(requireArguments().getInt("user_id"))
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // this is an incredibly ugly statement. (not anymore -belinda)
        if (currentUser == null) {
            Toast.makeText(
                context,
                "Error occurred while displaying profile",
                Toast.LENGTH_LONG
            ).show()
            returnToLoginPage()
            return
        }

        // change the dummy text to show the user information
        name.text = "${currentUser!!.first_name} ${currentUser!!.last_name}"
        usernameDisplay.text = currentUser!!.username

        // display the user's password when the "show password" button is pressed
        showPasswordButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                passwordDisplay.text = currentUser!!.password
            } else {
                passwordDisplay.text = "████████"
            }
        }

        // go to the edit user info page when this button is pressed
        editUserInformationButton.setOnClickListener {

            // i could probably turn this into a function but i'm lazy.
            val intent = Intent(activity, EditProfileActivity::class.java)
            intent.putExtra("user_id", currentUser!!.user_id.toString())
            startActivity(intent)
            (activity as Activity).overridePendingTransition(0, 0)
        }

        // delete the user and take them back to the login page
        deleteUserButton.setOnClickListener {
            presenter?.deleteCurrentUser(currentUser!!.user_id!!)
            returnToLoginPage()
        }
    }

    private fun returnToLoginPage() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        (activity as Activity).overridePendingTransition(0, 0)
    }

    override fun returnCurrentUser(databaseUser: UserData) {
        currentUser = databaseUser
    }
}