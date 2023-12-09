package com.nimesh.rabbit.data

import retrofit2.http.GET

interface RabbitsApi {
    @GET("/random-rabbit")
    suspend fun getRandomRabbit(): Rabbit

    companion object {
        const val BASE_URL = "http://172.20.10.4:8080"
    }
}