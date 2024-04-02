package com.example.cw

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cw.ui.theme.CwTheme
import kotlin.random.Random
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GuessCountry : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            CwTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFFFFF)

                ) {
                    val timerSwitch = intent.getBooleanExtra("Timer",false)
                    GuessCountryContent(timerSwitch)
                }
            }

        }
    }

}


@Composable
fun GuessCountryContent(timerSwitch:Boolean) {
    Log.d("Timer","$timerSwitch")
    val context = LocalContext.current
    val countryMap = rememberSaveable{ mutableMapOf<String,String>() }


    readJson(context,countryMap)



    // List of image resource IDs
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

    // State to hold the selected country key
    var randomCountryKey by rememberSaveable { mutableStateOf("") }



    // Generate a random index
    var randomIndex by rememberSaveable { mutableStateOf(Random(seed = System.currentTimeMillis()).nextInt(0, imageResourceIds.size)) }




    // Get the painter for the randomly selected image
    val randomImagePainter: Painter = painterResource(id = imageResourceIds[randomIndex])






    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Guess the Country!")


        Box(
            modifier = Modifier.size(150.dp),
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


        DropdownExample(
            countryMap = countryMap,
            randomCountryKey = randomCountryKey,
            onNextClicked = {
                //Reset the submitted state and genrate a new random index and country key
//                submitted = false
                randomIndex = Random(seed = System.currentTimeMillis()).nextInt(0, imageResourceIds.size)
                randomCountryKey = countryMap.keys.toList()[randomIndex]
            },
            timerSwitch = timerSwitch
        )
    }
}


fun readJson(context: Context, countryMap: MutableMap<String, String>) {
    // Read JSON from assets
    try {
        val json: String = context.assets.open("countries.json").bufferedReader().use{it.readText() }

        // Parse JSON using kotlinx.serialization
        val countryDataList = Json.decodeFromString<Map<String, String>>(json)

        // Update the countryMap
        countryMap.putAll(countryDataList)
    } catch (e: Exception) {
        // Handle exceptions (e.g., JSON parsing errors)
        // You may want to log the exception or show an error message
        e.printStackTrace()
        }
   }


@Composable
fun DropdownExample(
    countryMap: Map<String, String>,
    randomCountryKey: String,
    onNextClicked:()-> Unit,
    timerSwitch : Boolean
) {
    LocalContext.current
    var selectedOption by rememberSaveable { mutableStateOf("Click Here to Select") }
    var selectedCountryKey by rememberSaveable { mutableStateOf("") }
    var showToast by rememberSaveable { mutableStateOf(false) }
    var submitted by rememberSaveable { mutableStateOf(false) } // State to track if the user has submitted their guess
    var nextClicked by rememberSaveable { mutableStateOf(true) }
    var isEnable by rememberSaveable { mutableStateOf(true) }
    var timerOnNext by rememberSaveable { mutableStateOf(false) }

    if (timerSwitch){
        TimerBegin(
            onFinish = {
                submitted = true

                nextClicked = !nextClicked

                if (nextClicked ){
                    submitted = false
                    onNextClicked()
                    showToast = false
                    Log.d("NextButtonToggled", "Next button clicked")

                }
                else{
                    checkSelectedCountry(selectedCountryKey,randomCountryKey,countryMap)
                    showToast = true
                    Log.d("SubmitButtonToggled", "Submit button clicked")


                }
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {
        Text(text = "Select Country")

        TextButton(
            onClick = { },
            ) {
            Text(countryMap[randomCountryKey].toString() )
        }
        Button(onClick = {
            onNextClicked()
            isEnable = false
        }, enabled = isEnable,
            modifier = Modifier
                .width(200.dp)
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF52CCE3),
                contentColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text(text = "Click Here to Start")
        }
        Surface(
            modifier = Modifier
               .padding(8.dp) ,
            color = Color(0xFFF2F2F2)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .padding(16.dp),

                    enabled = !isEnable,
                    onClick = {
//                    Log.d("DropdownExample", "Submit button clicked")

                        submitted = true

                        nextClicked = !nextClicked

                        if (nextClicked ){
                            submitted = false
                            onNextClicked()
                            showToast = false
                            Log.d("NextButtonToggled", "Next button clicked")
                            timerOnNext = true

                        }
                        else{
                            checkSelectedCountry(selectedCountryKey,randomCountryKey,countryMap)
                            showToast = true
                            Log.d("SubmitButtonToggled", "Submit button clicked")


                        }

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF52CCE3),
                        contentColor = MaterialTheme.colors.onPrimary
                    )
                    ) {
                    Text(text = if (submitted && !nextClicked) "Next" else "Submit") // Change the button text based on the submitted state
                }


                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFFFFF)),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    items(countryMap.keys.toList()) { key ->
                        val value = countryMap[key] ?: ""
                        DropdownMenuItem(onClick = {
                            selectedCountryKey = key
                            selectedOption = value
                        }) {
                            Text(value)
                        }
                    }
                }
            }




        }
    }

    if (showToast){
        Log.d("ShowToast is Working", "Working Fine")
        val correctCountry = countryMap[randomCountryKey]
        val correctAnswer = selectedCountryKey == randomCountryKey
        if (correctAnswer) {
            if (correctCountry != null) {
                Log.d("ShowToast Correct Country ", "Working Fine")
                ShowDialog(

                    result = "CORRECT!",
                    message = "Congratulations You have Selected the Correct Answer which is:",
                    correctCountryName= correctCountry,
                    onDismissRequest = {showToast = false},
                    correctAnswer = correctAnswer
                )
            }
        }else{
            if (correctCountry != null) {
                Log.d("ShowToast Wrong Country ", "Working Fine")
                ShowDialog(
                    result = "Wrong!",
                    message = "The Selected Country is $selectedOption, but correct Country is: ",
                    correctCountryName= correctCountry,
                    onDismissRequest = {showToast = false},
                    correctAnswer = false
                )
            }
            else{
                Log.d("ShowToast Correct Country ", "Not Working Fine")
            }
        }

    }

    //Timer When Next is Clicked

//    if (timerOnNext){
//        if (timerSwitch){
//            TimerBegin(
//                onFinish = {
//                    submitted = true
//
//                    nextClicked = !nextClicked
//
//                    if (nextClicked ){
//                        submitted = false
//                        onNextClicked()
//                        showToast = false
//                        Log.d("NextButtonToggled", "Next button clicked")
//
//                    }
//                    else{
//                        checkSelectedCountry(selectedCountryKey,randomCountryKey,countryMap)
//                        showToast = true
//                        Log.d("SubmitButtonToggled", "Submit button clicked")
//
//
//                    }
//                }
//            )
//        }
//    }



}


fun checkSelectedCountry(selectedCountryKey: String, randomCountryKey: String, countryMap: Map<String, String>) {
    val selectedCountryName = countryMap[selectedCountryKey]
    val randomCountryName = countryMap[randomCountryKey]

    if (selectedCountryKey == randomCountryKey) {
        // Correct guess
        Log.d("GuessCountry", "Correct! The selected country is $selectedCountryName")
        // You can perform any additional actions here, such as updating UI or keeping score
    } else {
        // Incorrect guess
        Log.d(
            "GuessCountry",
            "Incorrect! The selected country is $selectedCountryName, but the correct country is $randomCountryName"
        )
        // You can perform any additional actions here, such as showing a message to the user
    }
}



@Composable
fun ShowDialog(
    result: String,
    message: String,
    correctCountryName: String,
    onDismissRequest: () -> Unit, // Function to handle dialog dismissal
    correctAnswer: Boolean
) {
    if (correctAnswer) Color.Green else Color.Red
    val textColor = if (correctAnswer) Color.Green else Color.Red
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Result")
        },
        text = {
            Row {
                Column {
                    Text(text = result, color = textColor)
                    Text(text = message)
                    Text(text = correctCountryName, color = Color.Blue)

                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    // Call the provided onDismissRequest function to dismiss the dialog
                    onDismissRequest()
                    Log.d("OnClick", "Function is Called")
                }
            ) {
                Text(text = "OK")
            }
        }
    )
}

