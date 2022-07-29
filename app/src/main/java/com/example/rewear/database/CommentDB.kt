package com.example.rewear.database

import com.example.rewear.objects.CommentData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement

class CommentDB: CommentInterface, GenerateConnection() {
    override fun addComment(comment: CommentData)  {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            //getData
            val st: Statement = conn!!.createStatement()
            st.execute("INSERT INTO Comment(picture_id,user_id) " +
                    "VALUES (${comment.picture_id},${comment.user_id}; ")

        }
        runBlocking { job.join() }
    }

    override fun deleteComment(comment_id: Int){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.execute("DELETE FROM User " +
                    "WHERE comment_id = ${comment_id};"
            )
        }

        runBlocking { job.join() }
    }

    override fun getComments(picture_id: Int) : List<CommentData>?{
        var toReturn: MutableList<CommentData> = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM Comment " +
                    "WHERE picture_id = " + picture_id)

            while (rs != null && rs.next()) {
                toReturn.add(
                    CommentData(Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString()),
                        Integer.parseInt(rs.getString(3).toString()),
                        rs.getString(4).toString())
                )
            }

        }
        runBlocking { job.join() }
        return toReturn
    }

    override fun updateComment(commentData: CommentData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val st: Statement = conn!!.createStatement()
            st.execute("UPDATE User" +
                    "SET comment_content = '${commentData.comment_content}' " +
                    "WHERE comment_id = ${commentData.comment_id};"
            )
        }
        runBlocking { job.join() }
    }
}