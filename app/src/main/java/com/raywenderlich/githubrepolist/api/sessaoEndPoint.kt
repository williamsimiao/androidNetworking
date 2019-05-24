package com.raywenderlich.githubrepolist.api

import com.raywenderlich.githubrepolist.data.ResponseBody1
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface sessaoEndPoint {

    @POST("close")
    @Headers("Content-type:application/json", "Authorization:{token}")
    fun close(@Header("token") token: String?): Call<ResponseBody1>

    @POST("auth")
    @Headers("Content-type:application/json")
    fun auth(@Body request: RequestBody): Call<ResponseBody1>
}
