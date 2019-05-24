package com.raywenderlich.githubrepolist.ui.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.raywenderlich.githubrepolist.R
import com.raywenderlich.githubrepolist.api.NetworkManager
import com.raywenderlich.githubrepolist.data.ResponseBody1
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.second_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : Activity() {
    private val networkManager = NetworkManager() // 1
    private var tokenString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        tokenString = intent.getStringExtra("TOKEN")

        listObjsButton.setOnClickListener {
            val context = baseContext
            val callback = object : Callback<ResponseBody1> {
                override fun onFailure(call: Call<ResponseBody1>?, t: Throwable?) {
                    Log.e("MainActivity", "Problem calling the API", t)
                }

                override fun onResponse(call: Call<ResponseBody1>?, response: Response<ResponseBody1>?) {
                    response?.isSuccessful.let {
                        tokenString = "HSM " + response?.body()?.token
                        Log.e("SecondActivity", "Deu certo "+tokenString)
                        val intent = Intent(context, ObjetosListActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            networkManager.runAuth(usrEditText.text.toString(), pwdEditText.text.toString(), "", callback)
        }
        closeButton.setOnClickListener {
            val context = baseContext
            val callbackClose = object : Callback<ResponseBody1> {
                override fun onFailure(call: Call<ResponseBody1>?, t: Throwable?) {
                    Log.e("SecondActivity", "Problem calling the API", t)
                }

                override fun onResponse(call: Call<ResponseBody1>?, response: Response<ResponseBody1>?) {
                    response?.isSuccessful.let {

                        AlertDialog.Builder(context).setTitle("Sessão encerrada")
                                .setMessage("Sessão encerrada com sucesso")
                                .setPositiveButton(android.R.string.ok) { dialogInterface, i ->
                                    
                                }

                        tokenString = "HSM " + response?.body()?.token
                        Log.e("SecondActivity", "Deu certo "+tokenString)
                        val intent = Intent(context, SecondActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            networkManager.runClose(tokenString!!, callbackClose)
        }
    }
}
