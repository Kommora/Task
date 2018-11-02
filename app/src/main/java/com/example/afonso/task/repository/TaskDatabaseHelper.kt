package com.example.afonso.task.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.afonso.task.constants.DatabaseConstants

class TaskDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Usado para criação do banco de dados
    companion object {
        private val DATABASE_VERSION: Int = 1
        private val DATABASE_NAME: String = "Tasks.db"
    }

    // Criação da tabela de usuário
    private val createTableUser = (
            "create table " + DatabaseConstants.USER.TABLE_NAME + " ("
                    + DatabaseConstants.USER.COLUMNS.ID + " integer primary key autoincrement, "
                    + DatabaseConstants.USER.COLUMNS.NAME + " text, "
                    + DatabaseConstants.USER.COLUMNS.PASSWORD + " text, "
                    + DatabaseConstants.USER.COLUMNS.EMAIL + " text);")

    // Criação da tabela de prioridades
    private val createTablePriority = (
            "create table " + DatabaseConstants.PRIORITY.TABLE_NAME + " ("
                    + DatabaseConstants.PRIORITY.COLUMNS.ID + " integer primary key, "
                    + DatabaseConstants.PRIORITY.COLUMNS.DESCRIPTION + " text);")

    // Criação da tabela de tarefas
    private val createTableTask = (
            "create table " + DatabaseConstants.TASK.TABLE_NAME + " ("
                    + DatabaseConstants.TASK.COLUMNS.ID + " integer primary key autoincrement, "
                    + DatabaseConstants.TASK.COLUMNS.USERID + " integer, "
                    + DatabaseConstants.TASK.COLUMNS.PRIORITYID + " integer, "
                    + DatabaseConstants.TASK.COLUMNS.DESCRIPTION + " text, "
                    + DatabaseConstants.TASK.COLUMNS.COMPLETE + " integer, "
                    + DatabaseConstants.TASK.COLUMNS.DUEDATE + " text);")

    private val insertPriorities = ("INSERT INTO ${DatabaseConstants.PRIORITY.TABLE_NAME}"
            + " values (1, 'Baixa'), (2, 'Média'), (3, 'Alta'), (4, 'Crítica')")

    // Remoção de tabelas

    private val dropTableUser = "DROP TABLE IF EXISTS " + DatabaseConstants.USER.TABLE_NAME
    private val dropTablePriority = "DROP TABLE IF EXISTS " + DatabaseConstants.PRIORITY.TABLE_NAME
    private val dropTableTask = "DROP TABLE IF EXISTS " + DatabaseConstants.TASK.TABLE_NAME


    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        // Faz a criação das tabelas
        sqLiteDatabase.execSQL(createTableUser)
        sqLiteDatabase.execSQL(createTablePriority)
        sqLiteDatabase.execSQL(createTableTask)
        sqLiteDatabase.execSQL(insertPriorities)

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        // Remover todas as tabelas
        // sqLiteDatabase.execSQL(dropTableUser)
        // sqLiteDatabase.execSQL(dropTablePriority)
        // sqLiteDatabase.execSQL(dropTableTask)

        // Fazer a criação novamente
        // onCreate(sqLiteDatabase)


    }
}