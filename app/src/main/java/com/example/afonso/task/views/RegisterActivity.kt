package com.example.afonso.task.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.afonso.task.R
import com.example.afonso.task.business.UserBusiness
import com.example.afonso.task.repository.UserRepository
import com.example.afonso.task.util.ValidationException
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.Exception

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserBusiness : UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        //Variaveis da Classe
        mUserBusiness = UserBusiness(this)

        //Eventos
        setListeners()
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.buttonSave -> {
                handleSave()
            }
        }
    }

    private fun setListeners(){
        buttonSave.setOnClickListener(this)
    }

    private fun handleSave(){

        val name = editName.text.toString()
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        try {

            mUserBusiness.insert(name, email, password)

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }catch (e: ValidationException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.erro_inesperado), Toast.LENGTH_LONG).show()
        }



    }

}
