package com.example.notes


import android.widget.Button
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(navController: NavController,
                 notesSaved: (Notes) -> Unit,
                 notesDeleted: (Notes) -> Unit,
                 noteId: UUID?,
                 noteList: List<Notes>
) {
    val newNote = noteId == null
    val noteToEdit = noteList.find { it.id == noteId }
    var title by rememberSaveable { mutableStateOf(noteToEdit?.title ?: "") }
    var text by rememberSaveable { mutableStateOf(noteToEdit?.text ?: "") }

    Column(Modifier.fillMaxSize()) {
        Scaffold (
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        TextInputTopBar(title) { title = it }
                    },
                    navigationIcon = {
                        if (noteId != null) {
                            IconButton(onClick = {
                                noteToEdit?.let { notesDeleted(it)}
                                navController.navigateUp()
                            }) {
                                Icon(Icons.Filled.Delete, "delete")
                            }
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val note = if (newNote) {
                        Notes(id = UUID.randomUUID(), title = title, text = text)
                    } else {
                        Notes(id = noteId!!, title = title, text = text)
                    }
                    notesSaved(note)
                    navController.navigateUp()
                }) {
                    Icon(Icons.Default.Save, contentDescription = "New Note")
                }
            }
        ) {innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                TextInputCenter(text = text, textChange = {text = it})
            }
        }
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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}