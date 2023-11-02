package com.example.notes


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(navController: NavController,
                 notesSaved: (Notes) -> Unit,
                 noteId: UUID?
) {
    val newNote = noteId == null
    var title by rememberSaveable { mutableStateOf("") }
    var text by rememberSaveable { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                TextInputTopBar(title) {title = it}
            },
            navigationIcon = {
                IconButton(onClick = {
                    val note = if (newNote){
                        Notes(id = UUID.randomUUID(), title = title, text = text)
                    } else {
                        Notes(id = noteId!!, title = title, text = text)
                    }
                    notesSaved(note)
                    navController.navigateUp()
                }) {
                    Icon(Icons.Filled.Save, "saveIcon")
                }
            }
        )
        TextInputCenter(text) {text = it}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputTopBar(title: String, textChange: (String) -> Unit){
    TextField( value = title,
        onValueChange = textChange,
        label = { Text(text = "Enter Title") },
        placeholder = { Text(text = "PlaceHolder")},
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputCenter(text: String, textChange: (String) -> Unit){
    OutlinedTextField(value = text,
        onValueChange = textChange,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}