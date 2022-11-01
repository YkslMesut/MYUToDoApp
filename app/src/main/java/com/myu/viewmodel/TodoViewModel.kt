package com.myu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.myu.myutodoapp.model.Todo
import com.myu.myutodoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel(){

    fun insertTodo(todo : Todo) = viewModelScope.launch {
        todoRepository.insertTodo(todo)
    }

    val getAllTodos = todoRepository.getAllToDos().asLiveData()



}