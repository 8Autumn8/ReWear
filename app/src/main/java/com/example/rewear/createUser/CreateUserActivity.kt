package com.example.rewear.createUser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rewear.R
import com.example.rewear.login.LoginPresenter

class CreateUserActivity : AppCompatActivity(), CreateUserContract.View {

    private var presenter: CreateUserContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        presenter = CreateUserPresenter(this)
    }
}