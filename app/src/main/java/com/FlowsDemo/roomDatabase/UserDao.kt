package com.FlowsDemo.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(data: DataUser)

    @Delete
    suspend fun deleteEvent(data: DataUser)

    @Update
    suspend fun updateEvent(data: DataUser)

    @Query("Select * from evTable order by eventid ASC")
    fun getAllEvents(): LiveData<List<DataUser>>

    @Query("DELETE FROM evTable")
    suspend fun clearEvent()

    @Query("DELETE FROM evTable WHERE eventid = :id")
    suspend fun deleteEventById(id: Int)
}
