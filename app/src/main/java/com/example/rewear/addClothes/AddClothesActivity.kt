package com.example.rewear.addClothes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rewear.R

class AddClothesActivity : AppCompatActivity(), AddClothesContract.View {

    private var presenter: AddClothesContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_clothes)

        val presenter = AddClothesPresenter(this)
    }
}