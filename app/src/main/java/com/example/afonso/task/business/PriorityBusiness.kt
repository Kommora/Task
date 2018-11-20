package com.example.afonso.task.business

import android.content.Context
import com.example.afonso.task.entities.PriorityEntity
import com.example.afonso.task.repository.PriorityRepository

class PriorityBusiness (context: Context) {

    private val mPriorityRepository: PriorityRepository = PriorityRepository.getInstance(context)

    fun getList(): MutableList<PriorityEntity> = mPriorityRepository.getList()

}