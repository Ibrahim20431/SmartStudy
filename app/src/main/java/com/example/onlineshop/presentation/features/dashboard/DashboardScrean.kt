package com.example.onlineshop.presentation.features.dashboard

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onlineshop.R
import com.example.onlineshop.domain.model.Session
import com.example.onlineshop.presentation.components.CountCard
import com.example.onlineshop.presentation.components.DashboardScreenTopBar
import com.example.onlineshop.domain.model.Subject
import com.example.onlineshop.domain.model.Task
import com.example.onlineshop.presentation.components.AddSubjectDialog
import com.example.onlineshop.presentation.components.SubjectCard
import com.example.onlineshop.presentation.components.studySessionsList
import com.example.onlineshop.presentation.components.tasksList

@Preview(showBackground = true)
@Composable
fun DashboardScrean() {

    val subjectList : List<Subject> = listOf(
        Subject(id = 1,"Maths", 12f , Subject.subjectsCardColors[0]),
        Subject(id = 2,"Science", 15f , Subject.subjectsCardColors[1]),
        Subject(id = 3,"Physics", 13f , Subject.subjectsCardColors[2]),
        Subject(id = 4,"English", 13f , Subject.subjectsCardColors[3]),
        Subject(id = 5,"Arabic", 13f , Subject.subjectsCardColors[4])
    )
    val tasksList: List<Task> = listOf(
        Task(id = 1,title = "Prepare notes", description="", dueDate = 0L, priority = 1, relatedToSubject = "", isComplete = false, taskSubjectId = 1),
        Task(id = 1,title = "Do HomeWork", description="", dueDate = 0L, priority = 2, relatedToSubject = "", isComplete = true, taskSubjectId = 3),
        Task(id = 1,title = "Go Coashing", description="", dueDate = 0L, priority = 0, relatedToSubject = "", isComplete = false, taskSubjectId = 1),
        Task(id = 1,title = "Assignment", description="", dueDate = 0L, priority = 2, relatedToSubject = "", isComplete = false, taskSubjectId = 2),
        Task(id = 1,title = "Write poam", description="", dueDate = 0L, priority = 1, relatedToSubject = "", isComplete = true, taskSubjectId = 2),
    )
    val sessionsList : List<Session> = listOf(
        Session(sessionId = 0, relatedtToSubject = "English", sessionSubjectId = 0, date = 0L, duration = 2L),
        Session(sessionId = 1, relatedtToSubject = "Science", sessionSubjectId = 0, date = 0L, duration = 2L),
        Session(sessionId = 2, relatedtToSubject = "Physics", sessionSubjectId = 0, date = 0L, duration = 2L),
        Session(sessionId = 3, relatedtToSubject = "Maths", sessionSubjectId = 0, date = 0L, duration = 2L),
        Session(sessionId = 4, relatedtToSubject = "Arabic", sessionSubjectId = 0, date = 0L, duration = 2L),
    )

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by remember { mutableStateOf("") }
    var goalHours by remember { mutableStateOf("") }
    var selectColors by remember { mutableStateOf(Subject.subjectsCardColors.random()) }

    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        onDismissRequest = {isAddSubjectDialogOpen = false},
        onConfirmButtonClicked = {isAddSubjectDialogOpen = false},
        sujectName = subjectName,
        goalHours = goalHours,
        onSubjectNameChange = { subjectName = it },
        onGoalHoursChange = { goalHours = it },
        selectColors = selectColors,
        onColorChange = { selectColors = it },
    )

    Scaffold (
        topBar = { DashboardScreenTopBar() }
    ) { paddingValues ->

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = 6,
                    studiesHours = "16",
                    goalHours = "30"
                )
            }

            item {
                SubjectCardSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = subjectList,
                    onAddSubjectClicked = {isAddSubjectDialogOpen = true}
                )
            }

            item {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text = "Start Study Session")
                }
            }

            tasksList(
                sectionTitle = "UPCOMING TASKS",
                emptyListText = "You don't have any upcoming tasks.\nplease, Click on + to add new subject",
                tasks = tasksList,
                onCheckBoxClick = {},
                onTaskCardList = {}
            )

            item { Spacer(modifier = Modifier.height(20.dp)) }

            studySessionsList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyListText = "You don't have any upcoming tasks.\nplease, Click on + to add new subject",
                sessions = sessionsList,
                onDeleteIconClick = {}
            )

        }

    }

}


@Composable
fun CountCardSection(
    modifier: Modifier,
    subjectCount: Int,
    studiesHours: String,
    goalHours: String
) {

    Row (modifier = modifier) {

        CountCard(
            modifier = Modifier.weight(1f),
            headingTitle = "Subject Count",
            count = "$subjectCount"
        )

        Spacer(modifier = Modifier.width(10.dp))

        CountCard(
            modifier = Modifier.weight(1f),
            headingTitle = "Studies Hours",
            count = studiesHours
        )

        Spacer(modifier = Modifier.width(10.dp))

        CountCard(
            modifier = Modifier.weight(1f),
            headingTitle = "Goal Study Hours",
            count = goalHours
        )
    }
}

@Composable
fun SubjectCardSection(
    modifier: Modifier,
    subjectList: List<Subject>,
    onAddSubjectClicked : () -> Unit,
    emptyListText: String = "You don't have any subject.\nplease, Click on + to add new subject",
) {
    Column (modifier = modifier) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )

            IconButton(onClick = onAddSubjectClicked) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Subject"
                )
            }
        }

//        Spacer(modifier= Modifier.height(8.dp))

        //Empty subject message
        if (subjectList.isEmpty()){
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.img_books),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
        else{
            LazyRow (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(start = 6.dp, end = 6.dp)
            ) {
                items(subjectList){ subject ->
                    SubjectCard(
                        subjectName = subject.name,
                        gradientColor = subject.colors,
                        onClick =  {}
                    )
                }
            }
        }
    }

}

@Composable
fun RecentStudySection(
    modifier: Modifier = Modifier,
) {
    Column (modifier = modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 6.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "RECENT STUDY SECTION",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier= Modifier.height(12.dp))


        }
    }
}