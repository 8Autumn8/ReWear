package com.example.rewear.database

import com.example.rewear.objects.ClothesData
import com.example.rewear.objects.DateWorn
import java.util.*

interface DateWornInterface {

    fun getDateWorn(clothes_id: Int) : List<DateWorn>?

    fun addDateWorn(dateWorn: DateWorn)

    fun deleteDateWorn(dateWorn: DateWorn)

    fun getMostWorn(user_id: Int) : ClothesData?

    fun getNumWornSinceDate(user_id: Int, date: Date): Int?

}