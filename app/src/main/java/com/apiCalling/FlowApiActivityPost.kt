package com.apiCalling

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.FlowsDemo.flowsdemo.databinding.ActivityFlowApi2Binding
import com.example.navigatorapp.Weather.weatherModel.WeatherListw
import kotlinx.coroutines.launch

class FlowApiActivityPost : AppCompatActivity() {
    private lateinit var binding: ActivityFlowApi2Binding
    private lateinit var viewModel: CommentViewModelPost
    private lateinit var wAdapter: WeatherAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowApi2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CommentViewModelPost::class.java]

        lifecycleScope.launch {
            viewModel.commentState.collect {
                when (it.status) {
                    CommentApiStatePost.Status.LOADING -> {
                        binding.progressHome.isVisible = true
                    }
                    CommentApiStatePost.Status.SUCCESS -> {
                        binding.progressHome.isVisible = false

                        it.data?.let { it ->
                            Log.d("TAG", "onCreate: dataofPost ${it}")
                            prepareRecyclerView(it.list)
                        }
                    }
                    else -> {
                        binding.progressHome.isVisible = false
                        Toast.makeText(
                            this@FlowApiActivityPost,
                            " error ${it.message}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }

    private fun prepareRecyclerView(arrayList: java.util.ArrayList<WeatherListw>) {
        wAdapter = WeatherAdapter(arrayList)
        binding.rvHome.apply {
            layoutManager =
                GridLayoutManager(applicationContext, 2)
            adapter = wAdapter
        }
    }
}

