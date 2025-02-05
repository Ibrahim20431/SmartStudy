package com.example.onlineshop.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.onlineshop.domain.model.Subject
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun AddSubjectDialog(
    isOpen: Boolean,
    title : String = "Add Subject",
    sujectName : String,
    goalHours : String,
    selectColors: List<Color>,
    onColorChange: (List<Color>) -> Unit,
    onSubjectNameChange: (String) -> Unit,
    onGoalHoursChange: (String) -> Unit,
    onDismissRequest : () -> Unit,
    onConfirmButtonClicked : () -> Unit,
) {

    var subjectNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var goalHoursError by rememberSaveable { mutableStateOf<String?>(null) }

    subjectNameError = when{
        sujectName.isBlank() -> "Please Enter Subject Name."
        sujectName.length < 2 -> "Subject Name is too Short."
        sujectName.length > 20 -> "Subject Name is too Long."
        else -> null
    }

    goalHoursError = when{
        goalHours.isBlank() -> "Please Enter Goal Study Hours."
        goalHours.toFloatOrNull() == null -> "Invalid Number."
        goalHours.toFloat() < 1f -> "Please Enter at least 1 hour."
        goalHours.toFloat() > 1000f -> "Please set a maximum 1000 hour."
        else -> null
    }

    if (isOpen){
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title) },
            text = {
                Column (modifier = Modifier.fillMaxWidth()) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Subject.subjectsCardColors.forEach { colors ->
                            Box (
                                modifier = Modifier
                                    .size(if (colors == selectColors) 35.dp else 24.dp)
                                    .clip(CircleShape)
                                    .background(brush = Brush.verticalGradient(colors))
                                    .border(
                                        width = 2.dp,
                                        color = if (colors == selectColors) Color.Yellow
                                                else Color.Transparent,
                                        shape = CircleShape )
                                    .clickable {
                                        onColorChange(colors)
                                    }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = sujectName,
                        onValueChange = onSubjectNameChange,
                        label = { Text(text = "Subject Name") },
                        singleLine = true,
                        isError = subjectNameError != null && sujectName.isNotBlank(),
                        supportingText = { Text(text = subjectNameError.orEmpty()) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = goalHours,
                        onValueChange = onGoalHoursChange,
                        label = { Text(text = "Goal Study Hours") },
                        singleLine = true,
                        isError = goalHoursError != null && goalHours.isNotBlank(),
                        supportingText = { Text(text = goalHoursError.orEmpty()) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmButtonClicked,
                    enabled = subjectNameError == null && goalHoursError == null
                ) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}