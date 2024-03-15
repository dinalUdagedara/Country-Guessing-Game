package com.example.cw

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.CwTheme
import kotlin.random.Random

class GuessHints : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CwTheme {

                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ){

                    GuessHintsContent()
                }
            }
        }
    }
}

var wrongGuesses = 1
var correctAnswer = false

@Composable
fun GuessHintsContent() {
    val context = LocalContext.current
    val countryMap = remember { mutableMapOf<String,String>() }

    readJson(context, countryMap)

    val imageResourceIds = listOf(
        R.drawable.ad,
        R.drawable.ae,
        R.drawable.af,
        R.drawable.ag,
        R.drawable.ai,
        R.drawable.al,
        R.drawable.am,
        R.drawable.ao,
        R.drawable.aq,
        R.drawable.ar,
        R.drawable.as_,
        R.drawable.at,
        R.drawable.au,
        R.drawable.aw,
        R.drawable.ax,
        R.drawable.az,
        R.drawable.ba,
        R.drawable.bb,
        R.drawable.bd,
        R.drawable.be,
        R.drawable.bf,
        R.drawable.bg,
        R.drawable.bh,
        R.drawable.bi,
        R.drawable.bj,
        R.drawable.bl,
        R.drawable.bm,
        R.drawable.bn,
        R.drawable.bo,
        R.drawable.bq,
        R.drawable.br,
        R.drawable.bs,
        R.drawable.bt,
        R.drawable.bv,
        R.drawable.bw,
        R.drawable.by,
        R.drawable.bz,
        R.drawable.ca,
        R.drawable.cc,
        R.drawable.cd,
        R.drawable.cf,
        R.drawable.cg,
        R.drawable.ch,
        R.drawable.ci,
        R.drawable.ck,
        R.drawable.cl,
        R.drawable.cm,
        R.drawable.cn,
        R.drawable.co,
        R.drawable.cr,
        R.drawable.cu,
        R.drawable.cv,
        R.drawable.cw,
        R.drawable.cx,
        R.drawable.cy,
        R.drawable.cz,
        R.drawable.de,
        R.drawable.dj,
        R.drawable.dk,
        R.drawable.dm,
        R.drawable.doo,
        R.drawable.dz,
        R.drawable.ec,
        R.drawable.ee,
        R.drawable.eg,
        R.drawable.eh,
        R.drawable.er,
        R.drawable.es,
        R.drawable.et,
        R.drawable.eu,
        R.drawable.fi,
        R.drawable.fj,
        R.drawable.fk,
        R.drawable.fm,
        R.drawable.fo,
        R.drawable.fr,
        R.drawable.ga,
        R.drawable.gbeng,
        R.drawable.gbnir,
        R.drawable.gbsct,
        R.drawable.gbwls,
        R.drawable.gb,
        R.drawable.gd,
        R.drawable.ge,
        R.drawable.gf,
        R.drawable.gg,
        R.drawable.gh,
        R.drawable.gi,
        R.drawable.gl,
        R.drawable.gm,
        R.drawable.gn,
        R.drawable.gp,
        R.drawable.gq,
        R.drawable.gr,
        R.drawable.gs,
        R.drawable.gt,
        R.drawable.gu,
        R.drawable.gw,
        R.drawable.gy,
        R.drawable.hk,
        R.drawable.hm,
        R.drawable.hn,
        R.drawable.hr,
        R.drawable.ht,
        R.drawable.hu,
        R.drawable.id,
        R.drawable.ie,
        R.drawable.il,
        R.drawable.im,
        R.drawable.in_,
        R.drawable.io,
        R.drawable.iq,
        R.drawable.ir,
        R.drawable.is_,
        R.drawable.it,
        R.drawable.je,
        R.drawable.jm,
        R.drawable.jo,
        R.drawable.jp,
        R.drawable.ke,
        R.drawable.kg,
        R.drawable.kh,
        R.drawable.ki,
        R.drawable.km,
        R.drawable.kn,
        R.drawable.kp,
        R.drawable.kr,
        R.drawable.kw,
        R.drawable.ky,
        R.drawable.kz,
        R.drawable.la,
        R.drawable.lb,
        R.drawable.lc,
        R.drawable.li,
        R.drawable.lk,
        R.drawable.lr,
        R.drawable.ls,
        R.drawable.lt,
        R.drawable.lu,
        R.drawable.lv,
        R.drawable.ly,
        R.drawable.ma,
        R.drawable.mc,
        R.drawable.md,
        R.drawable.me,
        R.drawable.mf,
        R.drawable.mg,
        R.drawable.mh,
        R.drawable.mk,
        R.drawable.ml,
        R.drawable.mm,
        R.drawable.mn,
        R.drawable.mo,
        R.drawable.mp,
        R.drawable.mq,
        R.drawable.mr,
        R.drawable.ms,
        R.drawable.mt,
        R.drawable.mu,
        R.drawable.mv,
        R.drawable.mw,
        R.drawable.mx,
        R.drawable.my,
        R.drawable.mz,
        R.drawable.na,
        R.drawable.nc,
        R.drawable.ne,
        R.drawable.nf,
        R.drawable.ng,
        R.drawable.ni,
        R.drawable.nl,
        R.drawable.no,
        R.drawable.np,
        R.drawable.nr,
        R.drawable.nu,
        R.drawable.nz,
        R.drawable.om,
        R.drawable.pa,
        R.drawable.pe,
        R.drawable.pf,
        R.drawable.pg,
        R.drawable.ph,
        R.drawable.pk,
        R.drawable.pl,
        R.drawable.pm,
        R.drawable.pn,
        R.drawable.pr,
        R.drawable.ps,
        R.drawable.pt,
        R.drawable.pw,
        R.drawable.py,
        R.drawable.qa,
        R.drawable.re,
        R.drawable.ro,
        R.drawable.rs,
        R.drawable.ru,
        R.drawable.rw,
        R.drawable.sa,
        R.drawable.sb,
        R.drawable.sc,
        R.drawable.sd,
        R.drawable.se,
        R.drawable.sg,
        R.drawable.sh,
        R.drawable.si,
        R.drawable.sj,
        R.drawable.sk,
        R.drawable.sl,
        R.drawable.sm,
        R.drawable.sn,
        R.drawable.so,
        R.drawable.sr,
        R.drawable.ss,
        R.drawable.st,
        R.drawable.sv,
        R.drawable.sx,
        R.drawable.sy,
        R.drawable.sz,
        R.drawable.tc,
        R.drawable.td,
        R.drawable.tf,
        R.drawable.tg,
        R.drawable.th,
        R.drawable.tj,
        R.drawable.tk,
        R.drawable.tl,
        R.drawable.tm,
        R.drawable.tn,
        R.drawable.to,
        R.drawable.tr,
        R.drawable.tt,
        R.drawable.tv,
        R.drawable.tw,
        R.drawable.tz,
        R.drawable.ua,
        R.drawable.ug,
        R.drawable.um,
        R.drawable.us,
        R.drawable.uy,
        R.drawable.uz,
        R.drawable.va,
        R.drawable.vc,
        R.drawable.ve,
        R.drawable.vg,
        R.drawable.vi,
        R.drawable.vn,
        R.drawable.vu,
        R.drawable.wf,
        R.drawable.ws,
        R.drawable.xk,
        R.drawable.ye,
        R.drawable.yt,
        R.drawable.za,
        R.drawable.zm,
        R.drawable.zw
    )

    var randomCountryKey by remember { mutableStateOf("") }
    var randomIndex by remember { mutableStateOf(Random.nextInt(0, imageResourceIds.size)) }
    val randomImagePainter: Painter = painterResource(id = imageResourceIds[randomIndex])


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Guess the Country!")

        Box(
            modifier = Modifier.size(200.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = randomImagePainter,
                contentDescription = "Random Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
        }

        DropDownHints(
            countryMap = countryMap,
            randomCountryKey = randomCountryKey,
            onNextClicked = {
                randomIndex = Random.nextInt(0, imageResourceIds.size)
                randomCountryKey = countryMap.keys.toList()[randomIndex]
            }
        )
    }
}

@Composable
fun DropDownHints(
    countryMap: Map<String, String>,
    randomCountryKey: String,
    onNextClicked: () -> Unit
) {
    val context = LocalContext.current
    var textFieldValue by remember { mutableStateOf("") }
    var guessedLetter by remember { mutableStateOf("") }
    var checkInput by remember { mutableStateOf(false) }
    var showToast by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) } // State for dialog visibility
    var submitted by remember { mutableStateOf(false) } // State to track if the user has submitted their guess.
    var nextClicked by remember { mutableStateOf(true) }

    correctAnswer = false


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = { /* Handle click */ }) {
            countryMap[randomCountryKey]?.let { Text(text = it) }
        }

        Surface(
            modifier = Modifier.padding(16.dp)
        ) {
            Row() {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = textFieldValue,
                        onValueChange = {
                            if (it.length <= 1) {
                                textFieldValue = it
                            }
                        },
                        label = { Text(text = "Enter Your Guess")},
                        modifier = Modifier.padding(16.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text
                        ),
                        maxLines = 1 // Restrict input to a single line
                    )
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ){

                        Button(
                            modifier = Modifier.padding(16.dp),
                            onClick = {
                                onNextClicked()
                                wrongGuesses=1

                                Log.d("NextButtonToggled", "Next button clicked")
                                checkInput = false
                            }) {
                            Text(text =  "Next") // Change the button text based on the submitted state

                        }

                        Button(
                            modifier = Modifier.padding(16.dp),
                            onClick = {
                                guessedLetter = textFieldValue
                                textFieldValue = ""
                                checkInput = true
                                showDialog =true


                            }) {
                            Text(text = "Submit")
                        }
                    }




//         Combining Two Buttons into One
//                    Button(
//                        modifier = Modifier.padding(16.dp),
//                        onClick = {
////                    Log.d("DropdownExample", "Submit button clicked")
//
//
//                            submitted = true
//
//                            nextClicked = !nextClicked
//
//                            if (nextClicked ){
//                                submitted = false
//                                onNextClicked()
//                                wrongGuesses=1
//
//                                Log.d("NextButtonToggled", "Next button clicked")
//                                checkInput = false
//                                Log.d("NextButtonToggled", "Next button clicked")
//
//                            }
//                            else{
//
//                                guessedLetter = textFieldValue
//                                textFieldValue = ""
//                                checkInput = true
//                                showDialog =true
//                                Log.d("SubmitButtonToggled", "Submit button clicked")
//
//
//                            }
//
//                        }) {
//                        Text(text = if (submitted && !nextClicked) "Next" else "Submit") // Change the button text based on the submitted state
//
//                    }
//
//



                    if(showDialog) {
                        if (correctAnswer) {
                            countryMap[randomCountryKey]?.let {
                                ShowDialog(

                                    context = context,
                                    result = "CORRECT!",
                                    message = "Congratulations You have Guessed the Correct Country which is:",
                                    correctCountryName = it,
                                    onDismissRequest = { showDialog = false },
                                    correctAnswer = correctAnswer
                                )
                            }

                        }
                    }
                }
            }

            if (checkInput && wrongGuesses<3) {
                val correctCountryName = countryMap[randomCountryKey]
                CheckChar(guessedLetter, correctCountryName)
                Log.d("if checkInput", " $correctCountryName input: $guessedLetter ")
            } else if (checkInput && wrongGuesses >= 3){
                Text(text = "Your chances ran out")
                showDialog = true
                if (showDialog) {
                    countryMap[randomCountryKey]?.let {
                        ShowDialog(

                            context = context,
                            result = "Wrong!",
                            message = "You have Ran out of your chances and the Correct Country is:",
                            correctCountryName = it,
                            onDismissRequest = { checkInput = false },
                            correctAnswer = false
                        )
                    }
                }
            }


        }

    }
}
@Composable
fun CheckChar(userInput: String, correctCountry: String?) {
    val correctCountryName = correctCountry ?: return

    val lowercaseUserInput = userInput.toLowerCase()
    val lowercaseCorrectCountryName = correctCountryName.toLowerCase()

    val guessedChars = remember { mutableSetOf<Char>() }

    // Add the newly guessed character to the set
    if (userInput.isNotEmpty()) {
        val guessedChar = lowercaseUserInput.first()
        if (guessedChar !in lowercaseCorrectCountryName && guessedChar !in guessedChars) {
            wrongGuesses++
        }
        guessedChars.add(guessedChar)
    }

    val modifiedCountryName = buildString {


        for (char in lowercaseCorrectCountryName) {
            if (char in guessedChars || char.isWhitespace()) {
                append(char)
            } else {
                append("-")
            }

        }

    }
    Log.d("Checking","$modifiedCountryName correct: $lowercaseCorrectCountryName")
    if (modifiedCountryName == lowercaseCorrectCountryName){
        correctAnswer = true
        Log.d("Correct Answer","Correctly Working")
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = modifiedCountryName,
            modifier = Modifier.padding(vertical = 1.dp),
            fontSize = 16.sp


        )
    }
}

