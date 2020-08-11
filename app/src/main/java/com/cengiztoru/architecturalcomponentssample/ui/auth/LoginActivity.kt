package com.cengiztoru.architecturalcomponentssample.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cengiztoru.architecturalcomponentssample.R
import com.cengiztoru.architecturalcomponentssample.databinding.ActivityLoginBinding
import com.cengiztoru.architecturalcomponentssample.util.hide
import com.cengiztoru.architecturalcomponentssample.util.show
import com.cengiztoru.architecturalcomponentssample.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.listener = this

    }

    override fun onStarted() {
        binding.progressBar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            binding.progressBar.hide()
            toast("SUCCESS $it")
        })
    }

    override fun onFailure(message: String) {
        binding.progressBar.hide()
        toast("Failure.$message")
    }
}