package com.FlowsDemo.apiusingFlow

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/comments/{id}")
    suspend fun getComents(@Path("id") id: Int): DataModel
}