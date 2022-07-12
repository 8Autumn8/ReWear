package com.example.rewear.viewUser

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rewear.R
import com.example.rewear.groups.GroupsContract
import com.example.rewear.groups.GroupsPresenter
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

    }
}