package com.evernext10.core.data.di

import android.content.Context
import com.evernext10.core.data.remote.moshi.DateMoshiAdapter
import com.evernext10.core.data.remote.retrofit.AuthInterceptor
import com.evernext10.core.data.remote.retrofit.OfflineInterceptor
import com.evernext10.core.data.remote.retrofit.OnlineInterceptor
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val coreModule = module {

    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(DateMoshiAdapter)
        .build()

    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun provideOkHttpClient(
        context: Context,
        loginInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val cacheSize10MB = 10 * 1024 * 1024
        val timeOutMS = 10L

        return OkHttpClient.Builder()
            .readTimeout(timeOutMS, TimeUnit.SECONDS)
            .connectTimeout(timeOutMS, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, (cacheSize10MB).toLong()))
            .addInterceptor(loginInterceptor)
            .addInterceptor(AuthInterceptor)
            .addInterceptor(OnlineInterceptor)
            .addInterceptor(OfflineInterceptor(context))
            .build()
    }

    fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://api.mercadolibre.com/sites/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { provideDispatcher() }
    single { provideMoshi() }
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get(), get()) }
    single { provideRetrofit(get(), get()) }
}
