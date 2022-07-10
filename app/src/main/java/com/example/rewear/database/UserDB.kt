package com.example.rewear.database

import com.example.rewear.objects.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.*

class UserDB : UserInterface, GenerateConnection(){
    private var conn: Connection? = null
    private var username: String = "belinda"
    private val password: String = "!adminpassword1"
    var res = ""

    override fun getUser(userNameInput: String): UserData? {
        var user: UserData? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            //Get Data
            if (conn != null){
                var rs: ResultSet? = null
                val st: Statement = conn!!.createStatement()
                var sql: String? = "SELECT * " +
                        "FROM User " +
                        "WHERE Username = '" + userNameInput + "'"
                rs = st.executeQuery(sql)
                if (rs.next()){
                    user = UserData(Integer.parseInt(rs.getString(1).toString()),
                        rs.getString(2).toString(),
                        rs.getString(3).toString(),
                        rs.getString(4).toString(),
                        rs.getString(5).toString())
                }
            }

        }
        runBlocking {
            job.join()
        }  //Program will wait until job is done

        return user
    }

    override fun addUser(user:UserData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            //Get Data
            if (conn != null){
                val st: Statement = conn!!.createStatement()
                st.executeQuery("INSERT INTO User(F_name,L_name,Username,Password) " +
                        "VALUES ('UserTwoF_NAME','UserTwoL_NAME', 'userNumTwo', 'testpassword!');")

            }

        }
        runBlocking {
            job.join()
        }  //Program will wait until job is done

    }

    override fun updateUser(user:UserData) {

    }

    override fun deleteUser(user:UserData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            //Get Data
            if (conn != null){
                var rs: ResultSet? = null
                val st: Statement = conn!!.createStatement()
                var sql: String? = "DELETE * " +
                        "FROM User " +
                        "WHERE Username = '" + user.username + "'"
                st.executeQuery(sql)
            }

        }
        runBlocking {
            job.join()
        }  //Program will wait until job is done
    }


}