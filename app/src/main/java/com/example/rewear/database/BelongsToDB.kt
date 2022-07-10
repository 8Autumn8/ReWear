package com.example.rewear.database

import com.example.rewear.objects.BelongsToData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Connection
import java.sql.ResultSet

class BelongsToDB : BelongsToInterface, GenerateConnection(){
    private var conn: Connection? = null

    override fun getBelongsTo(username: String): BelongsToData? {
        var belongsTo: BelongsToData? = null
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
                belongsTo = BelongsToData(Integer.parseInt(rs.getString(1).toString()),
                    rs.getString(2).toString()
                )
            }

        }
        runBlocking { job.join() }
        return belongsTo
    }

    override fun addBelongsTo(belongsTo: BelongsToData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            if (conn == null)
               return@launch

            conn!!.createStatement().execute("INSERT INTO BelongsTo(group_id, username) VALUES ('${belongsTo.groupID}', '${belongsTo.username}")
        }
        runBlocking { job.join() }
    }

    override fun deleteBelongsTo(){

    }
}