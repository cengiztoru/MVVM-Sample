package com.cengiztoru.architecturalcomponentssample.ui.auth


/**     Code With 💗
 * Created by Cengiz TORU
 * cengiztoru@gmail.com
 */
 
interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message : String)
}