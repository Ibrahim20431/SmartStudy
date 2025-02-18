package com.example.onlineshop.presentation.components

import android.app.DatePickerDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDatePicker(
    state : DatePickerState,
    isOpen: Boolean,
    confirmButtonText: String = "Ok",
    DismissButtonText: String = "Cancel",
    onDismissReqest : () -> Unit,
    onComfirmClicked : () -> Unit,
) {
    if (isOpen) {
        DatePickerDialog(
            onDismissRequest = onDismissReqest,
            confirmButton = {
                TextButton(onClick = onComfirmClicked) {
                    Text(text = confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissReqest) {
                    Text(text = DismissButtonText)
                }
            }
        ) {
            DatePicker(state = state)
        }
    }
}