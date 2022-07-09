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
                var sql: String? = "SELECT * FROM User WHERE Username = '" + userNameInput + "'"
                rs = st.executeQuery(sql)
                if (rs.next()){
                    println(rs.getString(4).toString())
                    user = UserData(Integer.parseInt(rs.getString(1).toString()),
                        rs.getString(2).toString(),
                        rs.getString(3).toString(),
                        rs.getString(4).toString(),
                        rs.getString(5).toString())
                    println("testingggggg")


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
                var rs: ResultSet? = null
                val st: Statement = conn!!.createStatement()
                rs = st.executeQuery("INSERT INTO User(F_name,L_name,Username,Password) VALUES (1,'Belinda','Vela', 'userNumOne', 'testpassword!');")


            }

        }
        runBlocking {
            job.join()
        }  //Program will wait until job is done

    }

    override fun updateUser() {
        TODO("Not yet implemented")
    }

    override fun deleteUser() {
        TODO("Not yet implemented")
    }


}