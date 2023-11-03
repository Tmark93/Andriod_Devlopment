package com.example.notes


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen() {
    var newNote by remember { mutableStateOf(0) }

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
            FloatingActionButton(onClick = {  newNote++ }) {
                Icon(Icons.Default.Add, contentDescription = "New Note")
            }
        }
    ) { innerPadding -> ScrollContent(innerPadding) }
}






