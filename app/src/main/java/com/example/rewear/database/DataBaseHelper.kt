package com.example.rewear.database

import androidx.appcompat.app.AppCompatActivity


class DataBaseHelper(
    belongsToDB: UserBelongsToDB = UserBelongsToDB(),
    clothesDB: ClothesDB = ClothesDB(),
    commentDB: CommentDB = CommentDB(),
    dateWornDB: DateWornDB = DateWornDB(),
    groupDB: GroupDB = GroupDB(),
    likedDB: LikedDB = LikedDB(),
    pictureDB: PictureDB = PictureDB(),
    pictureGroupDB: PictureGroupDB = PictureGroupDB(),
    userDB: UserDB = UserDB(),
    clothesCategory: ClothesCategoryDB = ClothesCategoryDB()
) :
    UserBelongsToInterface by belongsToDB,
    ClothesInterface by clothesDB,
    CommentInterface by commentDB,
    DateWornInterface by dateWornDB,
    GroupInterface by groupDB,
    LikedInterface by likedDB,
    PictureInterface by pictureDB,
    PictureGroupInterface by pictureGroupDB,
    UserInterface by userDB,
    ClothesCategoryInterface by clothesCategory,
    AppCompatActivity(){

}
