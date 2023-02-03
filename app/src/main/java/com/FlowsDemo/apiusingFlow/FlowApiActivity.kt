package com.FlowsDemo.apiusingFlow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.FlowsDemo.flowsdemo.databinding.ActivityFlowApiBinding
import kotlinx.coroutines.launch

class FlowApiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlowApiBinding
    private lateinit var viewModel: CommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CommentViewModel::class.java]

        binding.button.setOnClickListener {
            if (binding.searchEditText.text.isNullOrEmpty()) {
                Toast.makeText(this, "Query Cant be empty", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getNewComment(binding.searchEditText.text.toString().toInt())
            }
        }
        lifecycleScope.launch {
            viewModel.commentState.collect {
                when (it.status) {
                    CommentApiState.Status.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                    CommentApiState.Status.SUCCESS -> {
                        binding.progressBar.isVisible = false

                        it.data?.let { it ->
                            binding.commentIdTextview.text = it.id.toString()
                            binding.nameTextview.text = it.name
                            binding.emailTextview.text = it.email
                            binding.commentTextview.text = it.comment
                        }
                    }
                    else -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@FlowApiActivity, "${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}