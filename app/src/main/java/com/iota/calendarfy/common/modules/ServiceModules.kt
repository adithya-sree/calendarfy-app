package com.iota.calendarfy.common.modules

import com.iota.calendarfy.service.api.ProfileService
import com.iota.calendarfy.util.AuthUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Adithya S. on 04/02/2020
 */

object ServiceModules {

    fun get(): Module {
        return module {
            single {
                provideRetrofit(
                    get()
                )
            }

            factory {
                provideOkHttpClient(
                    get()
                )
            }

            factory {
                provideInterceptor()
            }

            factory {
                provideProfileService(
                    get()
                )
            }
        }
    }

    private fun provideRetrofit(httpclient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8085/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpclient)
            .build()
    }

    private fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request: Request =
                chain.request().newBuilder().addHeader("Authorization", AuthUtil.getHeader()).build()
            chain.proceed(request)
        }
    }

    private fun provideProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }
}