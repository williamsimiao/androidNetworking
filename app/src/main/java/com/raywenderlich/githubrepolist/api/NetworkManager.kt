package com.raywenderlich.githubrepolist.api

import com.raywenderlich.githubrepolist.data.ResponseBody1
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class NetworkManager {
    private val sessaoRouter: sessaoEndPoint

    companion object {
        const val BASE_URL = "https://hsmlab63.dinamonetworks.com/api/"  //1
    }

    init {
        // 2
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) //1
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        sessaoRouter = retrofit.create(sessaoEndPoint::class.java)
    }

//    fun getSearch(callback: Callback<RepoResult>) {
//        val call = service.searchRepositories()
//        call.enqueue(callback)
//    }
//
//    fun getRepositories(umaPalavra: String, callback: Callback<RepoResult>) {
//        val data = hashMapOf("q" to umaPalavra)
//        data.put("sort", "stars")
//        data.put("order", "desc")
//
//        val call = service.retrieveRepositories(data)
//        Log.e("RepoRetriver", "REQUEST: " + call.request().url().toString())
//        call.enqueue(callback)
//    }

    fun runAuth(usr: String, pwd: String, otp: String, callback: Callback<ResponseBody1>) {
        val json = JSONObject()
        json.put("usr", usr)
        json.put("pwd", pwd)

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), json.toString())
        val call = sessaoRouter.auth(requestBody)
        call.enqueue(callback)
    }
}
