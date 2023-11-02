package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.ui.theme.NotesTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Navigation()
                 }
              }
            }
        }
    }


@Composable
fun Navigation() {
    val navController = rememberNavController()
    var noteList by remember {
        mutableStateOf(listOf<Notes>())
    }

    NavHost(navController, startDestination = Screen.StartScreen.route) {

        composable(Screen.StartScreen.route){
            StartScreen(navController = navController,
                note = noteList,
                savedNotes = {
                    navController.navigate("${Screen.UpdateScreen.route}/null")}
            )

        }

        composable("${Screen.UpdateScreen.route}/{noteId}"){  backStackEntry ->
            val noteIdStr = backStackEntry.arguments?.getString("noteId")
            val noteId = if (noteIdStr == "null") null else noteIdStr?.let { UUID.fromString(it) }

            UpdateScreen(navController = navController,
                notesSaved = { updatedNote ->
                    if (noteId == null) {
                        noteList = noteList + updatedNote
                } else {
                    noteList = noteList.map {
                        if (it.id == updatedNote.id) updatedNote else it
                    }
                }
            }, noteId = noteId)
        }
    }
}


