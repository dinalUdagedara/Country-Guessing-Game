package com.example.cw

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cw.ui.theme.CwTheme
import kotlin.random.Random


class AdvancedLevel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CwTheme {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                    var showDialog  by remember { mutableStateOf(false) }
                    val (randomCountryKey, randomFlagName, imageIdAndNameList) = rememberRandomIndicesAndFlag()
                    var currentRandomIndices by remember { mutableStateOf(listOf(0,1,2)) }
                    AdvancedLevelContent(
                        showDialog = showDialog,
                        onShowDialogChange = { showDialog = it },
                        randomFlagName = randomFlagName,
                        imageIdAndNameList = imageIdAndNameList,
                        onNextButtonClick = {
                            currentRandomIndices = List(3) { Random.nextInt(imageIdAndNameList.size) }
                        },
                        currentRandomIndices = currentRandomIndices

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
    currentRandomIndices:List<Int>

) {
    Log.d(" GuessFlagContent","This is Working")
    val context = LocalContext.current

    var textFieldValue_1 by remember { mutableStateOf("") }
    var textFieldValue_2 by remember { mutableStateOf("") }
    var textFieldValue_3 by remember { mutableStateOf("") }

    var submitCount by remember { mutableStateOf(0) }

    // Define a variable to control the enabled state of the text fields
    var isFieldsEnabled by remember { mutableStateOf(true) }


    var textB_1Submitted by remember { mutableStateOf(false) }
    var textB_1 by remember { mutableStateOf(false) }

    var textB_2Submitted by remember { mutableStateOf(false) }
    var textB_2 by remember { mutableStateOf(false) }

    var textB_3Submitted by remember { mutableStateOf(false) }
    var textB_3 by remember { mutableStateOf(false) }

    var allCorrect by remember { mutableStateOf(false) }


    var textSubmitted by remember { mutableStateOf(false) }
    var submitted by remember { mutableStateOf(false) } // State to track if the user has submitted their guess
    var nextClicked by remember { mutableStateOf(true) }


//    // Get random flag details
//    val (randomCountryKey, randomFlagName) = countryMap.entries.toList()[randomIndices[0]]
//    val imageIdAndNameList = randomIndices.map { index ->
//        val (countryKey, _) = countryMap.entries.toList()[index]
//        val flagName = countryMap[countryKey].toString()
//        val imageId = imageResourceIds[index]
//        Pair(imageId, flagName)
//    }

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
//                Button(onClick = {
//                    onNextButtonClick()
//                    Log.d("Next Button","Working")
//                },
//                    modifier = Modifier.padding(top =6.dp )) {
//                    Text(text = "Next")
//                }



//                // Display buttons for each flag using the current random indices
//                currentRandomIndices.forEach { index ->
//                    val (imageId, flagName) = imageIdAndNameList[index]
//                    FlagButton(
//                        imageId = imageId,
//                        flagName = flagName,
//                        onShowDialogChange = onShowDialogChange
//                    )
//                    TextField(value = textFieldValue,
//                        onValueChange = {
//                            if (it.length <= 1) {
//                                textFieldValue = it
//                            }
//                        } )
//                }

//  Flag 1
                val (imageId_1,flagName_1) = imageIdAndNameList[currentRandomIndices[0]]
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
                        }) as Color  // Change the text color if needed
                    ),
                    )


//  Flag 2
                val (imageId_2,flagName_2) = imageIdAndNameList[currentRandomIndices[1]]
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
                val (imageId_3,flagName_3) = imageIdAndNameList[currentRandomIndices[2]]
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
                        }) as Color  // Change the text color if needed
                    ),
                    )



                // Display the correct Flag Name
                if (randomFlagName != null) {
                    Text(text = "1: $flagName_1, 2: $flagName_2, 3: $flagName_3")
                }
//
//                Button(onClick = {
//                    allCorrect = flagName_1 == textFieldValue_1 && flagName_2 == textFieldValue_2 && flagName_3 == textFieldValue_3
//                    submitCount = 1
//                    Log.d("ButtonClick","correct: $randomFlagName selected: $flagName_1")
//                    if (allCorrect){
//                        Log.d("Submit","All Correct Working")
//                        isFieldsEnabled = false
//                        onShowDialogChange (true)
//                    }
//                }) {
//                    Text(text = "Submit")
//                }



                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
//                    Log.d("DropdownExample", "Submit button clicked")


                        submitted = true

                        nextClicked = !nextClicked

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

                        }
                        else{

                            textSubmitted = true

                            allCorrect = flagName_1 == textFieldValue_1 && flagName_2 == textFieldValue_2 && flagName_3 == textFieldValue_3


                            submitCount = 1
                            Log.d("ButtonClick","correct: $randomFlagName selected: $flagName_1")
                            if (allCorrect){
                                Log.d("Submit","All Correct Working")
                                isFieldsEnabled = false
                            }
                            if (flagName_1 == textFieldValue_1){
                                Log.d("Answer 1 ","Correct")
                                textB_1 = true
                            }
                            if (flagName_2 == textFieldValue_2){
                                Log.d("Answer 2","Correct")
                                textB_2 = true
                            }
                            if (flagName_3 == textFieldValue_3){
                                Log.d("Answer 3","Correct")
                                textB_3 = true
                            }
                            onShowDialogChange (true)
                            Log.d("SubmitButtonToggled", "Submit button clicked")


                        }

                    }) {
                    Text(text = if (submitted && !nextClicked) "Next" else "Submit") // Change the button text based on the submitted state
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
                context = context,
                result = "All Correct",
                message = message,
                correctCountryName = selectedFlag,
                onDismissRequest = { onShowDialogChange(false) },
                correctAnswer = true
            )
        }
        if (!allCorrect){
            Log.d("Wrong Answer","Working")

            val message =  "Wrong Answers"


            ShowDialogFlag(
                context = context,
                result = "Wrong",
                message = message,
                correctCountryName = selectedFlag,
                onDismissRequest = { onShowDialogChange(false) },
                correctAnswer = false
            )
        }


    }
}
