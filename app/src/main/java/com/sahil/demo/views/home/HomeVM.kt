package com.sahil.demo.views.home

import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.varsha.di.ApiParams
import com.google.gson.Gson
import com.sahil.demo.dataclass.DATA
import com.sahil.demo.views.adapter.SimpleAdapter
import com.sahil.demo.views.utils.userDummyList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(private val apiParams: ApiParams) : ViewModel() {
    val userAdapter by lazy { SimpleAdapter() }
    var responseData: DATA? = null
    init {
//        userAdapter.addItems(userDummyList)
        callApi()
    }



    fun callApi() = viewModelScope.launch {
        flow {
            emit(apiParams.getEmployeesData())
        }.catch { error ->
            // Handle any error that might occur during the API call
            Log.e("TAG", "API Error: ${error.message}")
        }.collect { response ->
            if (response.isSuccessful) {
                // Convert JSON response to your DATA data class using Gson
                val responseBody = response.body()
                if (responseBody != null) {
                    val apiResponse = Gson().fromJson(responseBody.toString(), DATA::class.java)
                    responseData = apiResponse // Save the response to the responseData variable
                    Log.e("TAG", "callApi: $apiResponse ")
                userAdapter.addItems(apiResponse.data ?: emptyList())
                }
            } else {
                // Handle the case where the API response is not successful
                Log.e("TAG", "API Response not successful. Code: ${response.code()}")
            }
        }
    }



    data class UserDataDC(
        var userName: String? = null
    )


}