package com.example.rewear.database

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.rewear.objects.ClothesData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement
import com.example.rewear.Utility
import com.example.rewear.objects.ClothesCategoryData
import java.io.ByteArrayInputStream
import java.sql.Blob
import java.sql.PreparedStatement
import java.time.LocalDateTime


class ClothesDB: ClothesInterface, GenerateConnection() {
    val utility = Utility()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun addClothes(clothesObject: ClothesData) : Int?{
        var toReturn: Int? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            //getData
            val query =
                "INSERT INTO Clothes (user_id, clothes_pic, clothes_desc, date_created) VALUES (?,?,?,?);"
            val pstmt: PreparedStatement = conn.prepareStatement(query)
            pstmt.setInt(1, clothesObject.user_id!!)
            pstmt.setBytes(2, clothesObject.clothes_pic!!)
            pstmt.setString(3, clothesObject.clothes_desc!!)
            pstmt.setString(4, LocalDateTime.now().toString())
            pstmt.execute()

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT LAST_INSERT_ID();")

            if (rs != null && rs.next()) {
                toReturn = rs.getInt(1)
            }

        }
        runBlocking { job.join() }
        return toReturn
    }

    override fun deleteClothes(clothes_id: Int) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.execute(
                "DELETE FROM Clothes " +
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
                var blob: Blob = rs.getBlob(3)
                ClothesData(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBytes(3),
                    rs.getString(4).toString(),
                    rs.getString(5).toString(),
                    rs.getInt(6),
                    rs.getString(7).toString(),
                    rs.getInt(8),
                    rs.getString(9).toString()
                )
            }
        }
        runBlocking { job.join() }  //Program will wait until job is done

        return clothes
    }

    override fun updateClothes(clothesObject: ClothesData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val query = "UPDATE Clothes " +
                    "SET user_id = ?, " +
                    "clothes_pic = ?, " +
                    "clothes_desc = ?, " +
                    "date_created = ? " +
                    "WHERE clothes_id = ?;"
            val pstmt: PreparedStatement = conn.prepareStatement(query)
            pstmt.setInt(1, clothesObject.user_id!!)
            pstmt.setBytes(2, clothesObject.clothes_pic!!)
            pstmt.setString(3, clothesObject.clothes_desc!!)
            pstmt.setString(4, clothesObject.date_created!!)
            pstmt.setInt(5, clothesObject.clothes_id!!)
            pstmt.executeUpdate()
        }
        runBlocking { job.join() }
    }



    override fun getClothesByClothesID(clothes_id: List<Int>?): List<ClothesData>? {
        var toReturn: MutableList<ClothesData>? = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            var rs: ResultSet?
            val st: Statement = conn!!.createStatement()
            for (id in clothes_id!!) {

                rs = st.executeQuery(
                    "SELECT Clothes.*, " +
                            "GROUP_CONCAT( DISTINCT clothescategory.category_id SEPARATOR ', ' ) AS category_id, " +
                            "GROUP_CONCAT( DISTINCT NAME SEPARATOR ', ' ) AS names, " +
                            "IFNULL(TimesWorn,0), " +
                            "IFNULL(LastWorn,'N/A') " +
                            "FROM Clothes " +
                            "LEFT JOIN ClothesBelongsTo ON Clothes.clothes_id = ClothesBelongsTo.clothes_id " +
                            "LEFT JOIN clothescategory ON ClothesBelongsTo.category_id = clothescategory.category_id " +
                            "LEFT JOIN ( SELECT clothes_id, COUNT(*) AS TimesWorn, MAX( date ) AS LastWorn FROM dateworn GROUP BY clothes_id ) AS tblDateWorn ON Clothes.clothes_id = tblDateWorn.clothes_id " +
                            "WHERE " +
                            "Clothes.clothes_id =  ${id} " +
                            "GROUP BY clothes_id, user_id, clothes_pic, clothes_desc, date_created, timesworn, lastworn;"
                )
                if (rs != null && rs.next()) {
                    toReturn!!.add(
                        ClothesData(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getBytes(3),
                            rs.getString(4).toString(),
                            rs.getString(5).toString(),
                            rs.getInt(6),
                            rs.getString(7).toString(),
                            rs.getInt(8),
                            rs.getString(9).toString()
                        )
                    )
                }
            }


        }
        runBlocking { job.join() }
        return toReturn
    }

    override fun getClothesByUserID(user_id: Int?): List<ClothesData>? {
        var toReturn: MutableList<ClothesData>? = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn.createStatement()
            rs = st.executeQuery(
                "SELECT Clothes.*, IFNULL(ClothesBelongsTo.category_id,0) Category_id, IFNULL(Name,\"\"), IFNULL(TimesWorn,0),IFNULL(LastWorn,\"N/A\") " +
                        "FROM Clothes " +
                        "LEFT JOIN ClothesBelongsTo ON Clothes.clothes_id = ClothesBelongsTo.clothes_id " +
                        "LEFT JOIN clothescategory ON ClothesBelongsTo.category_id = clothescategory.category_id " +
                        "LEFT JOIN ( SELECT clothes_id, COUNT(*) AS TimesWorn, MAX( date ) AS LastWorn FROM dateworn GROUP BY clothes_id ) AS tblDateWorn ON Clothes.clothes_id = tblDateWorn.clothes_id " +
                        "WHERE Clothes.user_id = ${user_id};"
            )

            while (rs != null && rs.next()) {
                toReturn!!.add(
                    ClothesData(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getBytes(3),
                        rs.getString(4).toString(),
                        rs.getString(5).toString(),
                        rs.getInt(6),
                        rs.getString(7).toString(),
                        rs.getInt(8),
                        rs.getString(9).toString()
                    )
                )
            }
        }
        runBlocking { job.join() }
        return toReturn
    }



    override fun getClothesIDByCategory(category: Int): List<Int>? {
        var toReturn: MutableList<Int>? = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn.createStatement()
            rs = st.executeQuery(
                "SELECT Clothes.clothes_id " +
                        "FROM " +
                        "Clothes " +
                        "LEFT JOIN ClothesBelongsTo ON Clothes.clothes_id = ClothesBelongsTo.clothes_id " +
                        "WHERE clothesbelongsto.category_id = ${category};"
            )

            while (rs != null && rs.next()) {
                toReturn!!.add(rs.getInt(1))
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