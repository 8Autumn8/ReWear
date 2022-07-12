package com.example.rewear.closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rewear.R
import com.example.rewear.groups.GroupsPresenter

class ClosetFragment : Fragment(), ClosetContract.View{

    private var presenter: ClosetContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = ClosetPresenter(this)
        return inflater.inflate(R.layout.fragment_closet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}