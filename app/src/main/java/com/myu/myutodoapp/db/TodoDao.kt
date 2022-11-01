package com.myu.myutodoapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myu.myutodoapp.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo : Todo)

    @Query("SELECT * FROM todo ORDER BY todoTitle ASC")
    fun getAllTodos() : Flow<List<Todo>>
}