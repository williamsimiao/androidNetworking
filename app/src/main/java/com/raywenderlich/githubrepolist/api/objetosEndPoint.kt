package com.raywenderlich.githubrepolist.api

import com.raywenderlich.githubrepolist.data.ResponseBody1
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface obejetosEndPoint {


    @GET("list_objs")
    @Headers("Content-type:application/json", "Authorization:{token}")
    fun listObjs(@Header("token") token: String?): Call<ResponseBody1>
}
