package com.example.rewear.database

import java.sql.Connection
import java.sql.DriverManager

open class GenerateConnection() {
    fun createConnection() : Connection?{
        var conn: Connection? = null
        try {
            //Connect to Database
            Class.forName("com.mysql.jdbc.Driver")
            val url = "jdbc:mysql://rewea.mysql.database.azure.com:3306/reweardb"
            conn = DriverManager.getConnection(url, "belinda", "!adminpassword1")

        } catch (e: Exception) {
            e.printStackTrace();
        }
        return conn
    }
}