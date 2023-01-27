package com.example.acronymsmeaning.data

import com.example.acronymsmeaning.data.remote.ApiService
import com.example.acronymsmeaning.data.remote.models.AcronymResponse
import com.example.acronymsmeaning.data.remote.models.AcronymResponseItem
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAcronymDetails(typedText: String): Response<List<AcronymResponseItem?>> {
        return apiService.getAcronymDetails(typedText)
    }
}