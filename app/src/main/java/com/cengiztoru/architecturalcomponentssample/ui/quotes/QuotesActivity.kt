package com.cengiztoru.architecturalcomponentssample.ui.quotes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cengiztoru.architecturalcomponentssample.R
import com.cengiztoru.architecturalcomponentssample.data.network.MyApi
import com.cengiztoru.architecturalcomponentssample.data.network.NetworkConnectionsInterceptor
import com.cengiztoru.architecturalcomponentssample.data.network.NetworkState
import com.cengiztoru.architecturalcomponentssample.data.repositories.UserRepository
import com.cengiztoru.architecturalcomponentssample.util.toast
import kotlinx.android.synthetic.main.activity_quotes.*

class QuotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        val api = MyApi(NetworkConnectionsInterceptor(this))
        val repository = UserRepository(api)
        val factory = QuotesViewModelFactory(repository)


        val viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        viewModel.networkState.observe(this, Observer {

            when (it) {
                NetworkState.LOADED -> progress_bar.visibility = View.GONE
                NetworkState.LOADING -> progress_bar.visibility = View.VISIBLE
                else -> {
                    toast(it.msg)
                    progress_bar.visibility = View.GONE
                }
            }
        })

        viewModel.quotes.observe(this, Observer { quotes ->

            recyclerview.also {
                it.setHasFixedSize(true)
                it.adapter = QuotesAdapter(quotes)
            }
        })
    }
}