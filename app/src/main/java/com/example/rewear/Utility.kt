package com.example.rewear

import java.sql.Blob
import java.text.SimpleDateFormat
import java.util.*

class Utility {

    fun parseDate(date: String): String {
        return SimpleDateFormat("MM/dd/yyyy", Locale.US).format(date)
    }

    fun convert(blob: Blob): ByteArray {
        val bytes = blob.getBytes(1L, blob.length().toInt())
        blob.free()
        return bytes
    }

}