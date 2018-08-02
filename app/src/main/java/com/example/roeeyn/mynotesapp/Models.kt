package com.example.roeeyn.mynotesapp

import com.google.gson.annotations.SerializedName

object Models {

    data class CreateUserModel(val user:String, val pswd:String)
    data class LoginModel(val user:String, val pswd:String)
    data class SuccessModel(val success:String)
    data class NuevaNotaModel(val title:String, val description:String
                              , val date:String)

    data class Usuario(
            val _id:String,
            val user:String,
            val pswd:String,
            val notas:List<NuevaNotaModel>,
            val __v:Int
    )
}