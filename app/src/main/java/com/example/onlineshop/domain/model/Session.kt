package com.example.onlineshop.domain.model

data class Session(
    val sessionId : Int,
    val sessionSubjectId: Int,
    val relatedtToSubject: String,
    val date: Long,
    val duration: Long
)
