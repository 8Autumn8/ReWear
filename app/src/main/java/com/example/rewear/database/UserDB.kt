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
            conn = createConnection() ?: return@launch
            val rs: ResultSet?
            val st: Statement = conn!!.createStatement()
            rs = st.executeQuery("SELECT * " +
                                 "FROM User " +
                                 "WHERE username = '${usernameInput}'"
            )

            if (rs.next()) {
                user = UserData(
                    Integer.parseInt(rs.getString(1).toString()),
                    rs.getString(2).toString(),
                    rs.getString(3).toString(),
                    rs.getString(4).toString(),
                    rs.getString(5).toString()
                )
            }
        }
        runBlocking { job.join() }  //Program will wait until job is done

        return user
    }

    /**
     * Adds a user into the database
     * @param user The user to add
     */
    override fun addUser(user:UserData) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            val str = "INSERT INTO User(first_name,last_name,username,password) " +
            "VALUES ('${user.first_name}','${user.last_name}', '${user.username}', '${user.password}');"
            st.executeUpdate(str)
        }
        runBlocking { job.join() }
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
            conn = createConnection() ?: return@launch

            val st: Statement = conn!!.createStatement()
            st.execute("UPDATE User" +
                       "SET first_name = '${updatedUser.first_name}', last_name = '${updatedUser.last_name}', username = '${updatedUser.username}', password = '${updatedUser.password}' " +
                       "WHERE user_id = ${updatedUser.user_id};"
            )
        }
        runBlocking { job.join() }
    }

    override fun deleteUser(user_id: Int) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            conn = createConnection() ?: return@launch
            val st: Statement = conn!!.createStatement()
            st.execute("DELETE FROM User " +
                    "WHERE user_id = ${user_id};"
            )
        }

        runBlocking { job.join() }
    }
}