package com.example.afonso.task.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.afonso.task.R
import com.example.afonso.task.entities.TaskEntity
import com.example.afonso.task.repository.PriorityCacheConstants

class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    //variavel para referenciar
    private val mTextDescription: TextView = itemView.findViewById(R.id.textDescription)
    private val mTextPriority: TextView = itemView.findViewById(R.id.textPriority)
    private val mTextDate: TextView = itemView.findViewById(R.id.textDueDate)
    private val mImageTask: ImageView = itemView.findViewById(R.id.imageTask)

    fun bindData(task: TaskEntity){
        mTextDescription.text = task.description
        mTextPriority.text = PriorityCacheConstants.getPriorityDescription(task.priorityId)
        mTextDate.text = task.dueDate

        if(task.complete){
            mImageTask.setImageResource(R.drawable.ic_done)
        }
    }

}