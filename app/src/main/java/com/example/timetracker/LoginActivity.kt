package com.example.timetracker

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.timetracker.models.LoginRequest
import com.example.timetracker.models.LoginResponse
import com.example.timetracker.network.ServiceGenerator
import com.example.timetracker.viewmodel.LoginViewModel

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {


    companion object {
        val TAG = LoginActivity::class.java.simpleName
    }


    private lateinit var loginViewModel: LoginViewModel

    private lateinit var loginResponse: LoginResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_login)
        setSupportActionBar(toolbar)
        ServiceGenerator(this)
        // Get the view model
        initialiseViewModel()

        btnLogin.setOnClickListener{
            if (hasNetwork()) performLogin() else Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initialiseViewModel() {
        loginViewModel =
            ViewModelProviders.of(this).get(LoginViewModel(application)::class.java)
    }



    private fun  performLogin() {
        showProgressBar(true)
        var loginRequest =  LoginRequest(etLoginId.text.toString(),etPassword.text.toString())
        loginViewModel.perforLogin(loginRequest).observe(this, Observer { repoList ->
            Log.i(TAG, "Viewmodel response: $repoList")

            repoList?.let {

                showProgressBar(false)
                if (it?.key.isNotEmpty()) {
                    loginResponse = it
                    MyApplication.Companion.setkey(it.key)
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
