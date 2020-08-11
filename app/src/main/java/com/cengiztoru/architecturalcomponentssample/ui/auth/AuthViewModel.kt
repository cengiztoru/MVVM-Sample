package com.cengiztoru.architecturalcomponentssample.ui.auth

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cengiztoru.architecturalcomponentssample.data.repositories.UserRepository
import com.cengiztoru.architecturalcomponentssample.util.ApiExceptions
import com.cengiztoru.architecturalcomponentssample.util.Coroutines


/**     Code With 💗
 * Created by Cengiz TORU
 * cengiztoru@gmail.com
 */

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null
    var listener: AuthListener? = null

    val liveEmail: MutableLiveData<String> = MutableLiveData()


    fun onLoginClick(view: View) {
        listener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            listener?.onFailure("Invalid email or password")
            return
        }




        Coroutines.main {
            try {
                val response = UserRepository().userLogin(email!!, password!!)
                response.user?.let {
                    listener?.onSuccess(it)
                    return@main
                }
                listener?.onFailure(response.message!!)
            } catch (e: ApiExceptions) {
                listener?.onFailure(e.message!!)
            }


        }

        //BEFORE HANDLING API EXCEPTIONS
//        Coroutines.main {
//            val response = UserRepository().userLogin(email!!, password!!)
//            if (response.isSuccessful) {
//                listener?.onSuccess(response.body()?.user!!)
//            } else {
//                listener?.onFailure("Error code ${response.code()}")
//            }
//        }


        //BEFORE COROUTINES
        //todo DI
//        val loginResponse = UserRepository().userLogin(email!!, password!!)
//        listener?.onSuccess(loginResponse)
    }

}