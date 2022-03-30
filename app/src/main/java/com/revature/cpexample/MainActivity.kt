package com.revature.cpexample

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

@SuppressLint("Range")
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

            val values= ContentValues()

            values.put(RevatureCP.name,name)

            context.contentResolver.insert(RevatureCP.CONTENT_URI,values)

            Toast.makeText(context,"New Record is Inserted",Toast.LENGTH_LONG).show()

        }) {


            Text(text = "Add New Record")
        }

        Button(onClick = {

            val cursor=context.contentResolver.query(RevatureCP.CONTENT_URI,null,null,null,null)

             if(cursor!!.moveToFirst())
             {
                 val strBuild=StringBuilder()
                 while(!cursor.isAfterLast)
                 {
                     result="${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}"
                     cursor.moveToNext()
                 }
                 Log.d("Data","$result")
             }
            else
             {
                 Log.d("Data","No Records found")
             }



        }) {


            Text(text = "Show Records")
        }


    }
}
