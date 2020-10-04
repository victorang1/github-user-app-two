package com.example.dicodingsubmissiontwo.service

class ApiConfig {

    companion object {
        const val BASE_URL = "https://api.github.com"
        const val REQUEST_ERROR = 500
        const val REQUEST_SUCCESS = 200
    }

    interface ApiHandler<T> {
        fun onSuccess(response: T)
        fun onFailure(throwable: Throwable)
    }

}