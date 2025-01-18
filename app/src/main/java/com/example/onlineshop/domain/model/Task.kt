package com.example.onlineshop.domain.model

data class Task(
    val title: String,
    val description: String,
    val dueDate: Long,
    val periority: Int,
    val relatedToSubject: String,
    val isComplete: Boolean,
)
