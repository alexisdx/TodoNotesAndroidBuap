package com.example.roeeyn.mynotesapp

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppApiHelper {

    private val apiHelper by lazy { ApiHelper.create() }

    fun createUser(newUser: Models.CreateUserModel)
            :Single<Models.SuccessModel> {

        return apiHelper.createUser(newUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())

    }

    fun login(loginModel: Models.LoginModel):Single<Models.SuccessModel>{

        return apiHelper.login(loginModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())


    }

    fun createNote(nuevaNotaModel: Models.NuevaNotaModel, idUser:String)
            :Single<Models.Usuario>{

        return apiHelper.createNote(nuevaNotaModel, idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())

    }

    fun getNotes(userId:String):Single<List<Models.NuevaNotaModel>>{

        return apiHelper.getNotes(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())

    }

}