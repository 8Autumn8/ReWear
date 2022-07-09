package com.example.rewear.closet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rewear.R

class ClosetActivity : AppCompatActivity(), ClosetContract.View{

    private var presenter: ClosetContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_closet)

        presenter = ClosetPresenter(this)
    }
}