package com.example.onlineshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.onlineshop.domain.model.Session
import com.example.onlineshop.domain.model.Subject
import com.example.onlineshop.domain.model.Task
import com.example.onlineshop.presentation.features.dashboard.DashboardScrean
import com.example.onlineshop.presentation.features.subjectDetails.SubjectDetails
import com.example.onlineshop.presentation.features.task.TaskScreen
import com.example.onlineshop.presentation.ui.theme.OnlineShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineShopTheme {
//                DashboardScrean()
//                SubjectDetails()
                TaskScreen()
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