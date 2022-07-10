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

    override fun getBelongsTo(username: String): UserBelongsToData? {
        var belongsTo: UserBelongsToData? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()

            // pretty sure this guard clause just coses the coroutine early if there's no connection.
            // we'll see if it works when we try it out. If it no work, then I'll just wrap the whole
            // result set and stuff in a if statement. I just like guard clauses more
            if (conn == null)
                return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM BelongsTo " +
                    "WHERE username = $username")

            if (rs != null && rs.next()) {
                belongsTo = UserBelongsToData(Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString())
                )
            }

        }
        runBlocking { job.join() }
        return belongsTo
    }

    override fun addBelongsTo(belongsTo: UserBelongsToData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            if (conn == null)
                return@launch

            conn!!.createStatement().execute("INSERT INTO BelongsTo(group_id, username) VALUES ('${belongsTo.group_id}', '${belongsTo.user_id}")
        }
        runBlocking { job.join() }
    }

    override fun deleteBelongsTo(){

    }
}