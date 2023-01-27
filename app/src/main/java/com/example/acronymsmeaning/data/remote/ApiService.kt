package com.example.acronymsmeaning.data.remote

import com.example.acronymsmeaning.data.remote.models.AcronymResponse
import com.example.acronymsmeaning.data.remote.models.AcronymResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("dictionary.py")
    suspend fun getAcronymDetails(@Query("sf") typedText: String): Response<List<AcronymResponseItem?>>
}