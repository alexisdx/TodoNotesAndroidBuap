package com.example.roeeyn.mynotesapp

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiHelper {

    @POST("/user")
    fun createUser(@Body newUser: Models.CreateUserModel):Single<Models.SuccessModel>

    @POST("/login")
    fun login(@Body loginModel: Models.LoginModel):Single<Models.SuccessModel>

    @POST("/notas/{id}")
    fun createNote(@Body nuevaNotaModel: Models.NuevaNotaModel,
                   @Path("id") idUser:String):Single<Models.Usuario>

    @GET("/notas/{id}")
    fun getNotes(@Path("id") userId:String ):Single<List<Models.NuevaNotaModel>>

    companion object {
        fun create(): ApiHelper {

            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor {
                        val original = it.request()
                        val requestBuilder = original.newBuilder()
                                .method(original.method(), original.body())
                        val request = requestBuilder.build()
                        it.proceed(request)
                    }
                    .build()

            val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://backend-buap.herokuapp.com")
                    .build()

            return retrofit.create(ApiHelper::class.java)

        }
    }

}