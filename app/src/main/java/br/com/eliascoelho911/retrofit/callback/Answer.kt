package br.com.eliascoelho911.retrofit.callback

import android.content.Context
import br.com.eliascoelho911.R
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Answer<T>(
    private val success: (T) -> Unit,
    private val failure: (error: String) -> Unit
) : Callback<T> {

    private val context: Context by inject(Context::class.java)
    private val failureMessage = context.getString(R.string.failure_message)

    override fun onFailure(call: Call<T>, t: Throwable) {
        failure(failureMessage)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            if (response.body() != null) {
                success(response.body()!!)
            }
        } else {
            if (response.code() == 404) {
                val userNotFound = context.getString(R.string.user_not_found)
                failure(userNotFound)
            } else {
                failure(failureMessage)
            }
        }
    }
}