package com.example.afonso.task.business

import android.content.Context
import com.example.afonso.task.constants.TaskConstants
import com.example.afonso.task.entities.TaskEntity
import com.example.afonso.task.repository.TaskRepository
import com.example.afonso.task.util.SecurityPreferences

class TaskBusiness(context: Context) {

    private val mTaskRepository: TaskRepository = TaskRepository.getInstance(context)
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)

    fun getList() : MutableList<TaskEntity> {
        val userId = mSecurityPreferences.getStoredString(TaskConstants.KEY.USER_ID).toInt()
        mTaskRepository.getList(userId)
        return mTaskRepository.getList(userId)
    }

    fun insert(taskEntity: TaskEntity) = mTaskRepository.insert(taskEntity)
}
