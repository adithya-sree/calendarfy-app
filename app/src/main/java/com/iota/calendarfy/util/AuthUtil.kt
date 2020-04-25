package com.iota.calendarfy.util

import android.util.Base64

/**
 * Created by Adithya S. on 04/02/2020
 */
object AuthUtil {
    private const val USERNAME = "acbd885d-318f-4aa0-93ec-82946f681dc9"
    private const val PASSWORD = "6275c988-c99f-4e99-9db1-3340907bebe5"

    fun getHeader(): String{
        return "Basic ${basicAuthEncrypt()}".trim()
    }

    private fun basicAuthEncrypt(): String {
        val authString = "$USERNAME:$PASSWORD"
        val authEncBytes =
            Base64.encode(authString.toByteArray(), Base64.NO_WRAP)
        return String(authEncBytes).trim()
    }
}