package com.example.rewear.groups

import com.example.rewear.objects.PictureData
import java.util.*


// this has informal minh notes i need this for my sanity
interface GroupsContract {
    // talks to the xml files; the frontend
    interface View {
        fun onRecieveData(pictures: List<PictureData>?)
    }
    // talks to the database
    interface Presenter {

    }
}