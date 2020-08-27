package com.jarvis.baselibrary.retrofit

import android.os.Build
import com.jarvis.baselibrary.utils.GlobalUtil
import com.jarvis.baselibrary.utils.LogUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import java.util.*

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/21
 */
object RetrofitManager {

    private const val BASE_URL = "http://baobab.kaiyanapp.com/"


    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
//        .addInterceptor()
        .build()


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    inline fun <reified T> create(): T {
        return retrofit.create(T::class.java)
    }

}


//header
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().apply {
            header("paltform", "Android")
            header("If-Modified-Since", "${Date()}")
            header("User-Agent", System.getProperty("http.agent") ?: "unknown")
        }.build()

        return chain.proceed(request)
    }

}


class BasicParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url
        val url = originalHttpUrl.newBuilder().apply {
            addQueryParameter("udid", GlobalUtil.getDeviceSerial())
            addQueryParameter("vc", GlobalUtil.eyepetizerVersionCode.toString())
            addQueryParameter("vn", GlobalUtil.eyepetizerVersionName)
//            addQueryParameter("size", screenPixel())
            addQueryParameter("deviceModel", GlobalUtil.deviceModel)
            addQueryParameter("first_channel", GlobalUtil.deviceBrand)
            addQueryParameter("last_channel", GlobalUtil.deviceBrand)
            addQueryParameter("system_version_code", "${Build.VERSION.SDK_INT}")
        }.build()
        val request = originalRequest.newBuilder().url(url).method(
            originalRequest.method,
            originalRequest.body
        ).build()
        return chain.proceed(request)
    }
}


//日志
class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 = System.nanoTime()
        LogUtil.i("Sending request: ${request.url} \n ${request.headers}")
        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        LogUtil.i(
            "Received response for  ${response.request.url} " + "in ${(t2 - t1) / 1e6} ms\n${response.headers}"
        )
        return response
    }

}