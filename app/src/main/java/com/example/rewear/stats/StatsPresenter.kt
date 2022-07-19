package com.example.rewear.stats

import com.example.rewear.database.DataBaseHelper
import com.example.rewear.database.GenerateConnection
import com.example.rewear.objects.ClothesData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.ResultSet

class StatsPresenter (
    private val view: StatsContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) :StatsContract.Presenter {

    override fun getMostWorn(userID: Int): ClothesData? {
        var mostWorn: ClothesData? = null

        val SQL_request = "SELECT Clothes.*, IFNULL(ClothesBelongsTo.category_id,0) Category_id, IFNULL(Name,\"\"), IFNULL(TimesWorn,0),IFNULL(LastWorn,\"\")\n" +
                          "FROM Clothes \n" +
                          "LEFT JOIN ClothesBelongsTo ON Clothes.clothes_id = ClothesBelongsTo.clothes_id LEFT JOIN clothescategory ON ClothesBelongsTo.category_id = clothescategory.category_id \n" +
                          "LEFT JOIN ( SELECT clothes_id, COUNT(*) AS TimesWorn, MAX( date ) AS LastWorn FROM dateworn GROUP BY clothes_id ) AS tblDateWorn ON Clothes.clothes_id = tblDateWorn.clothes_id \n" +
                          "WHERE Clothes.user_id = $userID\n" +
                          "ORDER BY TimesWorn DESC\n" +
                          "LIMIT 1"

        val job = CoroutineScope(Dispatchers.IO).launch {
            val connection = GenerateConnection().createConnection() ?: return@launch
            val rs: ResultSet? = connection!!.createStatement().executeQuery(SQL_request)

            if (rs != null && rs.next()) {
                mostWorn = ClothesData(Integer.parseInt(rs.getString(1).toString()),
                rs.getBytes(2),
                rs.getString(3).toString(),
                rs.getString(4).toString())
            }
        }
        runBlocking { job.join() }

        return mostWorn
    }
}