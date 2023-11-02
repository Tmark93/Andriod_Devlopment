package com.example.notes

sealed class Screen(val route: String){
    object StartScreen : Screen("startScreen")
    object UpdateScreen : Screen("updateScreen")
}
