package com.example.rewear.groups

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.R

class GroupsActivity : AppCompatActivity(), GroupsContract.View {

    private var presenter: GroupsContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)

        presenter = GroupsPresenter(this)
    }
}