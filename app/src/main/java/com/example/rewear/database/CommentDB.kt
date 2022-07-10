package com.example.rewear.database

import com.example.rewear.objects.CommentData

class CommentDB: CommentInterface, GenerateConnection() {
    override fun addComment(comment: CommentData)  {
        TODO("Not yet implemented")
    }

    override fun deleteComment(comment_id: Int){
        TODO("Not yet implemented")
    }

    override fun getComment(picture_id: Int) : List<CommentData>?{
        TODO("Not yet implemented")
    }

    override fun updateComment() {
        TODO("Not yet implemented")
    }
}