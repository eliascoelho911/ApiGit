package br.com.eliascoelho911.retrofit.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val FAILURE_MESSAGE = "A network error has occurred. Check your Internet connection and try again later."
private const val USER_NOT_FOUND = "User not found. Please enter another name."

class Answer<T>(
    private val success: (T) -> Unit,
    private val failure: (error: String) -> Unit
) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        failure(FAILURE_MESSAGE)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            if (response.body() != null) {
                success(response.body()!!)
            }
        } else {
            if (response.code() == 404) {
                failure(USER_NOT_FOUND)
            } else {
                failure(FAILURE_MESSAGE)
            }
        }
    }
}