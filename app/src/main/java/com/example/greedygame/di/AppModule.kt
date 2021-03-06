package com.example.greedygame.di


import com.example.greedygame.api.NetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
val TIMEOUT=10
    @Singleton
    @Provides
    @Named("logging")
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY

        }

    @Singleton
    @Provides
    @Named("header")
    fun provideHeaderInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", NetworkApi.CLIENT_ID)
                .build()
            val newRequest = request.newBuilder()
                .url(newUrl)
                .method(request.method, request.body)
                .build()
            chain.proceed(newRequest)
        }
    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named("logging") logging: Interceptor,
        @Named("header") header: Interceptor
        ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(header)
            .addInterceptor(logging)
            .build()

  /*  @Provides
    @Singleton
    fun provideTwitterRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHTTP = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder()
            .baseUrl("https://6f8a2fec-1605-4dc7-a081-a8521fad389a.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHTTP)
            .build()
    }*/
    @Singleton
    @Provides
    fun provideAppRetrofit(
        okHttpClient: OkHttpClient

    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()



    @Provides
    @Singleton
    fun provideTwitterApi(retrofit: Retrofit): NetworkApi =
        retrofit.create(NetworkApi::class.java)

}