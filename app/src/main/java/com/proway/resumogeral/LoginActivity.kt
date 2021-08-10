package com.proway.resumogeral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.proway.resumogeral.model.Credential
import com.proway.resumogeral.model.LoginResponse
import com.proway.resumogeral.service.RetrofitBuilder
import com.proway.resumogeral.utis.snackBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), Callback<LoginResponse> {
    private lateinit var senha: EditText
    private lateinit var userEmail: EditText
    private lateinit var btnEntra: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadComponents()
        loadEvents()
    }

    private fun loadEvents() {
        btnEntra.apply {
            setOnClickListener {
                makeLogin()
            }
        }
    }

    private fun loadComponents() {
        senha = findViewById(R.id.passwordInput)
        userEmail = findViewById(R.id.emailInput)

        btnEntra = findViewById(R.id.loginButton)
    }

    private fun makeLogin() {
        val email = userEmail.text.toString()
        val senha = senha.text.toString()
        val credential = Credential(email, senha)

        if (credential.checkPassword() && credential.checkUserName()) {
            triggerRequestLogin(credential)

        } else {
            snackBar(userEmail, R.string.invalid_user)
        }

    }



    private fun triggerRequestLogin(credential: Credential) {
        val service = RetrofitBuilder.getAuthenticationServices()
        val call = service.login(credential)
        call.clone().enqueue(this)
    }

    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        if (response.body() != null) {
            val loginResponse = response.body()!!
            if (loginResponse.isError()) {
                snackBar(userEmail, R.string.invalid_user)
            } else {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)

                }

            }
        } else {
            snackBar(userEmail, R.string.invalid_user)
        }
    }

    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
        snackBar(userEmail, R.string.invalid_user)

    }
}