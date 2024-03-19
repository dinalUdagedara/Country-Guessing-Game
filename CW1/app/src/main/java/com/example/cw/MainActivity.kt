package com.example.cw


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cw.ui.theme.CwTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CwTheme {

                val expanded = remember { mutableStateOf(false) }
                val extraPadding = if (expanded.value) 48.dp else 0.dp

                var timerSwitch =  remember { mutableStateOf(false) }

                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp)
                            ,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,


                            ) {

                            Switch(
                                checked = timerSwitch.value,
                                onCheckedChange = { isChecked ->
                                    timerSwitch.value = isChecked

                                    Log.d("timerSwitch","${timerSwitch.value}")
                                },
                                modifier = Modifier.padding(16.dp) // Add padding for spacing
                            )
                            Surface(


                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colors.surface,
                                elevation = 4.dp,
                                border = BorderStroke(1.dp, Color.Gray)
                            ){

                            Row(
                                modifier = Modifier
                                    .padding(16.dp)) {

                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(25.dp)



                                ) {
                                    Text(text = "Guess The Country")
                                }
                                Button(onClick = {
                                    val navigate = Intent(this@MainActivity, GuessCountry::class.java)
                                    navigate.putExtra("Timer",timerSwitch.value)
                                    startActivity(navigate)
                                },
                                    modifier = Modifier.padding(10.dp),
                                    elevation = ButtonDefaults.elevation(8.dp),
                                    shape = RoundedCornerShape(50), // Adjust the corner radius as needed
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = MaterialTheme.colors.primary,
                                        contentColor = MaterialTheme.colors.onPrimary
                                )
                                        ){
                                    Text(text = "Start",
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
                                }


                            }
                        }

                            Surface(

                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colors.surface,
                                elevation = 4.dp,
                                border = BorderStroke(1.dp, Color.Gray)
                            ){

                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)) {
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(25.dp)
                                    ) {
                                        Text(text = "Guess-Hints")
                                    }
                                    Button(onClick = {
                                        val navigate = Intent(this@MainActivity, GuessHints::class.java)
                                        navigate.putExtra("Timer",timerSwitch.value)
                                        startActivity(navigate)
                                    },
                                        modifier = Modifier.padding(10.dp),
                                        elevation = ButtonDefaults.elevation(8.dp),
                                        shape = RoundedCornerShape(50), // Adjust the corner radius as needed
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = MaterialTheme.colors.primary,
                                            contentColor = MaterialTheme.colors.onPrimary
                                        )
                                    ){
                                        Text(text = "Start",
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
                                    }


                                }
                            }


                            Surface(


                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colors.surface,
                                elevation = 4.dp,
                                border = BorderStroke(1.dp, Color.Gray)
                            ){

                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)) {
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(25.dp)
                                    ) {
                                        Text(text = "Guess the Flag")
                                    }
                                    Button(onClick = {
                                        val navigate = Intent(this@MainActivity, GuessFlag::class.java)
                                        navigate.putExtra("Timer",timerSwitch.value)
                                        startActivity(navigate)
                                    },
                                        modifier = Modifier.padding(10.dp),
                                        elevation = ButtonDefaults.elevation(8.dp),
                                        shape = RoundedCornerShape(50), // Adjust the corner radius as needed
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = MaterialTheme.colors.primary,
                                            contentColor = MaterialTheme.colors.onPrimary
                                        )
                                    ){
                                        Text(text = "Start",
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
                                    }


                                }
                            }


                            Surface(

                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colors.surface,
                                elevation = 4.dp,
                                border = BorderStroke(1.dp, Color.Gray)
                            ){

                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)) {
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(25.dp)
                                    ) {
                                        Text(text = "Advanced Level")
                                    }
                                    Button(onClick = {
                                        val navigate = Intent(this@MainActivity, AdvancedLevel::class.java)
                                        navigate.putExtra("Timer",timerSwitch.value)
                                        startActivity(navigate)
                                    },
                                        modifier = Modifier.padding(10.dp),
                                        elevation = ButtonDefaults.elevation(8.dp),
                                        shape = RoundedCornerShape(50), // Adjust the corner radius as needed
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = MaterialTheme.colors.primary,
                                            contentColor = MaterialTheme.colors.onPrimary
                                        )
                                    ){
                                        Text(text = "Start",
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
                                    }


                                }
                            }



                        }

                }
            }
        }
    }
}



