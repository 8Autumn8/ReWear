package com.example.rewear.database

import com.example.rewear.objects.ClothesData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement
import com.example.rewear.Utility
import java.io.ByteArrayInputStream
import java.sql.Blob
import java.sql.PreparedStatement


class ClothesDB: ClothesInterface, GenerateConnection() {
    val utility = Utility()
    override fun addClothes(clothesObject: ClothesData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            //getData
            val query = "INSERT INTO Clothes (user_id, clothes_pic, clothes_desc, date_created) VALUES (?,?,?,?);"
            val st: Statement = conn!!.createStatement()
            val pstmt: PreparedStatement = conn.prepareStatement(query)
            pstmt.setInt(1, clothesObject.user_id!!)
            pstmt.setBytes(2, clothesObject.clothes_pic!!)
            pstmt.setString(3, clothesObject.clothes_desc!!)
            pstmt.setString(4, clothesObject.date_created!!)
            pstmt.execute()



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

            while (rs != null && rs.next()) {
                //NEED TO ADD ON TO THIS
                var blob:Blob = rs.getBlob(3)
                clothes = ClothesData(
                    Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString()),


                    blob.getBytes(1L, blob.length().toInt()),
                    rs.getString(4).toString(),
                    utility.parseDate(rs.getString(5).toString())
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

    override fun getClothesByID(clothesCategory: Int) : List<ClothesData>? {
        var toReturn: MutableList<ClothesData>? = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn!!.createStatement()
            rs = st.executeQuery(
                "SELECT Clothes.* " +
                        "FROM Clothes, ClothesBelongsTo " +
                        "WHERE Clothes.clothes_id = ClothesBelongsTo.clothes_id " +
                        "AND ClothesBelongsTo.category_id = '${clothesCategory}'"
            )

            while (rs != null && rs.next()) {
                val blob: Blob? = rs.getBlob(3)
                //NEED TO ADD ON TO THIS
                toReturn!!.add(
                    ClothesData(
                        Integer.parseInt(rs.getString(1).toString()),
                        Integer.parseInt(rs.getString(2).toString()),
                        rs.getBytes(3),
                        rs.getString(4).toString(),
                        rs.getString(5).toString()
                    )
                )
            }
        }
        runBlocking { job.join() }
        return toReturn
    }

    override fun countClothes(user_id: Int): Int?{
        var toReturn: Int = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn!!.createStatement()
            rs = st.executeQuery("SELECT COUNT(Clothes.clothes_id) " +
                    "FROM Clothes " +
                    "WHERE clothes.user_id = ${user_id}"
            )

            if (rs != null && rs.next()) {
                toReturn = rs.getInt(1)
            }
        }
        runBlocking { job.join() }  //Program will wait until job is done

        return toReturn
    }

}