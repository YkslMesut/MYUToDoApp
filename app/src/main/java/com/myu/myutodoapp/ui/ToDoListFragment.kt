package com.myu.myutodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myu.myutodoapp.R
import com.myu.myutodoapp.adapter.TodoAdapter
import com.myu.myutodoapp.databinding.FragmentToDoListBinding
import com.myu.myutodoapp.model.Todo
import com.myu.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoListFragment : Fragment() {
    private var _binding : FragmentToDoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var todoAdapter : TodoAdapter
    private val viewModel : TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       _binding =  FragmentToDoListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvAndObserver()


        binding.fbAddToDo.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_toDoListFragment_to_addToDoFragment
            )
        }
    }

    private fun setupRvAndObserver() {
        todoAdapter  = TodoAdapter()
        binding.rvListToDo.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
        viewModel.getAllTodos.observe(viewLifecycleOwner) {
            updateUi(it)
            todoAdapter.differ.submitList(it)
        }
    }

    private fun updateUi(list: List<Todo>) {
        if (list.isNotEmpty()) {
            binding.rvListToDo.visibility = View.VISIBLE
            binding.cardViewNoTodo.visibility = View.GONE
        } else {
            binding.rvListToDo.visibility = View.GONE
            binding.cardViewNoTodo.visibility = View.VISIBLE
        }
    }
}