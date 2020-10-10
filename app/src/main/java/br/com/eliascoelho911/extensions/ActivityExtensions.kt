package br.com.eliascoelho911.extensions

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.eliascoelho911.R

fun Activity.showToast(error: String) {
    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
}

fun Activity.showErrorDialog(error: String) {
    AlertDialog.Builder(this).setTitle(getString(R.string.error)).setMessage(error)
        .setPositiveButton(
            "OK"
        ) { _, _ -> }.show()
}