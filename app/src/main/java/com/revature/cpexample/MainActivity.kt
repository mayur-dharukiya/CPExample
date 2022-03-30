package com.revature.cpexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.revature.cpexample.ui.theme.CPExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CPExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                      UI()
                }
            }
        }
    }
}

@Composable
fun UI()
{


    var context= LocalContext.current
    Column() {
        var name by remember { mutableStateOf("") }
        var result  by remember{ mutableStateOf("")}

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Button(onClick = {


        }) {


            Text(text = "Add New Record")
        }

        Button(onClick = {




        }) {


            Text(text = "Show Records")
        }


    }
}
