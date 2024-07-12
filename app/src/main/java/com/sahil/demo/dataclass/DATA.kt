package com.sahil.demo.dataclass


import com.google.gson.annotations.SerializedName

data class DATA(
    @SerializedName("data")
    val data: List<Data>? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: String? = null
) {
    data class Data(
        @SerializedName("employee_age")
        val employeeAge: Int? = null,
        @SerializedName("employee_name")
        val employeeName: String? = null,
        @SerializedName("employee_salary")
        val employeeSalary: Int? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("profile_image")
        val profileImage: String? = null
    )
}