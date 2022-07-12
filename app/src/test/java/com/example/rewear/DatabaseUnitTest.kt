package com.example.rewear

import com.example.rewear.database.UserDB
import com.example.rewear.objects.UserData
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DatabaseUnitTest {
    @Test
    fun canAddAndGet_user() {
        val userDB = UserDB()
        val testUser = UserData(null, "first_name", "last_name", "username", "password")
        val databaseUser: UserData?
        // add user to database
        userDB.addUser(testUser)

        // retrieve user from database and check if it's equal to the one we added
        databaseUser = userDB.getUser(testUser.username!!)
        assertEquals(testUser, databaseUser)
    }
}