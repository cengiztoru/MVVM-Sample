package com.cengiztoru.architecturalcomponentssample.ui.auth

import androidx.lifecycle.LiveData


/**     Code With 💗
 * Created by Cengiz TORU
 * cengiztoru@gmail.com
 */

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message: String)
}