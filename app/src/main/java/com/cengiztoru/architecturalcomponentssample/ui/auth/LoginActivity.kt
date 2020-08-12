package com.cengiztoru.architecturalcomponentssample.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cengiztoru.architecturalcomponentssample.R
import com.cengiztoru.architecturalcomponentssample.data.network.MyApi
import com.cengiztoru.architecturalcomponentssample.data.network.NetworkConnectionsInterceptor
import com.cengiztoru.architecturalcomponentssample.data.network.responses.AuthResponse
import com.cengiztoru.architecturalcomponentssample.data.repositories.UserRepository
import com.cengiztoru.architecturalcomponentssample.databinding.ActivityLoginBinding
import com.cengiztoru.architecturalcomponentssample.ui.quotes.QuotesActivity
import com.cengiztoru.architecturalcomponentssample.util.hide
import com.cengiztoru.architecturalcomponentssample.util.show
import com.cengiztoru.architecturalcomponentssample.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {
    lateinit var binding: ActivityLoginBinding

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        val api = MyApi(NetworkConnectionsInterceptor(this))
        val repository = UserRepository(api)
        val factory = AuthViewModelFactory(repository)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.listener = this


        viewModel.liveEmail.observe(this, Observer {
            if (it?.length!! > 3) {
                binding.buttonSignIn.setBackgroundColor(Color.RED)
            } else {
                binding.buttonSignIn.setBackgroundColor(getColor(R.color.colorAccent))
            }
        })

        //TODO DELETE HERE
        startActivity(
            Intent(
                this,
                QuotesActivity::class.java
            )
        )
        finish()

    }

    override fun onStarted() {
        binding.progressBar.show()
    }

    override fun onSuccess(user: AuthResponse.User) {
        binding.progressBar.hide()

        startActivity(
            Intent(
                this,
                QuotesActivity::class.java
            )
        )
        finish()

//        toast(user.name + " LOGGED IN")
//        root_layout.snackbar(user.name + " LOGGED IN")
    }

    override fun onFailure(message: String) {
        binding.progressBar.hide()
        toast("Failure.$message")
    }
}