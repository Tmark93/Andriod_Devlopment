package com.example.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(navController: NavController){
    var newNote by remember { mutableStateOf(0) }
    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                TextInputTopBar()
            },
            navigationIcon = {
                IconButton(onClick = {navController.navigateUp()
                }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            }
        )
        TextInputCenter()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputTopBar(){
    var text by rememberSaveable {
        mutableStateOf("")
    }
    TextField(value = text,
        onValueChange = {
            text = it
        },
        label = { Text(text = "Enter Label") },
        placeholder = { Text(text = "PlaceHolder")},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputCenter(){
    var text by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(value = text,
        onValueChange = {
            text = it },
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}