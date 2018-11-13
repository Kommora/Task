package com.example.afonso.task.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.afonso.task.R

class TaskListFragment : Fragment(), View.OnClickListener{

    private lateinit var mContext: Context

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

        return rootView
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.floatAddTask -> {
                startActivity(Intent(mContext, TaskFormActivity::class.java))
            }
        }
    }


}
