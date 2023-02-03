package com.FlowsDemo.flowsdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.FlowsDemo.apiusingFlow.FlowApiActivity
import com.FlowsDemo.flowsdemo.databinding.ActivityMainBinding
import com.FlowsDemo.roomDatabase.RoomDatabaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var flow: Flow<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFlow()
        setupClicks()

        binding.Intent.setOnClickListener {
            startActivity(Intent(this,FlowApiActivity::class.java))
        }
        binding.roomdb.setOnClickListener {
            startActivity(Intent(this,RoomDatabaseActivity::class.java))
        }
    }

    private fun setupFlow() {
        flow = flow {
            Log.d("TAG", "setupFlow: ")
            (0..15).forEach {
                delay(300)
                Log.d("TAG", "Emitting $it")
                emit(it)
            }

        }.flowOn(Dispatchers.Default)

    }

    private fun setupClicks() {
        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                flow.collect{
                    Log.d("TAG", "${it}: ")
                }
            }

        }
    }
}