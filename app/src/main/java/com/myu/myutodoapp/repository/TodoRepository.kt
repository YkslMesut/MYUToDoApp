package com.myu.myutodoapp.repository

import com.myu.myutodoapp.db.TodoDao
import com.myu.myutodoapp.model.Todo
import javax.inject.Inject

class TodoRepository
@Inject constructor(
    private val dao : TodoDao
){
    suspend fun insertTodo(todo : Todo) = dao.insertTodo(todo)

    fun getAllToDos() = dao.getAllTodos()

}