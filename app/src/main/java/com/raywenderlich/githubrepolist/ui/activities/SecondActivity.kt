package com.raywenderlich.githubrepolist.ui.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.raywenderlich.githubrepolist.R
import kotlinx.android.synthetic.main.second_activity.*

class SecondActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        listObjsButton.setOnClickListener {
            Log.e("SecondActivity", "Apertou list")
        }
        closeButton.setOnClickListener {
            Log.e("SecondActivity", "Apertou close")
        }
    }
}
