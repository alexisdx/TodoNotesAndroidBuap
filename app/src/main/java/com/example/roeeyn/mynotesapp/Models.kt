package com.example.roeeyn.mynotesapp

import com.google.gson.annotations.SerializedName

object Models {

    data class CreateUserModel(val user:String, val pswd:String)
    data class LoginModel(val user:String, val pswd:String)
    data class SuccessModel(val success:String)
}