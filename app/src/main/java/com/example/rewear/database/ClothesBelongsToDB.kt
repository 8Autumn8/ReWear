package com.example.rewear.database

import com.example.rewear.objects.ClothesBelongsToData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet


class ClothesBelongsToDB : ClothesBelongsToInterface, GenerateConnection(){

    override fun getClothesBelongsTo(user_id: Int) : List<ClothesBelongsToData>?{
        var toReturn: MutableList<ClothesBelongsToData> = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM BelongsTo " +
                    "WHERE user_id = " + user_id)

            while (rs != null && rs.next()) {
                toReturn.add(
                    ClothesBelongsToData(Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString()))
                )
            }

        }
        runBlocking { job.join() }
        return toReturn

    }

    override fun addClothesBelongsTo(clothesBelongsToData: ClothesBelongsToData){

        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            conn!!.createStatement().execute("INSERT INTO ClothesBelongTo " +
                    "VALUES (${clothesBelongsToData.clothes_id},${clothesBelongsToData.category_id};")
        }
        runBlocking { job.join() }
    }

    override fun addClothesBelongsTo(clothesBelongsToData: List<ClothesBelongsToData>){

        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            for (clothes: ClothesBelongsToData in clothesBelongsToData){
                conn!!.createStatement().execute("INSERT IGNORE INTO ClothesBelongsTo " +
                        "VALUES ('${clothes.clothes_id}','${clothes.category_id}');")
            }

        }
        runBlocking { job.join() }
    }

    override fun deleteClothesBelongsTo(clothesBelongsToData: ClothesBelongsToData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            conn!!.createStatement().execute("DELETE FROM UserBelongsTo " +
                    "WHERE user_id = ${clothesBelongsToData.clothes_id} AND group_id = ${clothesBelongsToData.category_id}"  )
        }
        runBlocking { job.join() }

    }

    override fun deleteClothesBelongsTo(clothesBelongsToData: List<ClothesBelongsToData>){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            for (clothes: ClothesBelongsToData in clothesBelongsToData){
                conn!!.createStatement().execute("DELETE FROM UserBelongsTo " +
                        "WHERE user_id = ${clothes.clothes_id} AND group_id = ${clothes.category_id}"  )
            }

        }
        runBlocking { job.join() }
    }


}