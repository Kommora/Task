package com.example.afonso.task.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.afonso.task.R
import com.example.afonso.task.adapter.TaskListAdapter
import com.example.afonso.task.business.TaskBusiness
import com.example.afonso.task.util.SecurityPreferences

class TaskListFragment : Fragment(), View.OnClickListener{

    private lateinit var mContext: Context
    private lateinit var mRecycleTaskList: RecyclerView

    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences

    companion object {
        @JvmStatic
        fun newInstance() : TaskListFragment {
            /*Bundle args = new Bundle()
            args.putString()
            args.putString()
            fragments.setArguments(args)*/
            return TaskListFragment()

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_task_list, container, false)

        rootView.findViewById<FloatingActionButton>(R.id.floatAddTask).setOnClickListener(this)
        mContext = rootView.context

        mTaskBusiness = TaskBusiness(mContext)
        mSecurityPreferences = SecurityPreferences(mContext)

        //Busca o elemento
        mRecycleTaskList = rootView.findViewById(R.id.recyclerTaskList)

        //Define um adapter para a lista de tarefas
        mRecycleTaskList.adapter = TaskListAdapter(mutableListOf())

        //Definir Layout para o Recycler
        mRecycleTaskList.layoutManager = LinearLayoutManager(mContext)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    private fun loadTasks(){
        mRecycleTaskList.adapter = TaskListAdapter(mTaskBusiness.getList())
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.floatAddTask -> {
                startActivity(Intent(mContext, TaskFormActivity::class.java))
            }
        }
    }


}
