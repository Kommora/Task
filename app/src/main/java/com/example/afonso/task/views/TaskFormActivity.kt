package com.example.afonso.task.views

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.afonso.task.R
import com.example.afonso.task.business.PriorityBusiness
import com.example.afonso.task.business.TaskBusiness
import com.example.afonso.task.constants.TaskConstants
import com.example.afonso.task.entities.PriorityEntity
import com.example.afonso.task.entities.TaskEntity
import com.example.afonso.task.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_task_form.*
import java.text.SimpleDateFormat
import java.util.*

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private lateinit var mPriorityBusiness: PriorityBusiness
    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences
    private val mSimpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    private var mLstPrioritiesEntity: MutableList<PriorityEntity> = mutableListOf()
    private var mLstPrioritiesId: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mPriorityBusiness = PriorityBusiness(this)
        mTaskBusiness = TaskBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)

        loadPriorities()

        setListeners()
    }

    private fun loadPriorities(){
        mLstPrioritiesEntity = mPriorityBusiness.getList()

        val lstPriorities = mLstPrioritiesEntity.map { it.description }
        mLstPrioritiesId = mLstPrioritiesEntity.map { it.id }.toMutableList()

        val adapter = ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, lstPriorities)
        spinnerPriority.adapter = adapter
    }

    private fun setListeners() {
        buttonDate.setOnClickListener(this)
        buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.buttonDate -> {
                openDatePickerDialog()
            }
            R.id.buttonSave -> {
                handleSave()
            }
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        buttonDate.text = mSimpleDateFormat.format(calendar.time)
    }

    private fun openDatePickerDialog(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, this, year, month, dayOfMonth).show()
    }

    private fun handleSave(){
        try{

            val description = editDescription.text.toString()
            // [1,2,3,4]
            val priorityId = mLstPrioritiesId[spinnerPriority.selectedItemPosition]
            val complete = checkComplete.isChecked
            val dueDate = buttonDate.text.toString()

            val userId = mSecurityPreferences.getStorage(TaskConstants.KEY.USER_ID).toInt()

            val taskEntity = TaskEntity(0, userId, priorityId, description, dueDate, complete)
            mTaskBusiness.insert(taskEntity)
            Toast.makeText(this, getString(R.string.tarefa_inserida), Toast.LENGTH_LONG).show()

        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
