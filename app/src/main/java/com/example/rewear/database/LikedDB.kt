package com.example.rewear.database

import com.example.rewear.objects.CommentData
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
            st.execute("INSERT INTO Liked " +
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

}