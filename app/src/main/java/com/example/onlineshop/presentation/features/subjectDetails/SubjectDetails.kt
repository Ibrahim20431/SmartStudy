package com.example.onlineshop.presentation.features.subjectDetails

import android.annotation.SuppressLint
import android.content.ClipData.Item
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.onlineshop.domain.model.Session
import com.example.onlineshop.domain.model.Task
import com.example.onlineshop.presentation.components.CountCard
import com.example.onlineshop.presentation.components.DeleteDialog
import com.example.onlineshop.presentation.components.SubjectDetailsTopBar
import com.example.onlineshop.presentation.components.studySessionsList
import com.example.onlineshop.presentation.components.tasksList
import com.example.onlineshop.sessions
import com.example.onlineshop.tasks

@Composable
fun SubjectDetails() {

    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }

    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Session ???!",
        bodyText = "Are you sure you want to delete this Session ??\nYour studied hours will be reduced",
        onConfirmButtonClicked = {isDeleteDialogOpen = false},
        onDismissRequest = {isDeleteDialogOpen = false},
    )

    Scaffold(
        topBar = {
            SubjectDetailsTopBar(
                title = "English",
                onBackClicked = {},
                onDeleteClicked = {},
                onEditeClicked = {}
            )
        }
    ) { paddingValues ->

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SubjectOverviewSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    goalHours = "15",
                    studiedHours = "10",
                    progress = 0.75f
                )
            }

            tasksList(
                sectionTitle = "UPCOMING TASKS",
                emptyListText = "You don't have any upcoming tasks.\nplease, Click on + to add new subject",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardList = {}
            )

            item { Spacer(modifier = Modifier.height(20.dp)) }

            tasksList(
                sectionTitle = "COMPLETED TASKS",
                emptyListText = "You don't have any completed tasks.\nplease, Click the check box of task",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardList = {}
            )

            item { Spacer(modifier = Modifier.height(20.dp)) }

            studySessionsList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyListText = "You don't have any upcoming tasks.\nplease, Click on + to add new subject",
                sessions = sessions,
                onDeleteIconClick = {isDeleteDialogOpen = true}
            )
        }

    }
}


@SuppressLint("RememberReturnType")
@Composable
private fun SubjectOverviewSection(
    modifier: Modifier,
    goalHours: String,
    studiedHours: String,
    progress: Float
) {

    val percentageProgress = remember(progress) {
        (progress*100).toInt().coerceIn(0, 100)
    }

    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        CountCard(
            modifier = Modifier.weight(1f),
            headingTitle = "Goal Study hours",
            count = goalHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingTitle = "Study hours",
            count = studiedHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box (
            modifier = Modifier.size(75.dp),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = 1f,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = progress,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
            )
            Text(text = "$percentageProgress%")
        }
    }
}