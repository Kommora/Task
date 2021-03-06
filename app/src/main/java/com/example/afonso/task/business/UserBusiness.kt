package com.example.afonso.task.business

import android.content.Context
import android.widget.Toast
import com.example.afonso.task.R
import com.example.afonso.task.constants.TaskConstants
import com.example.afonso.task.entities.UserEntity
import com.example.afonso.task.repository.UserRepository
import com.example.afonso.task.util.SecurityPreferences
import com.example.afonso.task.util.ValidationException
import java.lang.Exception

class UserBusiness (var context: Context) {

    private val mUserRepository : UserRepository = UserRepository.getInstance(context)
    private val mSecurityPreferences : SecurityPreferences = SecurityPreferences(context)

    fun login(email: String, password: String): Boolean{

        val user: UserEntity? = mUserRepository.get(email,password)

        return if(user != null){
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_ID, user.id.toString())
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_NAME, user.name)
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_EMAIL, user.email)

            true
        }else{
            false
        }

    }

    fun insert (name:String, email: String, password: String){

        try {

            if(name == "" || email == "" || password == ""){
                throw ValidationException(context.getString(R.string.informe_todos_campos))
            }

            if(mUserRepository.isEmailExistent(email)){
                throw ValidationException(context.getString(R.string.email_igual))

            }

            val userId = mUserRepository.insert(name,email,password)

            mSecurityPreferences.storeString(TaskConstants.KEY.USER_ID, userId.toString())
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_NAME, name)
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_EMAIL, email)



        }catch (e: Exception){
            throw e
        }

    }

}