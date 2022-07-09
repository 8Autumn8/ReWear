package com.example.rewear

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.*


class DataBaseHelper : AppCompatActivity() {
    private var conn: Connection? = null
    private var username: String = "belinda"
    private val password: String = "!adminpassword1"
    var res = ""

    fun getUser() {
        var result: String = ""
        val job = CoroutineScope(Dispatchers.IO).launch {
            createConnection()
            //Get Data
            if (conn != null){
                var rs:ResultSet? = null
                val st: Statement = conn!!.createStatement()
                rs = st.executeQuery("SELECT * FROM User")
                val rsmd: ResultSetMetaData = rs!!.getMetaData()
                //Loop through Data
                while (rs!!.next()) {
                    result += rs!!.getString(2).toString().toString() + "\n"
                }
            }

        }
        runBlocking {
            job.join()
        }  //Program will wait until job is done

        println(result)
        println("The End")
    }
    private fun createConnection(){

        try {
            //Connect to Database
            Class.forName("com.mysql.jdbc.Driver")
            val url = "jdbc:mysql://rewea.mysql.database.azure.com:3306/reweardb"
            conn = DriverManager.getConnection(url, "belinda", "!adminpassword1")

        } catch (e: Exception) {
            e.printStackTrace();
        }
    }
}