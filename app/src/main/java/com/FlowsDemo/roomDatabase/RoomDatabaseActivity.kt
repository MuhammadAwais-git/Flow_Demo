package com.FlowsDemo.roomDatabase

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.FlowsDemo.flowsdemo.R
import com.FlowsDemo.flowsdemo.databinding.ActivityRoomDatabaseBinding


class RoomDatabaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomDatabaseBinding
    private lateinit var viewModel: RoomDBViewModel
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(RoomDBViewModel::class.java)


        eventAdapter = EventAdapter(object : EventAdapter.EventDeleteIconClickInterface {
            override fun onEventDeleteIconClick(data: DataUser) {
                viewModel.deleteEvent(data)
                Toast.makeText(this@RoomDatabaseActivity, "Event Deleted", Toast.LENGTH_LONG).show()
            }
        }, object : EventAdapter.EventClickInterface {
            override fun onEventClick(data: DataUser) {
                // opening a new intent and passing a data to it.
                val intent = Intent(this@RoomDatabaseActivity, AddEventActivity::class.java)
                intent.putExtra("eventType", "Edit")
                intent.putExtra("eventTitle", data.title)
                intent.putExtra("eventDescription", data.description)
                intent.putExtra("eventId", data.id)
                startActivity(intent)
                finish()
            }

        })

        initView()
        observeEvents()
    }

    private fun initView() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddEventActivity::class.java)
            startActivity(intent)
        }

        binding.eventsRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RoomDatabaseActivity)
            adapter = eventAdapter
        }
    }

    private fun observeEvents() {
        viewModel.allEvents.observe(this, Observer { list ->
            list?.let {
                // updates the list.
                eventAdapter.updateList(it)
            }
        })
    }

    private fun clearEvent() {
        val dialog = AlertDialog.Builder(this, R.style.Theme_FlowsDemo)
        dialog.setTitle("Clear Event")
            .setMessage("Are you sure, you want to delete all event?")
            .setPositiveButton(android.R.string.ok) { _, _ ->
                viewModel.clearEvent().also {
                    Toast.makeText(this, "Event Deleted", Toast.LENGTH_LONG).show()
                }
            }.setNegativeButton(android.R.string.cancel, null).create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.clearEventItem -> clearEvent()
        }
        return super.onOptionsItemSelected(item)
    }


}