package com.example.rewear.database

import com.example.rewear.objects.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class UserDB : UserInterface, GenerateConnection(){
    private var conn: Connection? = null

    /**
     * Gets the username from the database
     * @param usernameInput The given username
     * @return the `UserData` of the given user, `null` if user was not found.
     */
    override fun getUser(usernameInput: String): UserData? {
        var user: UserData? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            //Get Data
            if (conn != null){
                val rs: ResultSet?
                val st: Statement = conn!!.createStatement()
                rs = st.executeQuery("SELECT * " +
                                     "FROM User " +
                                     "WHERE username = '$usernameInput'"
                )

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

    /**
     * Adds a user into the database
     * @param user The user to add
     */
    override fun addUser(user:UserData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            //Get Data
            if (conn != null){
                val st: Statement = conn!!.createStatement()
                st.execute("INSERT INTO User(first_name,last_name,username,password) VALUES ('${user.firstName}','${user.lastName}', '${user.username}', '${user.password}!');")
            }
        }
        runBlocking {
            job.join()
        }

    }

    /**
     * Updates the user in the database with its new info
     * @param updatedUser The updated user details
     */
    override fun updateUser(updatedUser:UserData) {
        /*
            UPDATE Customers
            SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
            WHERE CustomerID = 1;
         */
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection()
            if (conn != null) {

                val st: Statement = conn!!.createStatement()
                st.execute("UPDATE User" +
                           "SET first_name = '${updatedUser.firstName}', last_name = '${updatedUser.lastName}', username = '${updatedUser.username}', password = '${updatedUser.password}'" +
                           "WHERE id = ${updatedUser.ID}"
                )
            }
        }
        runBlocking { job.join() }
        TODO("Not yet implemented")
    }

    override fun deleteUser() {
        TODO("Not yet implemented")
    }
}