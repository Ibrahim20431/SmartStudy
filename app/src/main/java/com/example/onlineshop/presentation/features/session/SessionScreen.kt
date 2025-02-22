package com.example.onlineshop.presentation.features.session

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshop.presentation.components.DeleteDialog
import com.example.onlineshop.presentation.components.SubjectListBottomSheet
import com.example.onlineshop.presentation.components.studySessionsList
import com.example.onlineshop.sessions
import com.example.onlineshop.subjectList
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SessionScreenRoute(navigator: DestinationsNavigator) {
    SessionScreen(onBackButtonClick = {navigator.navigateUp()})
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
private fun SessionScreen(
    onBackButtonClick: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }

    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Sessions",
        bodyText = "Are you sure, you want to delete this Session ?" +
                "this action can not be undone.",
        onDismissRequest = {isDeleteDialogOpen = false},
        onConfirmButtonClicked = {isDeleteDialogOpen = false}
    )

    SubjectListBottomSheet(
        sheetState = sheetState,
        isOpen = isBottomSheetOpen,
        subjects = subjectList,
        onDismissRequest = { isBottomSheetOpen = false},
        onSubjectClicked = {}
    )

    Scaffold (
        topBar = {SessionTopBar(onBackIconClick = onBackButtonClick)}
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues
        ) {
            item {
                TimerSection(modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f))
            }

            item {
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
            }

            item {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = {}) { Text(text = "Cancel", modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) }
                    Button(onClick = {}) { Text(text = "Start", modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) }
                    Button(onClick = {}) { Text(text = "Finish", modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) }
                }
            }

            studySessionsList(
                sectionTitle = "STUDY SESSIONS HISTORY",
                emptyListText = "You don't have any upcoming tasks.\nplease, Click on + to add new subject",
                sessions = sessions,
                onDeleteIconClick = { isDeleteDialogOpen = true }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionTopBar(
    onBackIconClick : () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackIconClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        title = { Text(text = "Study Session", style = MaterialTheme.typography.headlineSmall) }
    )
}

@Composable
fun TimerSection(
    modifier: Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(250.dp)
                .border(5.dp, color = MaterialTheme.colorScheme.surfaceVariant, shape = CircleShape)
        ){
            Text(
                text = "00:10:02",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 45.sp)
            )
        }
    }
}