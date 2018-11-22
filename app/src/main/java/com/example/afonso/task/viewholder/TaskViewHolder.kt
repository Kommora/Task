package com.example.afonso.task.viewholder

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.afonso.task.R
import com.example.afonso.task.entities.OnTaskListFragmentInteractionListener
import com.example.afonso.task.entities.TaskEntity
import com.example.afonso.task.repository.PriorityCacheConstants

class TaskViewHolder(itemView : View, val context: Context, val listener: OnTaskListFragmentInteractionListener) : RecyclerView.ViewHolder(itemView){

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

        //Evento de click para edição
        mTextDescription.setOnClickListener(View.OnClickListener {
            listener.onListClick(task.id)
        })

        mTextDescription.setOnLongClickListener(View.OnLongClickListener {
            showConfirmDialog(task)
            true
        })
    }

    private fun showConfirmDialog(task: TaskEntity) {
        AlertDialog.Builder(context)
            .setTitle("Remoção de Tarefa")
            .setMessage("Deseja remover ${task.description}?")
            .setIcon(R.drawable.ic_delete_black_24dp)
            .setPositiveButton("Remover", DialogInterface.OnClickListener { dialog, which -> listener.onDeleteClick(task.id) })
            .setNegativeButton("Cancelar", null).show()
    }

}