package com.raywenderlich.githubrepolist.api

import com.raywenderlich.githubrepolist.data.RepoResult
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("/search/repositories?q=mario+language:kotlin&sort=stars&order=desc")
    fun retrieveRepositories(): Call<RepoResult>

    @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc") //sample search
    fun searchRepositories(): Call<RepoResult>
}
