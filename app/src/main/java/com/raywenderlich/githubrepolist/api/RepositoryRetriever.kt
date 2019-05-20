package com.raywenderlich.githubrepolist.api

import android.util.Log
import com.raywenderlich.githubrepolist.data.RepoResult
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryRetriever {
    private val service: GithubService

    companion object {
        const val BASE_URL = "https://api.github.com/"  //1
    }

    init {
        // 2
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) //1
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(GithubService::class.java)
    }

    fun getSearch(callback: Callback<RepoResult>) {
        val call = service.searchRepositories()
        call.enqueue(callback)
    }

    fun getRepositories(callback: Callback<RepoResult>) {
        val call = service.retrieveRepositories()
        call.enqueue(callback)
        Log.e("RepoReetriver", "REAUEST: " + call.request().url().toString())


    }
}
