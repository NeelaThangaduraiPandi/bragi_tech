package com.movies.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class RetryInterceptor(private val maxRetries: Int) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var attempt = 0
        val request: Request = chain.request()
        var response: Response? = null
        var exception: IOException? = null

        while (attempt < maxRetries) {
            try {
                response = chain.proceed(request)
                if (response.isSuccessful) {
                    return response
                }
            } catch (e: IOException) {
                exception = e
            }

            attempt++
            response?.close()
        }

        return response ?: throw exception ?: IOException("Unknown network error")
    }
}