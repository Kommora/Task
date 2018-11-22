package com.example.afonso.task.entities

interface OnTaskListFragmentInteractionListener {
    fun onListClick(taskId : Int)

    fun onDeleteClick(taskId : Int)
}