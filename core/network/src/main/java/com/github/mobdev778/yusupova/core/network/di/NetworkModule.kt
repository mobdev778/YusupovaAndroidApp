package com.github.mobdev778.yusupova.core.network.di

import android.util.Log
import com.github.mobdev778.yusupova.core.domain.AppLocale
import com.github.mobdev778.yusupova.core.domain.ServerAddress
import com.github.mobdev778.yusupova.core.network.interceptors.PathInterceptor
import com.github.mobdev778.yusupova.core.network.moshi.UUIDAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
internal interface NetworkModule {

    companion object {

        @JvmStatic
        @Provides
        fun providesLoggingInterceptor(): HttpLoggingInterceptor {
            val logger = HttpLoggingInterceptor.Logger {
                    message -> Log.d("Http", message)
            }
            val interceptor = HttpLoggingInterceptor(logger)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        @JvmStatic
        @Provides
        fun providesPathInterceptor(
            appLocale: AppLocale,
            serverAddress: ServerAddress
        ): PathInterceptor {
            return PathInterceptor(appLocale, serverAddress)
        }

        @JvmStatic
        @Provides
        fun providesOkHttp(
            pathInterceptor: PathInterceptor,
            loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(pathInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @JvmStatic
        @Provides
        fun providesMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(UUIDAdapter)
                .build()
        }

        @JvmStatic
        @Provides
        fun providesRetrofit(
            serverAddress: ServerAddress,
            moshi: Moshi,
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(serverAddress.address)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
        }
    }
}
