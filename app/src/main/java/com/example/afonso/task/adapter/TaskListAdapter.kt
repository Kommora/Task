package com.example.afonso.task.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.afonso.task.R
import com.example.afonso.task.entities.TaskEntity
import com.example.afonso.task.viewholder.TaskViewHolder

//RecycleAdapter espera uma viewHolder
class TaskListAdapter (val taskList: List<TaskEntity>) : RecyclerView.Adapter<TaskViewHolder>() {

    //Cira layout para cada linha
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val context = parent?.context

        //Inflater para atribuir uma view
        val inflater =  LayoutInflater.from(context)

        //instancia uma view passando como parametros( uma view, Um view group, pai se precisar)
        val view = inflater.inflate(R.layout.row_task_list, parent, false)

        return TaskViewHolder(view)
    }

    //Qtd de linhas
    override fun getItemCount(): Int {
        return taskList.count()
    }

    //Atribui valor para determinada linha
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bindData(task)
    }


}