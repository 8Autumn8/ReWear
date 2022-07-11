package com.example.rewear.database

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.rewear.objects.ClothesData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Date
import java.sql.ResultSet
import java.sql.Statement
import java.time.LocalDate


class ClothesDB: ClothesInterface, GenerateConnection() {
    override fun addClothes(clothesObject: ClothesData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            //getData
            val st: Statement = conn!!.createStatement()
            st.execute("INSERT INTO Clothes(clothes_id,user_id,clothes_pic,clothes_desc,date_created) " +
                    "VALUES (${clothesObject.clothes_id},${clothesObject.user_id}, ${clothesObject.clothes_pic}, ${clothesObject.clothes_desc}, ${clothesObject.date_created});")

        }
        runBlocking { job.join() }
    }

    override fun deleteClothes(clothes_id: Int) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.execute("DELETE FROM Clothes " +
                    "WHERE clothes_id = ${clothes_id};"
            )
        }

        runBlocking { job.join() }
    }

    override fun getClothes(clothes_id: Int) : ClothesData? {
        var clothes: ClothesData? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn!!.createStatement()
            rs = st.executeQuery("SELECT * " +
                    "FROM Clothes " +
                    "WHERE clothes_id = '${clothes_id}'"
            )

            if (rs.next()) {
                //NEED TO ADD ON TO THIS
                clothes = ClothesData(
                    Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString()),
                    rs.getString(3).toString(),
                    rs.getString(4).toString(),
                    null
                )
            }
        }
        runBlocking { job.join() }  //Program will wait until job is done

        return clothes
    }

    override fun updateClothes(clothesObject: ClothesData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val st: Statement = conn!!.createStatement()
            st.execute("UPDATE Clothes" +
                    "SET user_id = '${clothesObject.user_id}', " +
                        "clothes_pic = '${clothesObject.clothes_pic}', " +
                        "username = '${clothesObject.clothes_desc}', " +
                        "clothes_desc = '${clothesObject.clothes_desc}', " +
                        "date_created = '${clothesObject.date_created}' " +
                    "WHERE clothes_id = ${clothesObject.clothes_id};"
            )
        }
        runBlocking { job.join() }
    }
}