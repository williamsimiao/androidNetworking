package com.raywenderlich.githubrepolist.data

data class ResponseBody1 (
        val token: String,
        val cid: String,
        val pwd_expired: String)

data class ResponseBody2 (
    val obj: List<String>)