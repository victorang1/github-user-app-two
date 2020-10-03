package com.example.dicodingsubmissiontwo.service

class ApiConfig {

    companion object {
        val BASE_URL = "https://api.github.com}"
    }

    interface ApiHandler<T> {
        fun onSuccess(response: T)
        fun onFailure(throwable: Throwable)
    }

}