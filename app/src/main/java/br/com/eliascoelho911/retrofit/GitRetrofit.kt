package br.com.eliascoelho911.retrofit

import br.com.eliascoelho911.retrofit.service.GitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

class GitRetrofit {
    private val retrofit: Retrofit
    val service: GitService

    init {
        val client = configuraClient()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        service = retrofit.create(GitService::class.java)
    }

    private fun configuraClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging).build()
    }
}
