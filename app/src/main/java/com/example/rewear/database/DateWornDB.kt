package com.example.rewear.database

import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.DateWorn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement
import java.text.SimpleDateFormat
import java.util.*
import com.example.rewear.Utility

class DateWornDB: DateWornInterface, GenerateConnection(){
    val utility = Utility()
    override fun getDateWorn(clothes_id: Int) : List<DateWorn>? {
        var toReturn: MutableList<DateWorn> = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM BelongsTo " +
                    "WHERE clothes_id = " + clothes_id)

            while (rs != null && rs.next()) {
                //need to fix
                toReturn.add(
                    DateWorn(Integer.parseInt(rs.getString(1).toString()),
                        utility.parseDate(rs.getString(2).toString()))
                )
            }

        }
        runBlocking { job.join() }
        return toReturn

    }


    override fun addDateWorn(dateWorn: DateWorn) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            conn!!.createStatement().execute("INSERT IGNORE INTO ClothesBelongTo " +
                    "VALUES (${dateWorn.clothes_id},${dateWorn.date};")
        }
        runBlocking { job.join() }
    }

    override fun deleteDateWorn(dateWorn: DateWorn){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.execute("DELETE FROM User " +
                    "WHERE clothes_id = ${dateWorn.clothes_id} AND date = ${dateWorn.date};"
            )
        }

        runBlocking { job.join() }
    }
}