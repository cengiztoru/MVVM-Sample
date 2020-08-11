package com.cengiztoru.architecturalcomponentssample.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast


/**     Code With 💗
 * Created by Cengiz TORU
 * cengiztoru@gmail.com
 */

fun Context.toast(message: String) {
    Toast.makeText(this, message + "", Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}