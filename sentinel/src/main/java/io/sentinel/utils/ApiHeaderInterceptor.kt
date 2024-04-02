package io.sentinel.utils

import io.sentinel.utils.Constants.ACCEPT
import io.sentinel.utils.Constants.APPLICATION_JSON
import io.sentinel.utils.Constants.CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Response

class ApiHeaderInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestBuilder = request.newBuilder()
            .addHeader(CONTENT_TYPE, APPLICATION_JSON)
            .addHeader(ACCEPT, "*/*")
        request = requestBuilder.build()

        return chain.proceed(request)
    }
}
