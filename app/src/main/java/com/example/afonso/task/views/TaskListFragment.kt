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
import android.widget.Toast
import com.example.afonso.task.R
import com.example.afonso.task.adapter.TaskListAdapter
import com.example.afonso.task.business.TaskBusiness
import com.example.afonso.task.constants.TaskConstants
import com.example.afonso.task.entities.OnTaskListFragmentInteractionListener
import com.example.afonso.task.util.SecurityPreferences

class TaskListFragment : Fragment(), View.OnClickListener{

    private lateinit var mContext: Context
    private lateinit var mRecycleTaskList: RecyclerView

    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences

    private lateinit var mListener: OnTaskListFragmentInteractionListener

    private var mTaskFilter : Int = 0

    companion object {
        @JvmStatic
        fun newInstance(taskFilter : Int) : TaskListFragment {
            val args : Bundle = Bundle()
            args.putInt(TaskConstants.TASKFILTER.KEY, taskFilter)

            val fragment = TaskListFragment()
            fragment.arguments = args
            return fragment

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mTaskFilter = arguments!!.getInt(TaskConstants.TASKFILTER.KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_task_list, container, false)

        rootView.findViewById<FloatingActionButton>(R.id.floatAddTask).setOnClickListener(this)
        mContext = rootView.context

        //Inicializa variáves
        mTaskBusiness = TaskBusiness(mContext)
        mSecurityPreferences = SecurityPreferences(mContext)
        mListener = object : OnTaskListFragmentInteractionListener {
            override fun onListClick(taskId: Int) {

                val bundle: Bundle = Bundle()
                bundle.putInt(TaskConstants.BUNDLE.TASKID, taskId)

                val intent = Intent(mContext, TaskFormActivity::class.java)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDeleteClick(taskId: Int) {
                mTaskBusiness.delete(taskId)
                loadTasks()
                Toast.makeText(mContext, getString(R.string.tarefa_removida), Toast.LENGTH_LONG).show()
            }
        }

        //Busca o elemento
        mRecycleTaskList = rootView.findViewById(R.id.recyclerTaskList)

        //Define um adapter para a lista de tarefas
        mRecycleTaskList.adapter = TaskListAdapter(mutableListOf(), mListener)

        //Definir Layout para o Recycler
        mRecycleTaskList.layoutManager = LinearLayoutManager(mContext)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    private fun loadTasks(){
        mRecycleTaskList.adapter = TaskListAdapter(mTaskBusiness.getList(mTaskFilter), mListener)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.floatAddTask -> {
                startActivity(Intent(mContext, TaskFormActivity::class.java))
            }
        }
    }


}
