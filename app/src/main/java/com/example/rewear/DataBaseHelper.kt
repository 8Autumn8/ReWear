package com.example.rewear

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.sql.*
import java.util.Properties


class DataBaseHelper : AppCompatActivity(){
    private var conn: Connection? = null
    private var username: String = "belinda"
    private val password: String = "!adminpassword1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun checkUsernamePassword(): Boolean{
        getConnection();
        //executeMySQLQuery();
        return true;
    }

    fun getUser(){
        getConnection()
    }

    private fun getConnection() {
        var textView: String = ""
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection("jdbc:mysql://rewea.mysql.database.azure.com:3306/reweardb?useSSL=true", username, password)
        } catch (ex: ClassNotFoundException) {
            ex.printStackTrace();
            textView = "ERROR"
        } catch (ex: SQLException) {
            ex.printStackTrace();
            textView = "FAILURE"
        }
    }
}

