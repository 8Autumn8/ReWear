package com.example.rewear.groups

import android.R
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class GroupsActivity : AppCompatActivity(), GroupsContract.View {
    private var presenter: GroupsContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.rewear.R.layout.activity_groups)

        presenter = GroupsPresenter(this)

    }
}