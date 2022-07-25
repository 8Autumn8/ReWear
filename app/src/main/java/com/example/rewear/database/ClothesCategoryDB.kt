package com.example.rewear.database

import com.example.rewear.objects.ClothesCategoryData
import com.example.rewear.objects.UserBelongsToData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement

class ClothesCategoryDB : ClothesCategoryInterface, GenerateConnection() {
    override fun addClothesCategory(clothescategory: ClothesCategoryData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            conn!!.createStatement().execute("INSERT INTO ClothesCategory(user_id, name) " +
                    "VALUES (${clothescategory.user_id},'${clothescategory.name}');")
        }
        runBlocking { job.join() }
    }

    override fun addClothesCategory(clothescategory: List<ClothesCategoryData>) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            for (clothes: ClothesCategoryData in clothescategory){
                conn!!.createStatement().execute("INSERT IGNORE INTO ClothesCategory(user_id, name) " +
                        "VALUES (${clothes.user_id},'${clothes.name}');")
            }

        }
        runBlocking { job.join() }
    }


    override fun deleteClothesCategory(category_id: Int) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            conn!!.createStatement().execute("DELETE FROM ClothesCategory " +
                    "WHERE category_id = " + category_id  )
        }
        runBlocking { job.join() }
    }

    override fun getClothesCategory(category_id: Int) : ClothesCategoryData?{
        var toReturn: ClothesCategoryData? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM ClothesCategory " +
                    "WHERE category_id = " + category_id)

            while (rs != null && rs.next()) {
                toReturn = ClothesCategoryData(Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString()),rs.getString(3).toString(),rs.getString(4).toString())
            }

        }
        runBlocking { job.join() }
        return toReturn
    }

    override fun getClothesCategory(user_id: Int, clothesID: Int) : ClothesCategoryData?{
        var toReturn: ClothesCategoryData? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM ClothesCategory " +
                    "WHERE user_id = ${user_id} AND name = ${clothesID};")

            while (rs != null && rs.next()) {
                toReturn = ClothesCategoryData(Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString()),rs.getString(3).toString(),rs.getString(4).toString())
            }

        }
        runBlocking { job.join() }
        return toReturn
    }

    override fun updateClothesCategory(clothescategory: ClothesCategoryData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val st: Statement = conn!!.createStatement()
            st.execute("UPDATE ClothesCategory " +
                    "SET user_id = '${clothescategory.user_id}', name = '${clothescategory.name}, description = '${clothescategory.description},' " +
                    "WHERE category_id = ${clothescategory.category_id};"
            )
        }
        runBlocking { job.join() }
    }


    override fun getClothesCategoryByUserID(user_id: Int) : List<ClothesCategoryData>?{
        var toReturn: MutableList<ClothesCategoryData>? = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM ClothesCategory " +
                    "WHERE user_id = " + user_id)

            while (rs != null && rs.next()) {
                toReturn!!.add(ClothesCategoryData(Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString()),rs.getString(3).toString(),null))
            }

        }
        runBlocking { job.join() }
        return toReturn
    }

}