package com.example.rewear.database

import com.example.rewear.objects.PictureData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Blob
import java.sql.ResultSet
import java.sql.Statement
import com.example.rewear.Utility
import java.util.*

class PictureDB: PictureInterface, GenerateConnection() {
    val utility = Utility()
    override fun addPicture(pictureData: PictureData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            //getData
            val st: Statement = conn!!.createStatement()
            st.execute("INSERT INTO PictureGroupData(picture_id,user_id,picture,) " +
                    "VALUES (${pictureData.picture_id},${pictureData.user_id}, ${pictureData.picture}, ${pictureData.caption}, ${pictureData.date_posted}  ")

        }
        runBlocking { job.join() }
    }

    override fun deletePicture(picture_id: Int) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.execute("DELETE FROM PictureGroup " +
                    "WHERE picture_id = ${picture_id};"
            )
        }

        runBlocking { job.join() }
    }

    override fun getPicture(picture_id: Int) : PictureData? {
        var toReturn: PictureData? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn!!.createStatement()
            rs = st.executeQuery("SELECT * " +
                    "FROM PictureGroup " +
                    "WHERE group_id = '$picture_id'; "
            )

            if (rs.next()) {
                //(assuming you have a ResultSet named RS)
                //(assuming you have a ResultSet named RS)



                toReturn = PictureData(Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString()),
                    utility.convert(rs.getBlob(3)),
                    rs.getString(4).toString(),
                    rs.getString(5).toString())
            }
        }
        runBlocking { job.join() }  //Program will wait until job is done

        return toReturn
    }

    override fun getPictureByGroup(group_id:Int) : List<PictureData>?{
        var toReturn: MutableList<PictureData>? = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn!!.createStatement()
            rs = st.executeQuery("SELECT Picture.*, User.username " +
                    "FROM Picture, PictureGroup, User " +
                    "WHERE Picture.Picture_id = PictureGroup.picture_id " +
                    "AND Picture.user_id = User.user_id " +
                    "AND PictureGroup.group_id = ${group_id} " +
                    "ORDER BY PictureGroup.picture_id DESC; "
            )

            while (rs != null && rs.next()) {
//utility.convert(rs.getBlob(3)),
                toReturn!!.add(PictureData(Integer.parseInt(rs.getString(1).toString()),
                    Integer.parseInt(rs.getString(2).toString()),
                    null,
                    rs.getString(4).toString(),
                    rs.getString(5).toString(),
                    rs.getString(6).toString()))
            }
        }
        runBlocking { job.join() }  //Program will wait until job is done

        return toReturn
    }

    override fun updatePicture(pictureData: PictureData) {
        TODO("Not yet implemented")
    }
}