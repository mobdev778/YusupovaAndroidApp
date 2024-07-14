package com.github.mobdev778.yusupova.core.network.interceptors

import com.github.mobdev778.yusupova.core.domain.AppLocale
import com.github.mobdev778.yusupova.core.domain.ServerAddress
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class PathInterceptor @Inject constructor(
    private val appLocale: AppLocale,
    private val serverAddress: ServerAddress,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val tail = parseTail(request.url.toString())
        val newUrl = serverAddress.address + appLocale.prefix + "/" + tail

        request = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(request)
    }

    private fun parseTail(url: String): String {
        var i = 0
        while (i < url.length && serverAddress.address[i] == url[i]) i++
        return url.substring(i)
    }
}
