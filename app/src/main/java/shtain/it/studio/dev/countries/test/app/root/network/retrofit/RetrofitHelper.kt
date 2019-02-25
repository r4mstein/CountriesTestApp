package shtain.it.studio.dev.countries.test.app.root.network.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Alex Shtain on 25.02.2019.
 */
private const val BASE_URL          = "https://restcountries.eu/"
private const val API_VERSION       = "rest/v2/"

class RetrofitHelper {

    fun <T> createService(_class: Class<T>, level: HttpLoggingInterceptor.Level): T {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = level
        builder.addInterceptor(loggingInterceptor)

        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(builder.build())
            .baseUrl(BASE_URL + API_VERSION)
            .build()

        return retrofit.create(_class)
    }
}