package com.example.afonso.task.entities

import android.accounts.AuthenticatorDescription

class TaskEntity(val id: Int,
                 val userId: Int,
                 val priorityId: Int,
                 var description: String,
                 var dueDate: String,
                 var complete: Boolean)