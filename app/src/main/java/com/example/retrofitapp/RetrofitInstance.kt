package com.example.retrofitapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://zenquotes.io/api/"

    private fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val alintiSozlerApi : AlintiSozlerApi = getInstance().create(AlintiSozlerApi::class.java)

}