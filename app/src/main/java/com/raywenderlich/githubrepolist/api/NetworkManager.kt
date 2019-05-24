package com.raywenderlich.githubrepolist.api

import android.util.Log
import com.raywenderlich.githubrepolist.data.ResponseBody1
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

class UnsafeOkHttpClient {
    companion object {
        fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
                    }
                })

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory

                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier { _, _ -> true }

                return builder
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}


class NetworkManager {
    private val sessaoRouter: sessaoEndPoint

    companion object {
        const val BASE_URL = "https://hsmlab63.dinamonetworks.com/api/"
    }

    init {
        val unsafeClient = UnsafeOkHttpClient.getUnsafeOkHttpClient().build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(unsafeClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        sessaoRouter = retrofit.create(sessaoEndPoint::class.java)
    }

    fun runClose(token: String, callback: Callback<ResponseBody1>) {
        val json = JSONObject()
        Log.e("JSON", json.toString())

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), json.toString())
        val call = sessaoRouter.close(token)
        call.enqueue(callback)
    }

    fun runAuth(usr: String, pwd: String, otp: String, callback: Callback<ResponseBody1>) {
        val json = JSONObject()
        json.put("usr", usr)
        json.put("pwd", pwd)

        Log.e("JSON", json.toString())

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), json.toString())
        val call = sessaoRouter.auth(requestBody)
        call.enqueue(callback)
    }
}
