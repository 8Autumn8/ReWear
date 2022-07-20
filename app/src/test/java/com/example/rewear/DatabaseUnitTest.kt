package com.example.rewear

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.UserData
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DatabaseUnitTest {
    private val db: DataBaseHelper = DataBaseHelper()
    @Test
    fun canAddAndGet_user() {
        val testUser = UserData(null, "first_name", "last_name", "username", "password")
        // add user to database
        db.addUser(testUser)

        // retrieve user from database and check if it's equal to the one we added
        val databaseUser: UserData? = db.getUser(testUser.username!!)
        assertNotNull(databaseUser)
    }
}