package com.example.flagfinder.clienthelper

import android.content.Context
import com.example.flagfinder.clienthelper.factory.SslClientFactory
import com.example.flagfinder.clienthelper.intereceptor.IBMInterceptor
import com.example.flagfinder.clienthelper.intereceptor.NetworkInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ClientHelper {

    fun retroBuilder(context: Context):Retrofit {
        val gson = GsonBuilder().serializeNulls().create()
        return Retrofit.Builder()
            .baseUrl("https://restcountries.eu/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createHttpClient(context))
            .build()
    }

    fun createHttpClient(context: Context): OkHttpClient {

        val builder = SslClientFactory.okHttpClientBuilder
            .addInterceptor(NetworkInterceptor(context))
            .addInterceptor(IBMInterceptor())
            .connectTimeout(1000, TimeUnit.SECONDS)
            .readTimeout(1000, TimeUnit.SECONDS)
            .writeTimeout(1000, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
        return builder.build()
    }
}