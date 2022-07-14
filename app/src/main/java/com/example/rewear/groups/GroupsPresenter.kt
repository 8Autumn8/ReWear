package com.example.rewear.groups


import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.GroupsData
import com.example.rewear.objects.LikedData
import com.example.rewear.objects.PictureData
import com.example.rewear.objects.UserData

class GroupsPresenter (
    private val view: GroupsContract.View,
    private val db: DataBaseHelper = DataBaseHelper()
        ) :GroupsContract.Presenter {

    override fun getPictures(group_id: Int) : List<PictureData>?{
        val compareUser: List<PictureData>? = db.getPictureByGroup(group_id)
        return compareUser
    }

    override fun getGroups(user_id: Int): List<GroupsData>?{
        val groups: List<GroupsData>? = db.getGroupsByUser(user_id)
        return groups
    }

    override fun getUserLiked(user_id: Int, group_id: Int) : List<LikedData>?{
        return db.getUserLiked(user_id,group_id)
    }

}