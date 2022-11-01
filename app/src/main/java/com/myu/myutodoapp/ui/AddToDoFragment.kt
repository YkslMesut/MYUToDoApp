package com.myu.myutodoapp.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.myu.myutodoapp.R
import com.myu.myutodoapp.databinding.FragmentAddToDoBinding
import com.myu.myutodoapp.model.Todo
import com.myu.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoFragment : Fragment() {

    private var _binding : FragmentAddToDoBinding?= null
    private val binding get() = _binding!!
    private val viewModel :  TodoViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       _binding = FragmentAddToDoBinding.inflate(inflater,container,false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener {
            view.findNavController().navigate(R.id.action_addToDoFragment_to_toDoListFragment)
        }
        binding.btnAddToDo.setOnClickListener {
            saveTodo(it)
        }
    }

    private fun saveTodo(it: View) {
        val todoName = binding.editText.text.toString()

        if (todoName.isNotEmpty()) {
            val todo = Todo(0,todoName)
            viewModel.insertTodo(todo)
            Snackbar.make(it,"Todo Added", Snackbar.LENGTH_LONG).show()

            it.findNavController().navigate(
                R.id.action_addToDoFragment_to_toDoListFragment
            )
        } else {
            val toast = Toast.makeText(activity,"Todo title cannot be empty", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()

        }

    }

}