package com.example.rewear.viewUser

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.example.rewear.R
import com.example.rewear.profile.ProfileContract
import com.example.rewear.profile.ProfilePresenter


class ProfileFragment : Fragment(), ProfileContract.View  {
    private var presenter: ProfileContract.Presenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = ProfilePresenter(this)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // this is an incredibly ugly statement.
        Intent().getStringExtra("user_id")
        val currentUser = presenter?.getCurrentUser(Integer.parseInt(requireArguments().getString("user_id").toString()))

        if (currentUser == null) {
            Toast.makeText(context,
                "Error occurred while displaying profile",
                Toast.LENGTH_LONG).show()
            return
        }

        view.findViewById<TextView>(R.id.name).text = "${currentUser.first_name} ${currentUser.last_name}"

        view.findViewById<ToggleButton>(R.id.showPasswordButton).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                view.findViewById<TextView>(R.id.passwordDisplay).text = currentUser.password
            }
            else {
                view.findViewById<TextView>(R.id.passwordDisplay).text = "████████"
            }
        }
    }
}