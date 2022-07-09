package com.example.rewear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.sql.*


open class TestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var btButton = findViewById<Button>(R.id.btButton)

        btButton.setOnClickListener(View.OnClickListener { v: View? ->
            //OnClick code goes here
/*
            val job = GlobalScope.run {
                val rs = sampleOne()
            }

 */
            var result: String? = null

            val job = CoroutineScope(IO).launch {
                result = SampleOne()
            }
            runBlocking {
                job.join()
            }  //Program will wait until job is done

            println(result)
            println("The End")

/*
            //Loop through Data
            while (rs.next()) {
                println(rs.getString(2).toString().toString() + "\n")
            }

 */
        })


    }

    private fun SampleOne(): String? {

        var result: String = ""

        var rs: ResultSet? = null
        try {
            //Connect to Database
            Class.forName("com.mysql.jdbc.Driver")
            val url = "jdbc:mysql://rewea.mysql.database.azure.com:3306/quickstartdb"
            val con: Connection = DriverManager.getConnection(url, "belinda", "!adminpassword1")

            //Get Data
            val st: Statement = con.createStatement()
            rs = st.executeQuery("SELECT * FROM inventory")
            val rsmd: ResultSetMetaData = rs.getMetaData()

            //Loop through Data
            while (rs.next()) {
                result += rs.getString(2).toString().toString() + "\n"
            }

        } catch (e: Exception) {
            e.printStackTrace();
        }
        return result
    }
}
