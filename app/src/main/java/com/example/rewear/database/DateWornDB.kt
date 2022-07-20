package com.example.rewear.database

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.rewear.Utility
import com.example.rewear.objects.ClothesData
import com.example.rewear.objects.DateWorn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement
import java.time.LocalDate
import java.util.*

class DateWornDB : DateWornInterface, GenerateConnection() {
    private val utility = Utility()
    override fun getDateWorn(clothes_id: Int): List<DateWorn> {
        val toReturn: MutableList<DateWorn> = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn.createStatement().executeQuery(
                "SELECT * " +
                        "FROM BelongsTo " +
                        "WHERE clothes_id = " + clothes_id
            )

            while (rs != null && rs.next()) {
                //need to fix
                toReturn.add(
                    DateWorn(
                        Integer.parseInt(rs.getString(1).toString()),
                        utility.parseDate(rs.getString(2).toString())
                    )
                )
            }

        }
        runBlocking { job.join() }
        return toReturn

    }


    override fun addDateWorn(dateWorn: DateWorn) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            conn.createStatement().execute(
                "INSERT INTO ClothesBelongTo " +
                        "VALUES (${dateWorn.clothes_id},${dateWorn.date};"
            )
        }
        runBlocking { job.join() }
    }

    override fun deleteDateWorn(dateWorn: DateWorn) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn.createStatement()
            st.execute(
                "DELETE FROM User " +
                        "WHERE clothes_id = ${dateWorn.clothes_id} AND date = ${dateWorn.date};"
            )
        }

        runBlocking { job.join() }
    }

    override fun getMostWorn(user_id: Int): ClothesData {
        val mostWorn = ClothesData()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn.createStatement().executeQuery(
                "SELECT Clothes.*, IFNULL(TimesWorn,0),IFNULL(LastWorn,\"\") " +
                        "FROM Clothes " +
                        "LEFT JOIN ( SELECT clothes_id, COUNT(*) AS TimesWorn, MAX( date ) AS LastWorn FROM dateworn GROUP BY clothes_id ) AS tblDateWorn ON Clothes.clothes_id = tblDateWorn.clothes_id " +
                        "WHERE Clothes.user_id = ${user_id} " +
                        "ORDER BY TimesWorn DESC " +
                        "LIMIT 1;"
            )

            if (rs != null && rs.next()) {
                //need to fix
                mostWorn.clothes_id = rs.getInt("clothes_id")
                mostWorn.user_id = rs.getInt("user_id")
                mostWorn.clothes_desc = rs.getString("clothes_desc")
                mostWorn.clothes_pic = rs.getBytes("clothes_pic")
            }

        }
        runBlocking { job.join() }
        return mostWorn
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getNumWornSinceDate(user_id: Int, date: Date): Int {
        var numWorn = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs = conn.createStatement().executeQuery(
                "SELECT count(*) " +
                        "FROM dateworn INNER JOIN clothes ON dateworn.clothes_id = clothes.clothes_id " +
                        "WHERE date BETWEEN '${date}' AND '${LocalDate.now()}' AND user_id = ${user_id}; "
            )

            if (rs != null && rs.next())
                numWorn = rs.getInt(1)

        }
        runBlocking { job.join() }
        return numWorn
    }
}