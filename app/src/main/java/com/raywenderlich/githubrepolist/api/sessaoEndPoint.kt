package com.raywenderlich.githubrepolist.api

import com.raywenderlich.githubrepolist.data.ResponseBody1
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface sessaoEndPoint {

//    @GET("/search/repositories?")
//    fun retrieveRepositories(@QueryMap(encoded = true) query: Map<String, String>): Call<RepoResult>

//    @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc")
//    fun searchRepositories(): Call<RepoResult>

    @POST("auth")
    @Headers("Content-type:application/json")
    fun auth(@Body request: RequestBody): Call<ResponseBody1>
}
