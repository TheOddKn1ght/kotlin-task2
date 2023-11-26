package com.example.mypin

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface MemesApi {

    @GET
    @Headers(
        "X-RapidAPI-Key: caf4f4e607msh6aa04bc38d82b7bp1fa8b6jsn4f66304dc7ac",
        "X-RapidAPI-Host: programming-memes-images.p.rapidapi.com"
    )
    fun getMemes(@Url url: String): Response<ImageItem>

}
