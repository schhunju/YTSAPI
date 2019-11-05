package com.example.api

import retrofit2.Call
import retrofit2.http.GET

public interface JsonPlaceholder {

    @GET("posts")

    fun gMovies():Call<List<IdClass>>

}