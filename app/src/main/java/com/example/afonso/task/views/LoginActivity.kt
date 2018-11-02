package com.example.afonso.task.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.afonso.task.R
import com.example.afonso.task.business.UserBusiness
import com.example.afonso.task.constants.TaskConstants
import com.example.afonso.task.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserBusiness: UserBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mUserBusiness = UserBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)

        setListeners()

        verifyLoggedUser()

    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.buttonLogin -> {
                handleLogin()
            }
        }
    }

    private fun setListeners(){
        buttonLogin.setOnClickListener(this)
    }

    private fun verifyLoggedUser(){
        val userId = mSecurityPreferences.getStorage(TaskConstants.KEY.USER_ID)
        val userName = mSecurityPreferences.getStorage(TaskConstants.KEY.USER_NAME)

        if (userId != "" && userName != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleLogin(){
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        if (mUserBusiness.login(email,password)){

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        } else{
            Toast.makeText(this, getString(R.string.usuario_senha_incorretos), Toast.LENGTH_LONG).show()

        }
    }
}
