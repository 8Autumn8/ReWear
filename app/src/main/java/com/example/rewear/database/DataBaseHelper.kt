package com.example.rewear.database

import android.graphics.Picture
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.security.acl.Group
import java.sql.*


class DataBaseHelper(
    belongsToDB: BelongsToDB = BelongsToDB(),
    clothesDB: ClothesDB = ClothesDB(),
    commentDB: CommentDB = CommentDB(),
    dateWornDB: DateWornDB = DateWornDB(),
    groupDB: GroupDB = GroupDB(),
    likedDB: LikedDB = LikedDB(),
    pictureDB: PictureDB = PictureDB(),
    pictureGroupDB: PictureGroupDB = PictureGroupDB(),
    userDB: UserDB = UserDB()
) :
    BelongsToInterface by belongsToDB,
    ClothesInterface by clothesDB,
    CommentInterface by commentDB,
    DateWornInterface by dateWornDB,
    GroupInterface by groupDB,
    LikedInterface by likedDB,
    PictureInterface by pictureDB,
    PictureGroupInterface by pictureGroupDB,
    UserInterface by userDB,
    AppCompatActivity(){

}
