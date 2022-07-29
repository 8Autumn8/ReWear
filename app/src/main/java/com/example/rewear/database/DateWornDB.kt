package com.example.rewear.database

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.rewear.objects.DateWornData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement
import java.util.*
import com.example.rewear.Utility
import com.example.rewear.objects.ClothesData
import java.time.LocalDate

class DateWornDB: DateWornInterface, GenerateConnection(){
    val utility = Utility()
    override fun getDateWorn(clothes_id: Int) : List<DateWornData>? {
        var toReturn: MutableList<DateWornData> = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM DateWorn " +
                    "WHERE clothes_id = " + clothes_id)

            while (rs != null && rs.next()) {
                //need to fix
                toReturn.add(
                    DateWornData(Integer.parseInt(rs.getString(1).toString()),
                        utility.parseDate(rs.getString(2).toString()))
                )
            }

        }
        runBlocking { job.join() }
        return toReturn

    }


    override fun addDateWorn(dateWorn: DateWornData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            conn!!.createStatement().execute("INSERT IGNORE INTO DateWorn " +
                    "VALUES (${dateWorn.clothes_id},'${dateWorn.date}');")
        }
        runBlocking { job.join() }
    }

    override fun deleteDateWorn(dateWorn: DateWornData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.execute("DELETE FROM DateWorn " +
                    "WHERE clothes_id = ${dateWorn.clothes_id} AND date = '${dateWorn.date}';"
            )
        }

        runBlocking { job.join() }
    }

    override fun getMostWorn(user_id: Int) : ClothesData?{
        var toReturn: ClothesData? = ClothesData()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery(
                "SELECT Clothes.*, IFNULL(TimesWorn,0),IFNULL(LastWorn,\"\") " +
                    "FROM Clothes " +
                    "LEFT JOIN ( SELECT clothes_id, COUNT(*) AS TimesWorn, MAX( date ) AS LastWorn FROM dateworn GROUP BY clothes_id ) AS tblDateWorn ON Clothes.clothes_id = tblDateWorn.clothes_id " +
                    "WHERE Clothes.user_id = ${user_id} " +
                    "ORDER BY TimesWorn DESC " +
                    "LIMIT 1;")

            if (rs != null && rs.next()) {
                //need to fix
                toReturn!!.clothes_id = rs.getInt("clothes_id")
                toReturn!!.user_id = rs.getInt("user_id")
                toReturn!!.clothes_desc = rs.getString("clothes_desc")
                toReturn!!.clothes_pic = rs.getBytes("clothes_pic")

            }

        }
        runBlocking { job.join() }
        return toReturn
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getNumWornSinceDate(user_id: Int, date: Date): Int?{
        var toReturn: Int = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            var rs = conn.createStatement().executeQuery(
                    "SELECT count(DISTINCT dateworn.clothes_id)  " +
                            "FROM dateworn INNER JOIN clothes ON dateworn.clothes_id = clothes.clothes_id " +
                            "WHERE date BETWEEN '${date}' AND '${LocalDate.now()}' AND user_id = ${user_id}; "
                )

            if (rs != null && rs.next())
                toReturn = rs.getInt(1)

        }
        runBlocking { job.join() }
        return toReturn
    }
}