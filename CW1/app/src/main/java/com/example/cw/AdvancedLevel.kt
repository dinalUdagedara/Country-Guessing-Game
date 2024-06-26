package com.example.cw

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.cw.ui.theme.CwTheme
import kotlinx.coroutines.delay
import kotlinx.serialization.descriptors.PrimitiveKind
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class AdvancedLevel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CwTheme {
                val timerSwitch = intent.getBooleanExtra("Timer",false)
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFFFFFFF)),

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                    var showDialog  by rememberSaveable  { mutableStateOf(false) }
                    val (randomCountryKey, randomFlagName, imageIdAndNameList) = rememberRandomIndicesAndFlag()
                    var currentRandomIndices by rememberSaveable  { mutableStateOf(listOf(0,1,2)) }
                    AdvancedLevelContent(
                        showDialog = showDialog,
                        onShowDialogChange = { showDialog = it },
                        randomFlagName = randomFlagName,
                        imageIdAndNameList = imageIdAndNameList,
                        onNextButtonClick = {
                            currentRandomIndices = List(3) { Random.nextInt(imageIdAndNameList.size) }

                        },
                        currentRandomIndices = currentRandomIndices,
                        timerSwitch

                    )
                    Log.d("setContent Running","Running")
                }
            }
        }
    }
}




@Composable
fun AdvancedLevelContent(
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit,
    randomFlagName: String,
    imageIdAndNameList: List<Pair<Int, String>>,
    onNextButtonClick: () -> Unit ,// Callback for handling Next button click
    currentRandomIndices:List<Int>,
    timerSwitch : Boolean

) {
//    TimerBegin(
//        onFinish = {
//            onShowDialogChange(true)
//        }
//    )


    Log.d("Timer Switch","$timerSwitch")

    Log.d(" GuessFlagContent","This is Working")
    val context = LocalContext.current

    var textFieldValue_1 by rememberSaveable { mutableStateOf("") }
    var textFieldValue_2 by rememberSaveable { mutableStateOf("") }
    var textFieldValue_3 by rememberSaveable { mutableStateOf("") }

    var submitCount by rememberSaveable { mutableStateOf(0) }

    // Define a variable to control the enabled state of the text fields
    var isFieldsEnabled by rememberSaveable { mutableStateOf(true) }



    var textB_1 by rememberSaveable { mutableStateOf(false) }


    var textB_2 by rememberSaveable { mutableStateOf(false) }


    var textB_3 by rememberSaveable { mutableStateOf(false) }

    var allCorrect by rememberSaveable { mutableStateOf(false) }


    var textSubmitted by rememberSaveable { mutableStateOf(false) }
    var submitted by rememberSaveable { mutableStateOf(false) } // State to track if the user has submitted their guess
    var nextClicked by rememberSaveable { mutableStateOf(false) }

    var marks by rememberSaveable  { mutableStateOf(0) }
    var attempts by rememberSaveable  { mutableStateOf(0) }
    var wrongCounts by rememberSaveable  { mutableStateOf(0) }

    val (imageId_1,flagName_1) = imageIdAndNameList[currentRandomIndices[0]]
    val (imageId_2,flagName_2) = imageIdAndNameList[currentRandomIndices[1]]
    val (imageId_3,flagName_3) = imageIdAndNameList[currentRandomIndices[2]]





    if (timerSwitch){
        TimerBegin(
            onFinish = {
                submitted = true

                if (nextClicked ){
                    Log.d("Timer If","Working")
                    textSubmitted = false

                    isFieldsEnabled = true

                    onNextButtonClick()

                    allCorrect = false


                    textB_1 = false
                    textB_2 = false
                    textB_3 = false

                    textFieldValue_1 = ""
                    textFieldValue_2 = ""
                    textFieldValue_3 = ""

                    Log.d("Next Button","Working")
                    Log.d("NextButtonToggled", "Next button clicked")
                    nextClicked = false

                }
                else{
                    Log.d("Else Timer","Working")

                    textSubmitted = true
                    attempts += 3

                    allCorrect = flagName_1 == textFieldValue_1 && flagName_2 == textFieldValue_2 && flagName_3 == textFieldValue_3


                    submitCount = 1
                    Log.d("ButtonClick","correct: $randomFlagName selected: $flagName_1")
                    if (allCorrect){
                        Log.d("Submit","All Correct Working")
                        isFieldsEnabled = false
                        wrongCounts = 0
                    }else{
                        wrongCounts++
                        Log.d("wrong Count","Count:$wrongCounts")
                    }
                    if (flagName_1 == textFieldValue_1){
                        Log.d("Answer 1 ","Correct")
                        textB_1 = true
                        marks++
                    }
                    if (flagName_2 == textFieldValue_2){
                        Log.d("Answer 2","Correct")
                        textB_2 = true
                        marks++
                    }
                    if (flagName_3 == textFieldValue_3){
                        Log.d("Answer 3","Correct")
                        textB_3 = true
                        marks++
                    }
                    onShowDialogChange (true)
                    Log.d("SubmitButtonToggled", "Submit button clicked")


                }
            }
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Guess the Flag!")
                Text(text = "$marks/$attempts")

//  Flag 1

                FlagButton(
                    imageId = imageId_1,
                    flagName = flagName_1 ,
                    onShowDialogChange = onShowDialogChange)

                TextField(value = textFieldValue_1,
                    onValueChange = {
                            textFieldValue_1 = it
                    },
                    enabled = !textB_1,

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = if (textB_1) Color.Gray else Color.White, // Change the background color based on the enabled state
                        textColor = (if (textB_1 && textSubmitted){Color.Green} else if (!textB_1 && textSubmitted) {Color.Red} else {
                            Color.Black
                        })
                    ),
                    )


//  Flag 2

                FlagButton(
                    imageId = imageId_2,
                    flagName = flagName_2 ,
                    onShowDialogChange = onShowDialogChange)

                TextField(value = textFieldValue_2,
                    onValueChange = {
                            textFieldValue_2 = it
                    } ,
                    enabled = !textB_2,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = if (textB_2) Color.Gray else Color.White, // Change the background color based on the enabled state
                        textColor = (if (textB_2 && textSubmitted){Color.Green} else if (!textB_2 && textSubmitted) {Color.Red} else {
                            Color.Black
                        })
                    ),
                )



//  Flag 3

                FlagButton(
                    imageId = imageId_3,
                    flagName = flagName_3 ,
                    onShowDialogChange = onShowDialogChange)

                TextField(value = textFieldValue_3,
                    onValueChange = {
                            textFieldValue_3 = it
                    },
                    enabled = !textB_3,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = if (textB_3) Color.Gray else Color.White, // Change the background color based on the enabled state
                        textColor = (if (textB_3 && textSubmitted){Color.Green} else if (!textB_3 && textSubmitted) {Color.Red} else {
                            Color.Black
                        })
                    ),
                    )



                // Display the correct Flag Name
                if (randomFlagName != null) {
                    Text(text = "1: $flagName_1, 2: $flagName_2, 3: $flagName_3")
                }

                Log.d("Next button Selection ","OutSide")
                if (wrongCounts >= 3 || allCorrect){
                    nextClicked = true
                    wrongCounts = 0
                    Log.d("Next button Selection ","Working")
                }


                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = {



                        submitted = true

//                        nextClicked = !nextClicked

                        if (nextClicked ){
                            textSubmitted = false

                            isFieldsEnabled = true

                            onNextButtonClick()

                            allCorrect = false


                            textB_1 = false
                            textB_2 = false
                            textB_3 = false

                            textFieldValue_1 = ""
                            textFieldValue_2 = ""
                            textFieldValue_3 = ""

                            Log.d("Next Button","Working")
                            Log.d("NextButtonToggled", "Next button clicked")
                            nextClicked = false

                        }
                        else{

                            textSubmitted = true
                            attempts += 3

                            allCorrect = flagName_1 == textFieldValue_1 && flagName_2 == textFieldValue_2 && flagName_3 == textFieldValue_3


                            submitCount = 1
                            Log.d("ButtonClick","correct: $randomFlagName selected: $flagName_1")
                            if (allCorrect){
                                Log.d("Submit","All Correct Working")
                                isFieldsEnabled = false
                                wrongCounts = 0
                            }else{
                                wrongCounts++
                                Log.d("wrong Count","Count:$wrongCounts")
                            }
                            if (flagName_1 == textFieldValue_1){
                                Log.d("Answer 1 ","Correct")
                                textB_1 = true
                                marks++
                            }
                            if (flagName_2 == textFieldValue_2){
                                Log.d("Answer 2","Correct")
                                textB_2 = true
                                marks++
                            }
                            if (flagName_3 == textFieldValue_3){
                                Log.d("Answer 3","Correct")
                                textB_3 = true
                                marks++
                            }
                            onShowDialogChange (true)
                            Log.d("SubmitButtonToggled", "Submit button clicked")


                        }

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF52CCE3),
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    ) {
                    Text(text = if (submitted && nextClicked) "Next" else "Submit") // Change the button text based on the submitted state
                }






            }
        }
    }


    // Show dialog if necessary
    if (showDialog) {
        Log.d("showDialog","is Working $allCorrect")


        if (allCorrect){
            Log.d("Correct Answer","Working")

            val message =  "You Have Guessed all the Country names Correctly"


            ShowDialogFlag(
                result = "Congratulations",
                message = message,
                correctCountryName = "",
                onDismissRequest = {
                    onShowDialogChange(false)

                                   },
                correctAnswer = true
            )
        }
        if (!allCorrect){
            Log.d("Wrong Answer","Working")

            val message =  "Your Response Consist Wrong Answers"


            ShowDialogFlag(
                result = "Try Again",
                message = message,
                correctCountryName = "",
                onDismissRequest = {
                    onShowDialogChange(false)

                                   },
                correctAnswer = false
            )
        }


    }



}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TimerBegin(onFinish : () -> Unit) {
    var time by rememberSaveable {
        mutableStateOf(TimeUnit.SECONDS.toMillis(10)) // Start from 59 seconds
    }
    var isRunning by rememberSaveable {
        mutableStateOf(true) // Start the timer automatically
    }
    var startTime by rememberSaveable {
        mutableStateOf(System.currentTimeMillis())
    }

    Column(
        modifier = Modifier
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formatTime(time),
            modifier = Modifier.padding(9.dp)
        )

        LaunchedEffect(isRunning) {
            while (isRunning) {
                delay(1000)
                val elapsedTime = System.currentTimeMillis() - startTime
                time = TimeUnit.SECONDS.toMillis(10) - elapsedTime
                if (time <= 0) {
                    time = 0
                    isRunning = false
                    onFinish()

                }
            }
        }
    }

}


@Composable
fun formatTime(timeMi : Long):String{
    val min = TimeUnit.MILLISECONDS.toMinutes(timeMi) %60
    val sec = TimeUnit.MILLISECONDS.toSeconds(timeMi) %60

    return String.format("%02d:%02d",min,sec)
}

