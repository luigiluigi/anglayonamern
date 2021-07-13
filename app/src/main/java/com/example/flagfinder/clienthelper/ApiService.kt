package com.example.flagfinder.clienthelper

import com.example.flagfinder.model.FlagResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    /*Get List Records*/

    @GET("rest/v2/all")
    fun getRecords(): Call<ArrayList<FlagResponseItem>>

}