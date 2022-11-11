package me.gabriel.hearthstone.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.gabriel.hearthstone.data.HearthStoneApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideBiomarkersService(moshi: Moshi): HearthStoneApiService {
        val okHttpClient = OkHttpClient().newBuilder().callTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).addInterceptor(Interceptor { chain ->
                    val request = chain.request().newBuilder().addHeader(
                            "X-RapidAPI-Key", "b5b0eaf685mshdbf90ad52e039b1p1ebd9ejsnad4dc4036443"
                        ).addHeader("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
                        .build()
                    chain.proceed(request)
                }).build()
        return Retrofit.Builder().baseUrl("https://omgvamp-hearthstone-v1.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient).build()
            .create(HearthStoneApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()


}
