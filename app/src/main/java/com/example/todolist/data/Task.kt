package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Auto-gerado pelo Room
    val title: String,
    val body: String? = null,
    val startTime: String,
    val endTime: String
)