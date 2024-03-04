package com.example.idnow_v2application

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.idnow_v2application.R
import com.example.idnow_v2application.di.NetworkModule
import com.example.idnow_v2application.network.JWTData
import com.example.idnow_v2application.network.JWTUser
import com.example.idnow_v2application.network.ProductApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var editTxtUsername: EditText
    lateinit var editTxtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var productApi: ProductApi
    lateinit var textViewError : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        productApi = NetworkModule.buildRetrofit(
            NetworkModule.createOkHttpClient(),
            NetworkModule.createGsonConverterFactory()
        ).create(ProductApi::class.java)

        editTxtUsername = findViewById(R.id.editTxtUsername)
        editTxtPassword = findViewById(R.id.editTxtPassword)
        btnLogin = findViewById(R.id.btnLogin)


        btnLogin.setOnClickListener(btnLoginClick)

    }

    val btnLoginClick = View.OnClickListener {
        val username = editTxtUsername.text.toString()
        val password = editTxtPassword.text.toString()
        val jwtUser = JWTUser(username, password)
        if (jwtUser.username == "" || jwtUser.password == "") {
            Toast.makeText(
                this,
                "You have left the username or password empty, please check",
                Toast.LENGTH_LONG
            ).show()
        }
        productApi.login(jwtUser).enqueue(object : Callback<JWTData> {
            override fun onResponse(call: Call<JWTData>, response: Response<JWTData>) {
                val user = response.body()
                Log.d("status", response.code().toString())
                if (user != null) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    textViewError.text = "Username or password is incorrect"
                    textViewError.visibility = View.VISIBLE
                }


            }

            override fun onFailure(call: Call<JWTData>, t: Throwable) {
                Log.e("Error", t.toString())
                Toast.makeText(this@LoginActivity, "Internet or server error", Toast.LENGTH_LONG)
                    .show()
            }

        })
    }
}