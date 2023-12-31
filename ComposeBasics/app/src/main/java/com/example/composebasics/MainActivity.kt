package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(){
                MyScreenContent()
            }
            }
        }
    }


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Surface(color = Color.Yellow) {
//        Text(text = "Hello $name!",
//            modifier = modifier.padding(16.dp)
//                .background(color = Color.Magenta)
//        )
//    }
//}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBasicsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) {"Hello Andriod $it"}){
    var counterState by remember {
        mutableStateOf(0)
    }
    Column (modifier = Modifier.fillMaxHeight()){
        NamesList(names = names, modifier = Modifier.weight(1f))
        Counter(
            count = counterState,
            updateCount = { newCount ->
                counterState = newCount
            }
        )
        if (counterState > 5){
            Text(text = "I love to count!")
        }
    }
}

@Composable
fun NamesList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names){
            Greeting(name = it)
            Divider()
        }

    }
}

    @Composable
    fun Counter(count: Int, updateCount: (Int) -> Unit) {
        Button(onClick = { updateCount(count + 1) }) {
            Text(text = "I´ve been clicked $count times")
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(
            text = "Hello $name!",
            modifier = Modifier.padding(16.dp)
        )
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ComposeBasicsTheme {
            MyApp {
                MyScreenContent()
            }
        }
    }

