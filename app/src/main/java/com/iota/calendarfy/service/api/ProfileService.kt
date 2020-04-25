package com.iota.calendarfy.service.api

import com.iota.calendarfy.model.Event
import com.iota.calendarfy.model.ResponseObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by Adithya S. on 04/01/2020
 */

interface ProfileService {

    companion object {
        const val BASE_PATH = "/api/profile"
    }

    @POST("$BASE_PATH/updateToken")
    suspend fun updateToken(
        @Header("profileEmail") profileEmail: String,
        @Header("deviceToken") deviceToken: String
    ): ResponseObject

    @GET("$BASE_PATH/getEvents")
    suspend fun getEvents(
        @Header("profileEmail") profileEmail: String
    ): ArrayList<Event>

    @POST("$BASE_PATH/addEvent")
    suspend fun addEvent(
        @Header("profileEmail") profileEmail: String,
        @Header("requestingUser") requestingUser: String,
        @Header("eventTitle") eventTitle: String,
        @Header("eventDesc") eventDesc: String
    ): ResponseObject

    @POST("$BASE_PATH/removeEvent")
    suspend fun removeEvent(
        @Header("profileEmail") profileEmail: String,
        @Header("requestingUser") requestingUser: String,
        @Header("eventTitle") eventTitle: String,
        @Header("eventDesc") eventDesc: String
    ): ResponseObject
}