package com.example.rewear.database

import com.example.rewear.objects.LikedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement

class LikedDB:LikedInterface, GenerateConnection() {
    override fun addLiked(info: LikedData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.executeUpdate("INSERT INTO Liked " +
                    "VALUES(${info.picture_id} AND user_id = ${info.user_id}); "
            )
        }

        runBlocking { job.join() }
    }

    override fun deleteLiked(info: LikedData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.execute("DELETE FROM Liked " +
                    "WHERE picture_id = ${info.picture_id} AND user_id = ${info.user_id};"
            )
        }

        runBlocking { job.join() }
    }

    override fun getLiked(picture_id: Int) : List<LikedData>? {
        var toReturn: MutableList<LikedData> = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM Liked " +
                    "WHERE picture_id = " + picture_id)

            while (rs != null && rs.next()) {
                toReturn.add(
                    LikedData(Integer.parseInt(rs.getString(1).toString()),
                        Integer.parseInt(rs.getString(2).toString()))
                )
            }

        }
        runBlocking { job.join() }
        return toReturn
    }

    override fun getUserLiked(user_id: Int, group_id: Int) : List<LikedData>? {
        var toReturn: MutableList<LikedData> = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT Liked.picture_id, LikedUser.user_id AS 'MyLike', COUNT(*) AS Count " +
                    "From Liked " +
                    "LEFT JOIN (SELECT user_id, picture_id " +
                    "From Liked " +
                    "WHERE user_id = ${user_id}) AS LikedUser " +
                    "ON Liked.picture_id = LikedUser.picture_id " +
                    "WHERE Liked.picture_id IN (SELECT picture_id FROM picturegroup WHERE group_id = ${group_id}) " +
                    "GROUP BY Liked.picture_id, LikedUser.user_id " +
                    "ORDER BY Liked.picture_id DESC;")

            while (rs != null && rs.next()) {
                if ( rs.getString(2) != null){
                    toReturn.add(
                        LikedData(Integer.parseInt(rs.getString(1).toString()),
                            Integer.parseInt(rs.getString(2).toString()), Integer.parseInt(rs.getString(3).toString()))
                    )
                } else {
                    LikedData(Integer.parseInt(rs.getString(1).toString()),
                        0, Integer.parseInt(rs.getString(3).toString()))

                }

            }

        }
        runBlocking { job.join() }
        return toReturn
    }

}