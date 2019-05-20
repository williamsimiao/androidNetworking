package com.raywenderlich.githubrepolist.api

import com.raywenderlich.githubrepolist.data.RepoResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GithubService {

    @GET("/search/repositories?")
    fun retrieveRepositories(@QueryMap(encoded = true) query: Map<String, String>): Call<RepoResult>


//    fun retrieveRepositories(@Query("q", encoded=true) keyword : String,
//                             @Query("sort") sorting: String,
//                             @Query("order") ordering: String): Call<RepoResult>

    @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc")
    fun searchRepositories(): Call<RepoResult>
}
