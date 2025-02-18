package com.example.onlineshop.presentation.features.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onlineshop.presentation.components.DeleteDialog
import com.example.onlineshop.presentation.components.SubjectListBottomSheet
import com.example.onlineshop.presentation.components.TaskCheckBox
import com.example.onlineshop.presentation.components.TaskDatePicker
import com.example.onlineshop.subjectList
import com.example.onlineshop.util.Priority
import com.example.onlineshop.util.convertMillisDatetoString
import com.google.firebase.annotations.concurrent.Background
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TaskScreen() {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDatePickerOpen by rememberSaveable { mutableStateOf(false) }
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    val dataPickerState = rememberDatePickerState(initialSelectedDateMillis = Instant.now().toEpochMilli())
    val sheetState = rememberModalBottomSheetState()

    var taskTitleError by rememberSaveable { mutableStateOf<String?>(null) }
    taskTitleError = when {
        title.isBlank() -> "Please Enter Task Title."
        title.length < 4 -> "Task title is too short."
        title.length > 30 -> "Task title is too Long."
        else -> null
    }

    var taskDescriptionError by rememberSaveable { mutableStateOf<String?>(null) }
    taskDescriptionError = when {
        description.isBlank() -> "Please Enter Task Description."

        else -> null
    }

    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Task",
        bodyText = "Are you sure, you want to delete this task ?" +
                "this action can not be undone.",
        onDismissRequest = {isDeleteDialogOpen = false},
        onConfirmButtonClicked = {isDeleteDialogOpen = false}
    )

    TaskDatePicker(
        state = dataPickerState,
        isOpen = isDatePickerOpen,
        onDismissReqest = { isDatePickerOpen = false },
        onComfirmClicked = { isDatePickerOpen = false }
    )

    SubjectListBottomSheet(
        sheetState = sheetState,
        isOpen = isBottomSheetOpen,
        subjects = subjectList,
        onDismissRequest = { isBottomSheetOpen = false},
        onSubjectClicked = {}
    )

    Scaffold (
        topBar = {
            TaskScreenTopBar(
            isTaskExist = true,
            isComplete = false,
            checkboxBorderColors = Color.Red,
            onBackButtonClick = {},
            onDeleteButtonClick = {isDeleteDialogOpen = true},
            onCheckboxClick = {}
        )}
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                singleLine = true,
                isError = taskTitleError != null && title.isNotBlank(),
                supportingText = { Text(text = taskTitleError.orEmpty()) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") },
                isError = taskDescriptionError != null && description.isNotBlank(),
                supportingText = { Text(text = taskDescriptionError.orEmpty()) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Dio Date",
                style = MaterialTheme.typography.bodySmall
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = dataPickerState.selectedDateMillis.convertMillisDatetoString(),
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = { isDatePickerOpen = true }) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Priority",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row (modifier = Modifier.fillMaxWidth()){
                Priority.entries.forEach{ priority ->
                    PriorityButton(
                        modifier = Modifier.weight(1f),
                        label = priority.title,
                        backgroundColor = priority.color,
                        borderColor = if (priority == Priority.MEDIUM) Color.White else Color.Transparent,
                        labelColor = if (priority == Priority.MEDIUM) Color.White else Color.White.copy(alpha = 0.7f),
                        onClick = {}
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Related to Subject",
                style = MaterialTheme.typography.bodySmall
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "English",
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = { isBottomSheetOpen = true }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }
            }

            Button(
                enabled = taskTitleError == null,
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
            ) {
                Text(text = "Save")
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreenTopBar(
    isTaskExist : Boolean,
    isComplete : Boolean,
    checkboxBorderColors: Color,
    onBackButtonClick : () -> Unit,
    onDeleteButtonClick : () -> Unit,
    onCheckboxClick : () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        title = { Text(text = "Task") },
        actions = {
            if (isTaskExist) {
                TaskCheckBox(
                    isComplete = isComplete,
                    borderColor = checkboxBorderColors,
                    onCheckBoxClicked = onCheckboxClick
                )
                IconButton(onClick = onDeleteButtonClick) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                }
            }
        }
    )

}

@Composable
fun PriorityButton(
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color,
    borderColor : Color,
    labelColor: Color,
    onClick: () -> Unit
) {

    Box (
        modifier = modifier
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(5.dp)
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(5.dp))
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = label,
            color = labelColor
        )
    }
    
}