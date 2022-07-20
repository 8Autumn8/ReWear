package com.example.rewear.stats

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.database.GenerateConnection
import com.example.rewear.objects.ClothesData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Date
import java.sql.ResultSet

class StatsPresenter(
    private val view: StatsContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
) : StatsContract.Presenter {

    /**
     * This function grabs the most worn piece of clothing from the given user's closet through
     * executing a SQL statement from the database.
     * @param userID the user's ID used to find the most worn clothing from
     * @return A `ClothesData` of the user's most worn clothing, or `null` if it couldn't be
     * fetched.
     */
    override fun getMostWorn(userID: Int): ClothesData? {
        var mostWorn: ClothesData? = null

        val SQL_request =
            "SELECT Clothes.*, IFNULL(ClothesBelongsTo.category_id,0) Category_id, IFNULL(Name,\"\"), IFNULL(TimesWorn,0),IFNULL(LastWorn,\"\")\n" +
                    "FROM Clothes \n" +
                    "LEFT JOIN ClothesBelongsTo ON Clothes.clothes_id = ClothesBelongsTo.clothes_id LEFT JOIN clothescategory ON ClothesBelongsTo.category_id = clothescategory.category_id \n" +
                    "LEFT JOIN ( SELECT clothes_id, COUNT(*) AS TimesWorn, MAX( date ) AS LastWorn FROM dateworn GROUP BY clothes_id ) AS tblDateWorn ON Clothes.clothes_id = tblDateWorn.clothes_id \n" +
                    "WHERE Clothes.user_id = $userID\n" +
                    "ORDER BY TimesWorn DESC\n" +
                    "LIMIT 1"

        val job = CoroutineScope(Dispatchers.IO).launch {
            val connection = GenerateConnection().createConnection() ?: return@launch
            val rs: ResultSet? = connection.createStatement().executeQuery(SQL_request)

            // guard clause because I like them more.
            if (rs == null || !rs.next()) return@launch

            mostWorn = ClothesData(
                Integer.parseInt(rs.getString(1).toString()),
                rs.getBytes(2),
                rs.getString(3).toString(),
                rs.getString(4).toString()
            )
        }
        runBlocking { job.join() }

        return mostWorn
    }

    override fun getPercentageWornFromDate(userID: Int, date: Date): Int {
        var numClothesInCloset = 0
        var wornSinceGivenDate = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            val connection = GenerateConnection().createConnection() ?: return@launch

            // get number of clothes in closet
            var rs: ResultSet? = connection.createStatement().executeQuery(
                "SELECT COUNT(Clothes.clothes_id) " +
                        "FROM Clothes " +
                        "WHERE clothes.user_id = $userID"
            )
            if (rs == null || !rs.next()) return@launch
            numClothesInCloset = rs.getInt(1)

            while (date <= Date(java.util.Date().time)) {
                rs = connection.createStatement().executeQuery(
                    "SELECT COUNT(dateworn.clothes_id) " +
                            "FROM dateworn " +
                            "WHERE dateworn.date = \"$date\""
                )
                // don't bother continuing if one result = bad.
                if (rs == null || !rs.next()) return@launch
                wornSinceGivenDate += rs.getInt(1)
            }
        }
        runBlocking { job.join() }

        if (numClothesInCloset == 0 || wornSinceGivenDate == 0)
            return 0
        return (wornSinceGivenDate.toFloat() / numClothesInCloset.toFloat() * 100).toInt()
    }
}