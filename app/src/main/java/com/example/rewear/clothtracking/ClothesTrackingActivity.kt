package com.example.rewear.clothtracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rewear.R

class ClothesTrackingActivity : AppCompatActivity(), ClothesTrackingContract.View {

    private var presenter: ClothesTrackingContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes_tracking)

        presenter = ClothesTrackingPresenter(this)
    }
}