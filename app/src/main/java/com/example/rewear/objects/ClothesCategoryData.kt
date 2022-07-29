package com.example.rewear.objects

data class ClothesCategoryData(val category_id: Int?,
                               val user_id: Int?,
                               val name: String?,
                                val description: String?) : java.io.Serializable{
    constructor(user_id: Int?):  this(null,user_id, "All", "Contains all clothes from all categories")
}