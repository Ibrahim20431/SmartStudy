package com.example.onlineshop.presentation.features.subjectDetails

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onlineshop.domain.model.Subject
import com.example.onlineshop.presentation.components.AddSubjectDialog
import com.example.onlineshop.presentation.components.CountCard
import com.example.onlineshop.presentation.components.DeleteDialog
import com.example.onlineshop.presentation.components.SubjectDetailsTopBar
import com.example.onlineshop.presentation.components.studySessionsList
import com.example.onlineshop.presentation.components.tasksList
import com.example.onlineshop.sessions
import com.example.onlineshop.tasks

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SubjectDetails() {

    var scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()
    val isFABExtended by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }

    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Session ???!",
        bodyText = "Are you sure you want to delete this Session ??\nYour studied hours will be reduced",
        onConfirmButtonClicked = {isDeleteDialogOpen = false},
        onDismissRequest = {isDeleteDialogOpen = false},
    )
    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Subject ???!",
        bodyText = "Are you sure you want to delete this Subject ??\nYour studied hours will be reduced",
        onConfirmButtonClicked = {isDeleteDialogOpen = false},
        onDismissRequest = {isDeleteDialogOpen = false},
    )

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by rememberSaveable { mutableStateOf("") }
    var goalHours by rememberSaveable { mutableStateOf("") }
    var selectColors by rememberSaveable { mutableStateOf(Subject.subjectsCardColors.random()) }

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

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SubjectDetailsTopBar(
                title = "English",
                onBackClicked = {},
                onDeleteClicked = {},
                onEditeClicked = {},
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {  },
                icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                text = { Text(text = "Add task") },
                expanded = isFABExtended
            )
        }
    ) { paddingValues ->

        LazyColumn (
            state = listState,
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