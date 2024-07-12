package com.github.mobdev778.yusupova.core.network.di

import android.util.Log
import com.github.mobdev778.yusupova.core.domain.AppLocale
import com.github.mobdev778.yusupova.core.domain.ServerAddress
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
interface NetworkModule {

    companion object {
        @Provides
        fun providesLoggingInterceptor(): HttpLoggingInterceptor {
            val logger = HttpLoggingInterceptor.Logger {
                    message -> Log.d("Http", message)
            }
            val interceptor = HttpLoggingInterceptor(logger)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        @Provides
        fun providesOkHttp(
            loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            val client = OkHttpClient.Builder()
            client.connectTimeout(10, TimeUnit.SECONDS)
            client.readTimeout(10, TimeUnit.SECONDS)
            client.writeTimeout(10, TimeUnit.SECONDS)
            client.addInterceptor(loggingInterceptor)
            return client.build()
        }

        @Provides
        fun providesMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Provides
        fun providesRetrofit(
            appLocale: AppLocale,
            serverAddress: ServerAddress,
            moshi: Moshi,
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(serverAddress.address + "/" + appLocale.prefix)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }
    }
}
