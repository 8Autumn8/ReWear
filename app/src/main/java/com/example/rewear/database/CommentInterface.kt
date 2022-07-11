package com.example.rewear.database
import com.example.rewear.objects.CommentData

interface CommentInterface {
    fun getComments(picture_id: Int) : List<CommentData>?

    fun addComment(comment: CommentData)

    fun deleteComment(comment_id: Int)

    fun updateComment(commentData: CommentData)
}