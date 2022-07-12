package com.example.rewear

import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.sql.Blob
import java.text.SimpleDateFormat
import java.util.*

class Utility {

    fun parseDate(date: String): String {
        var simpleDateFormat: SimpleDateFormat

        simpleDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        var toReturn = simpleDateFormat.format(date)
        return toReturn
    }

    fun convert(blob: Blob): ByteArray {


        val blobLength = blob.length() as Int
        val blobAsBytes: ByteArray = blob.getBytes(1, blobLength)
        blob.free()
        return blobAsBytes
    }

}