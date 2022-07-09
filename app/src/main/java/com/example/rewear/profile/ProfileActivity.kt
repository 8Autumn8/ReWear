package com.example.rewear.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.R

class ProfileActivity : AppCompatActivity(), ProfileContract.View {

    // Declare MVP
    private var presenter: ProfileContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        presenter = ProfilePresenter(this)
    }
}