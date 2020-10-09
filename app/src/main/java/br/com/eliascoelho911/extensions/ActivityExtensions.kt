package br.com.eliascoelho911.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.showToast(error: String) {
    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
}