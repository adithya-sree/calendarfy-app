package com.iota.calendarfy.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Adithya S. on 04/02/2020
 */

class ResponseObject {
    @SerializedName("success")
    @Expose
    val success: Boolean? = null

    @SerializedName("eventDesc")
    @Expose
    val eventDesc: String? = null
}