package com.example.cw

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

class GuessFlag : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CwTheme {


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var showDialog  by rememberSaveable { mutableStateOf(false) }
                    val (_, _, imageIdAndNameList) = rememberRandomIndicesAndFlag()
                    var currentRandomIndices by rememberSaveable { mutableStateOf(listOf(0,1,2)) }
                    GuessFlagContent(
                        showDialog = showDialog,
                        onShowDialogChange = { showDialog = it },
                        imageIdAndNameList = imageIdAndNameList,
                        onNextButtonClick = {

                            currentRandomIndices = List(3) { Random.nextInt(imageIdAndNameList.size) }
                            Log.d("Checking current random","$currentRandomIndices")

                        },
                        currentRandomIndices = currentRandomIndices

                    )
                    Log.d("setContent Running","Running")
                }
            }
        }

    }
}


var nextButtonVisible = false



@Composable
fun GuessFlagContent(
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit,
    imageIdAndNameList: List<Pair<Int, String>>,
    onNextButtonClick: () -> Unit ,// Callback for handling Next button click
    currentRandomIndices:List<Int>

) {
    var randomFlagName1 by rememberSaveable { mutableStateOf("") }
    Log.d(" GuessFlagContent","This is Working")
    LocalContext.current

    if(currentRandomIndices.isNotEmpty() && !showDialog){
        val randomIndex = (currentRandomIndices.indices).random()
        val (_, flagName)= imageIdAndNameList[currentRandomIndices[randomIndex]]
         randomFlagName1 = flagName
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                Text(text = "Guess Flag by Country!")
                Button(onClick = {
                    onNextButtonClick()
                    submitClicked = false
                    nextButtonVisible = false },
                    enabled =nextButtonVisible,
                    modifier = Modifier.padding(top =6.dp )) {
                    Text(text = "Next")
                }

                // Display the correct Flag Name
                if (!submitClicked) {
                    Text(text = randomFlagName1)
                }

                if (!submitClicked){

                    // Display buttons for each flag using the current random indices
                    currentRandomIndices.forEach { index ->
                        val (imageId, flagName) = imageIdAndNameList[index]
                        FlagButton(
                            imageId = imageId,
                            flagName = flagName,
                            onShowDialogChange = onShowDialogChange
                        )
                    }
                }
            }

        }


    }

    // Show dialog if necessary
    if (showDialog) {
        val rightAnswer = randomFlagName1 == selectedFlag
        Log.d("Selected Flag","Selected Flag $selectedFlag  right answer: $randomFlagName1")
        val message = if (rightAnswer) "You Have Selected The Correct Flag for the Country: " else "You Have Selected The Flag for the Country: "
        ShowDialogFlag(
            result = if (rightAnswer) "Correct" else "Wrong",
            message = message,
            correctCountryName = selectedFlag,
            onDismissRequest = { onShowDialogChange(false) },
            correctAnswer = rightAnswer
        )
    }
}
var selectedFlag=""
var submitClicked = false

@Composable
fun FlagButton(
    imageId: Int,
    flagName: String,
    onShowDialogChange: (Boolean) -> Unit
) {
    Log.d("FlagButton()","Running")
    val randomImagePainter: Painter = painterResource(id = imageId)
    Button(
        onClick = {
            selectedFlag = flagName
            onShowDialogChange(true)
            nextButtonVisible = true
            submitClicked = true


        },
        enabled= !submitClicked,
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp)
    ) {
        Image(
            painter = randomImagePainter,
            contentDescription = "Random Image",
            modifier = Modifier
                .size(200.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun ShowDialogFlag(
    result: String,
    message: String,
    correctCountryName: String,
    onDismissRequest: () -> Unit, // Function to handle dialog dismissal
    correctAnswer: Boolean
) {
    Log.d("ShowDialogFlag","Running")
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
                    onDismissRequest()
                    Log.d("OnClick", "Function is Called")
                }
            ) {
                Text(text = "Ok")
            }
        }
    )
}


@Composable
fun rememberRandomIndicesAndFlag(): Triple<String, String, List<Pair<Int, String>>> {
    Log.d("rememberRandomIndices()","Running")
    val context = LocalContext.current
    val countryMap = rememberSaveable { mutableMapOf<String, String>() }
    readJson(context, countryMap)

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

//    // Generate random indices
//    val randomIndices = remember{ List(100) { Random.nextInt(imageResourceIds.size) }}
    // Generate a list of random indices
    val randomIndices = rememberSaveable {
        List(100) { Random(seed = System.currentTimeMillis() + it).nextInt(imageResourceIds.size) }
    }

    val randomIndex = randomIndices.random()
    val (randomCountryKey, randomFlagName) = remember {countryMap.entries.toList()[randomIndex]}
    val imageIdAndNameList = remember {
        randomIndices.map { index ->
            val (countryKey, _) = countryMap.entries.toList()[index]
            val flagName = countryMap[countryKey].toString()
            val imageId = imageResourceIds[index]
            Pair(imageId, flagName)
        }
    }

    return Triple(randomCountryKey, randomFlagName, imageIdAndNameList)
}

