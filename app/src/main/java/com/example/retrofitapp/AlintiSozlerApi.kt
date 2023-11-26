package com.example.retrofitapp

import retrofit2.Response
import retrofit2.http.GET

interface AlintiSozlerApi {

    @GET("random")
    suspend fun getRandomSoz() : Response<List<AlintiSozlerModel>>

}