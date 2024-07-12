package com.example.varsha.di

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.sahil.demo.dataclass.DATA
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://dummy.restapiexample.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiParams =
        retrofit.create(ApiParams::class.java)


}


interface ApiParams {

    @GET("employees")
    suspend fun getEmployeesData(
    ): Response<JsonElement>

}
//https://dummy.restapiexample.com/api/v1/employees