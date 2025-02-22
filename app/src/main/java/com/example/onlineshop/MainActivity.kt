package com.example.onlineshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.onlineshop.domain.model.Session
import com.example.onlineshop.domain.model.Subject
import com.example.onlineshop.domain.model.Task
import com.example.onlineshop.presentation.features.NavGraphs
import com.example.onlineshop.presentation.ui.theme.OnlineShopTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.spec.NavGraphSpec

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineShopTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}


val subjectList : List<Subject> = listOf(
    Subject(id = 1,"Maths", 12f , Subject.subjectsCardColors[0]),
    Subject(id = 2,"Science", 15f , Subject.subjectsCardColors[1]),
    Subject(id = 3,"Physics", 13f , Subject.subjectsCardColors[2]),
    Subject(id = 4,"English", 13f , Subject.subjectsCardColors[3]),
    Subject(id = 5,"Arabic", 13f , Subject.subjectsCardColors[4])
)
val tasks: List<Task> = listOf(
    Task(id = 1,title = "Prepare notes", description="", dueDate = 0L, priority = 1, relatedToSubject = "", isComplete = false, taskSubjectId = 1),
    Task(id = 1,title = "Do HomeWork", description="", dueDate = 0L, priority = 2, relatedToSubject = "", isComplete = true, taskSubjectId = 3),
    Task(id = 1,title = "Go Coashing", description="", dueDate = 0L, priority = 0, relatedToSubject = "", isComplete = false, taskSubjectId = 1),
    Task(id = 1,title = "Assignment", description="", dueDate = 0L, priority = 2, relatedToSubject = "", isComplete = false, taskSubjectId = 2),
    Task(id = 1,title = "Write poam", description="", dueDate = 0L, priority = 1, relatedToSubject = "", isComplete = true, taskSubjectId = 2),
)
val sessions : List<Session> = listOf(
    Session(sessionId = 0, relatedtToSubject = "English", sessionSubjectId = 0, date = 0L, duration = 2L),
    Session(sessionId = 1, relatedtToSubject = "Science", sessionSubjectId = 0, date = 0L, duration = 2L),
    Session(sessionId = 2, relatedtToSubject = "Physics", sessionSubjectId = 0, date = 0L, duration = 2L),
    Session(sessionId = 3, relatedtToSubject = "Maths", sessionSubjectId = 0, date = 0L, duration = 2L),
    Session(sessionId = 4, relatedtToSubject = "Arabic", sessionSubjectId = 0, date = 0L, duration = 2L),
)