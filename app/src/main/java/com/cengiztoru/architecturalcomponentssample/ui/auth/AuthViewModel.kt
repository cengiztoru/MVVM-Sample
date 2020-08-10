package com.cengiztoru.architecturalcomponentssample.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel


/**     Code With 💗
 * Created by Cengiz TORU
 * cengiztoru@gmail.com
 */

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null
    var listener : AuthListener? = null

    fun onLoginClick(view : View){
        listener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            listener?.onFailure("Invalid email or password")
            return
        }
        listener?.onSuccess()
    }

}