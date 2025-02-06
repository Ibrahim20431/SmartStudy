package com.example.onlineshop.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDetailsTopBar(
    title: String,
    onBackClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onEditeClicked: () -> Unit,
) {

    LargeTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        title = {
            Text(
                text = title    ,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        actions = {
            IconButton(onClick = onDeleteClicked) {
                Icon(Icons.Default.Delete, contentDescription = "")
            }
            IconButton(onClick = onEditeClicked) {
                Icon(Icons.Default.Edit, contentDescription = "")
            }
        }
    )

}