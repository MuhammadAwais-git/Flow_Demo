package com.FlowsDemo.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "evTable")
data class DataUser(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "eventid")
    val id: Int?,

    @ColumnInfo(name = "eventtitle")
    val title: String?,

    @ColumnInfo(name = "eventdescription")
    val description: String?
)