package com.example.rewear.database

import com.example.rewear.objects.GroupsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet
import java.sql.Statement


class GroupDB : GroupInterface, GenerateConnection() {
    override fun getGroup(group_id: Int) : GroupsData? {
        var toReturn: GroupsData? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val rs: ResultSet? = conn!!.createStatement().executeQuery("SELECT * " +
                    "FROM Groups " +
                    "WHERE group_id = " + group_id)

            if (rs != null && rs.next()) {
                toReturn = GroupsData(Integer.parseInt(rs.getString(1).toString()),
                    rs.getString(2).toString(),
                    rs.getString(3).toString(),
                    rs.getString(4).toString()
                )
            }

        }
        runBlocking { job.join() }
        return toReturn
    }


    override fun addGroup(group: GroupsData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            conn!!.createStatement().execute("INSERT INTO ClothesBelongTo " +
                    "VALUES (${group.group_id},${group.group_password}, ${group.group_name}, ${group.group_desc};")
        }
        runBlocking { job.join() }
    }

    override fun deleteGroup(group_id: Int){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val st: java.sql.Statement = conn!!.createStatement()
            st.execute("DELETE FROM User " +
                    "WHERE group_id = ${group_id};"
            )
        }

        runBlocking { job.join() }
    }

    override fun updateGroup(group: GroupsData){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch

            val st: Statement = conn!!.createStatement()
            st.execute("UPDATE User" +
                    "SET group_password = '${group.group_password}', group_name = '${group.group_name}', group_desc = '${group.group_desc}' " +
                    "WHERE group_id = ${group.group_id};"
            )
        }
        runBlocking { job.join() }
    }

    override fun getGroupsByUser(user_id: Int): List<GroupsData>?{
        var toReturn: MutableList<GroupsData>? = mutableListOf()
        val job = CoroutineScope(Dispatchers.IO).launch {
            val conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn!!.createStatement()
            rs = st.executeQuery(" SELECT Groups.* " +
                    "FROM Groups, UserBelongsTo " +
                    "WHERE Groups.group_id = UserBelongsTo.group_id " +
                    "AND user_id = ${user_id}; "
            )

            while (rs != null && rs.next()) {
                toReturn!!.add(
                    GroupsData(Integer.parseInt(rs.getString(1).toString()),
                    rs.getString(2).toString(),
                    rs.getString(3).toString(),
                    rs.getString(4).toString())
                )
            }
        }
        runBlocking { job.join() }  //Program will wait until job is done

        return toReturn
    }


}