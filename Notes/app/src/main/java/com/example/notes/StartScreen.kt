package com.example.notes


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavController, note: List<Notes>,
                savedNotes: () -> Unit){

    Column(Modifier.fillMaxSize()) {
        Scaffold (
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Notes")
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = savedNotes) {
                    Icon(Icons.Default.Create, contentDescription = "New Note")
                }
            }
        ) {innerPadding ->
            MyApp(modifier = Modifier.padding(innerPadding), note = note,
                navController = navController)
    }
    }
}

@Composable
fun DisplaySavedNotes(notes: Notes,
                      editNote: (Notes) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val extraPadding = if (expanded) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = notes.title)
                if (expanded) {
                        Text(text = notes.text)
                        Button(onClick = { editNote(notes) }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit Note")
                    }
                    }
                }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandMore
                    else Icons.Filled.ExpandLess,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
            }
        }
    }


@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    note: List<Notes>,
    navController: NavController
) {
    LazyColumn(modifier) {
        items(items = note){ notes ->
            DisplaySavedNotes(notes = notes, editNote = { selectedNote ->
            navController.navigate("${Screen.UpdateScreen.route}/${selectedNote.id}")
            })
        }
    }
}




