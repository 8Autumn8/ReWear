package com.example.rewear.groups

import com.example.rewear.objects.GroupsData
import com.example.rewear.objects.LikedData
import com.example.rewear.objects.PictureData
import java.util.*


// this has informal minh notes i need this for my sanity
interface GroupsContract {
    // talks to the xml files; the frontend
    interface View {
    }
    // talks to the database
    interface Presenter {
        fun getPictures(group_id: Int) : List<PictureData>?
        fun getGroups(user_id: Int): List<GroupsData>?
        fun getUserLiked(user_id: Int, group_id: Int) : List<LikedData>?

    }
}