package com.FlowsDemo.roomDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RoomDBViewModel(application: Application) : AndroidViewModel(application) {

    val allEvents: LiveData<List<DataUser>>
    val repository: Repository

    // initialize dao, repository and all events
    init {
        val dao = DatabaseClass.getDatabase(application).getDao()
        repository = Repository(dao)
        allEvents = repository.getAllEvents()
    }

    fun insertEvent(data: DataUser) =
        viewModelScope.launch(Dispatchers.IO) { repository.insertEvent(data) }

    fun updateEvent(data: DataUser) =
        viewModelScope.launch(Dispatchers.IO) { repository.updateEvent(data) }

    fun deleteEvent(data: DataUser) =
        viewModelScope.launch(Dispatchers.IO) { repository.deleteEvent(data) }

    fun deleteEventById(id: Int) =
        viewModelScope.launch(Dispatchers.IO) { repository.deleteEventById(id) }

    fun clearEvent() =
        viewModelScope.launch(Dispatchers.IO) { repository.clearEvent() }
}