package com.FlowsDemo.roomDatabase

import androidx.lifecycle.LiveData

class Repository(private val mDao: UserDao) {

    // get all the events
    fun getAllEvents(): LiveData<List<DataUser>> = mDao.getAllEvents()

    // adds an event to our database.
    suspend fun insertEvent(data: DataUser) {
        mDao.insertEvent(data)
    }

    // deletes an data from database.
    suspend fun deleteEvent(data: DataUser) {
        mDao.deleteEvent(data)
    }
    // updates an data from database.
    suspend fun updateEvent(data: DataUser) {
        mDao.updateEvent(data)
    }

    //delete an event by id.
    suspend fun deleteEventById(id: Int) = mDao.deleteEventById(id)

    // delete all events
    suspend fun clearEvent() = mDao.clearEvent()
}