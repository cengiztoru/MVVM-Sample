package com.cengiztoru.architecturalcomponentssample.ui.quotes


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cengiztoru.architecturalcomponentssample.data.network.NetworkState
import com.cengiztoru.architecturalcomponentssample.data.network.responses.QuotesResponse
import com.cengiztoru.architecturalcomponentssample.data.repositories.UserRepository
import com.cengiztoru.architecturalcomponentssample.util.ApiExceptions
import com.cengiztoru.architecturalcomponentssample.util.Coroutines
import com.cengiztoru.architecturalcomponentssample.util.NoInternetExceptions

class QuotesViewModel(private val repository: UserRepository) : ViewModel() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _quotes = MutableLiveData<List<QuotesResponse.Quote>>()
    val quotes: LiveData<List<QuotesResponse.Quote>>
        get() = _quotes

    //get data from remote here. Not in UI.
    // By this way data is will be lifecycle aware
    //So if user turn his phone to landscape, data will not re-request, because in store viewmodel
    init {
        getQuotes()
    }


    fun getQuotes() {
        _networkState.postValue(NetworkState.LOADING)

        Coroutines.main {
            try {
                val response = repository.getQuotes()
                response.quotes.let {
                    _networkState.postValue(NetworkState.LOADED)
                    _quotes.value = it
                    Log.e("quotes", "success")
                    return@main
                }
                _networkState.postValue(NetworkState.ERROR)
                Log.e("quotes", "error")


            } catch (e: ApiExceptions) {
                _networkState.postValue(NetworkState.ERROR)
                Log.e("quotes", "failure")
            } catch (e: NoInternetExceptions) {
                _networkState.postValue(NetworkState.NOINTERNET)
                Log.e("quotes", "internet exception")

            }

        }
    }

}