package com.example.onlineshop.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteDialog(
    isOpen: Boolean,
    title: String,
    bodyText: String,
    onDismissRequest : () -> Unit,
    onConfirmButtonClicked : () -> Unit,
) {

    if (isOpen){
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title) },
            text = {
                Text(text = bodyText)
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmButtonClicked,
                ) {
                    Text(text = "Delete")
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