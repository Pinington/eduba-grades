package com.example.eduba_grades

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.log

val listSpe : List<String> = listOf(
    // Don't change ordering because enum depends on it
    // Add new spe at end
    "Automatique & Systemes Embarques",
    "Genie Industriel",
    "Informatique et Reseaux",
    "Mecanique",
    "Textiles et Fibres"
)

enum class Spe (val value : Int) {
    // https://stackoverflow.com/questions/53523948/how-do-i-create-an-enum-from-an-int-in-kotlin
    None(0),
    ASE(1),
    GI(2),
    IR(3),
    MECA(4),
    TF(5);

    companion object {
        fun fromInt(value: Int) = entries.first { it.value == value }
    }
}

fun getUeList(root: RootData, spe: Spe, sem: Int): List<UniteEnseignement> {
    val ueMap = when (spe) {
        Spe.IR -> root.ue_ir
        Spe.ASE -> root.ue_ase
        Spe.GI -> root.ue_gi
        Spe.MECA -> root.ue_meca
        Spe.TF -> root.ue_tf
        Spe.None -> emptyMap()
    }

    return ueMap[sem.toString()] ?: emptyList()
}

enum class Screen {
    SPECIALITE,
    SEMESTRE,
    MATIERES
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(Screen.SPECIALITE) }

    BackHandler(enabled = currentScreen != Screen.SPECIALITE) {
        currentScreen = when (currentScreen) {
            Screen.MATIERES -> Screen.SEMESTRE
            Screen.SEMESTRE -> Screen.SPECIALITE
            else -> Screen.SPECIALITE
        }
    }

    var selectedSem by remember { mutableIntStateOf(1) }
    var selectedSpe by remember { mutableStateOf(Spe.None) }

    val context = LocalContext.current
    val root = remember {
        loadRootData(context)
    }

    when (currentScreen) {
        Screen.SPECIALITE -> {
            SpeSelectView(
                onSpecialityClick = { s ->
                    currentScreen = Screen.SEMESTRE
                    selectedSpe = Spe.fromInt(s)
                }
            )
        }

        Screen.SEMESTRE -> {
            SemSelectView(
                onSemesterClick = { sem ->
                    currentScreen = Screen.MATIERES
                    selectedSem = sem
                    Log.d(
                        "EDUBA_DEBUG",
                        "SelectedSpe=$selectedSpe | SelectedSem=$selectedSem"
                    )
                }
            )
        }

        Screen.MATIERES -> {
            val ueList = getUeList(root, selectedSpe, selectedSem)
            DisplaySubjects(ueList)
        }
    }
}

@Composable
fun SpeSelectView(spe: List<String> = listSpe, onSpecialityClick: (Int) -> Unit) {
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(0.dp, 18.dp)
            .fillMaxWidth()
    ) {
        itemsIndexed(spe) { index, item ->
            Card(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(0.95f)
                    .clickable {
                        onSpecialityClick(index + 1)
                    }
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun SemSelectView(sem: Int = 6, onSemesterClick: (Int) -> Unit) {
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(sem) {

            if (it % 2 == 0) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .padding(top = 24.dp)
                ) {
                    Text (
                        text = "${(it / 2) + 1}A",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .align(Alignment.CenterStart)
                    )
                }
            }

            Card (
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(0.95F)
                    .clickable{
                        onSemesterClick(it + 1)
                    }
            ) {
                Text (
                    text = "Semestre ${it + 1}",
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun DisplaySubjects(unites : List<UniteEnseignement>) {
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp)
    ) {
        items(unites) { ue ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {
                Text(
                    text = ue.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp, 5.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }


            ue.modules.forEachIndexed { index, subject ->
                val color = if (index % 2 == 0) Color.Gray else Color.DarkGray
                Row (
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .background(color)
                        .padding(6.dp)
                ) {
                    Text(
                        text = subject.name,
                        fontSize = 20.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    var searchQuery by remember {mutableStateOf("")}
                    TextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                        },
                        singleLine = true,
                        modifier = Modifier
                            .padding()
                            .height(28.dp)
                            .width(40.dp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}