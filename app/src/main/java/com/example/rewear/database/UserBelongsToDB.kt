package com.example.rewear.database

import com.example.rewear.objects.UserBelongsToData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Connection
import java.sql.ResultSet

class UserBelongsToDB : UserBelongsToInterface, GenerateConnection(){
    private var conn: Connection? = null

    override fun getUserBelongsTo(user_id: Int): List<UserBelongsToData>? {
        var toReturn: MutableList<UserBelongsToData> = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM BelongsTo " +
                    "WHERE user_id = " + user_id)

            while (rs != null && rs.next()) {
                toReturn.add(UserBelongsToData(Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString())))
            }

        }
        runBlocking { job.join() }
        return toReturn
    }

    override fun addUserBelongsTo(belongsTo: UserBelongsToData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            if (conn == null)
                return@launch

            conn!!.createStatement().execute("INSERT INTO BelongsTo(group_id, username) " +
                    "VALUES ('${belongsTo.group_id}', '${belongsTo.user_id}")
        }
        runBlocking { job.join() }
    }

    override fun deleteUserBelongsTo(belongsTo: UserBelongsToData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            if (conn == null)
                return@launch

            conn!!.createStatement().execute("DELETE FROM UserBelongsTo " +
                                             "WHERE user_id = ${belongsTo.user_id} AND group_id = ${belongsTo.group_id}"  )
        }
        runBlocking { job.join() }

    }
}