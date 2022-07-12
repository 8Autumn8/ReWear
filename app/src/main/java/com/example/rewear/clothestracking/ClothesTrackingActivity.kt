package com.example.rewear.clothestracking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.R

class ClothesTrackingActivity : AppCompatActivity(), ClothesTrackingContract.View {

    private var presenter: ClothesTrackingContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes_tracking)

        presenter = ClothesTrackingPresenter(this)
    }
}