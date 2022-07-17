package com.example.rewear.database

import com.example.rewear.objects.ClothesBelongsToData
import com.example.rewear.objects.PictureGroupData
import com.example.rewear.objects.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement

class PictureGroupDB:PictureGroupInterface,GenerateConnection() {
    override fun addPictureGroup(picturegroup: PictureGroupData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            //getData
            val st: Statement = conn!!.createStatement()
            st.executeUpdate("INSERT INTO PictureGroupData(picture_id,group_id) " +
                    "VALUES (${picturegroup.picture_id},${picturegroup.group_id}; ")

        }
        runBlocking { job.join() }
    }

    override fun deletePictureGroup(picturegroup: PictureGroupData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.execute("DELETE FROM PictureGroup " +
                    "WHERE picture_id = ${picturegroup.picture_id} AND group_id = ${picturegroup.group_id};"
            )
        }

        runBlocking { job.join() }
    }

    override fun getPictureGroup(group_id: Int) : List<PictureGroupData>? {
        var toReturn: MutableList<PictureGroupData> = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn!!.createStatement()
            rs = st.executeQuery("SELECT * " +
                    "FROM PictureGroup " +
                    "WHERE group_id = '$group_id'; "
            )

            while (rs != null && rs.next()) {
                toReturn.add(
                    PictureGroupData(
                        Integer.parseInt(rs.getString(1).toString()),
                        Integer.parseInt(rs.getString(2).toString())
                    )
                )
            }
        }
        runBlocking { job.join() }  //Program will wait until job is done

        return toReturn
    }

}
