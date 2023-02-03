package com.FlowsDemo.roomDatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.FlowsDemo.flowsdemo.databinding.ActivityAddEventBinding


class AddEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEventBinding
    lateinit var viewModal: RoomDBViewModel
    var eventID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModal = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[RoomDBViewModel::class.java]

        val eventType = intent.getStringExtra("eventType")
        if (eventType.equals("Edit")) {

            val eventTitle = intent.getStringExtra("eventTitle")
            val eventDescription = intent.getStringExtra("eventDescription")
            eventID = intent.getIntExtra("eventId", -1)
            binding.btnSave.text = "Update Event"
            binding.titleET.setText(eventTitle)
            binding.descriptionET.setText(eventDescription)
        } else {
            binding.btnSave.text = "Save Event"
        }

        binding.btnSave.setOnClickListener {

            val eventTitle = binding.titleET.text.toString()
            val eventDescription = binding.descriptionET.text.toString()

            if (eventType.equals("Edit")) {

                if (eventTitle.isNotEmpty() && eventDescription.isNotEmpty()) {

                    val updatedEvent = DataUser(eventID, eventTitle, eventDescription)
                    viewModal.updateEvent(updatedEvent)
                    Toast.makeText(this, "Event Updated", Toast.LENGTH_LONG).show()
                }
            } else {
                if (eventTitle.isNotEmpty() && eventDescription.isNotEmpty()) {

                    viewModal.insertEvent(DataUser(null, eventTitle, eventDescription))
                    Toast.makeText(this, "Event Added", Toast.LENGTH_LONG).show()
                }
            }

            startActivity(Intent(applicationContext, RoomDatabaseActivity::class.java))
            this.finish()
        }
    }
}