package com.example.rewear

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mysql.jdbc.Statement
import java.sql.*


class DataBaseHelper : AppCompatActivity() {
    private var conn: Connection? = null
    private var username: String = "belinda"
    private val password: String = "!adminpassword1"
    var res = ""


    fun getUser() {
        //SampleOne()
        Log.d("ERROR", "Failure")
    }
}