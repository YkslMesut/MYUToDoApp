package com.myu.myutodoapp.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myu.myutodoapp.databinding.TodoLayoutAdapterBinding
import com.myu.myutodoapp.model.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding : TodoLayoutAdapterBinding) :
    RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo  = differ.currentList[position]

        holder.binding.apply {
            textView.text = todo.todoTitle
        }

        holder.binding.cbTodo.apply {
           setOnClickListener {
               holder.binding.apply {
                   if (isChecked) {
                       textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                   } else {
                       textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                   }
               }
           }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}