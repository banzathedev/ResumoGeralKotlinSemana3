package com.proway.resumogeral.model

import com.google.gson.annotations.SerializedName

data class Credential(
    @SerializedName("username")
    val email: String,
    @SerializedName("password")
    val password: String
) {
    fun checkUserName(): Boolean {
        return email.isNotEmpty()
    }
    fun checkPassword(): Boolean{
        return password.isNotEmpty()
    }
}
