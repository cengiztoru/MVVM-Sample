package com.cengiztoru.architecturalcomponentssample.ui.auth

import com.cengiztoru.architecturalcomponentssample.data.network.responses.AuthResponse


/**     Code With 💗
 * Created by Cengiz TORU
 * cengiztoru@gmail.com
 */

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: AuthResponse.User)
    fun onFailure(message: String)
}