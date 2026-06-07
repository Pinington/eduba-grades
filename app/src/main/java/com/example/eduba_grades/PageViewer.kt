package com.example.eduba_grades

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val listSpe : List<String> = listOf(
    "Automatique & Systemes Embarques",
    "Genie Industriel",
    "Informatique et Reseaux",
    "Mecanique",
    "Textiles et Fibres"
)

enum class Screen {
    SPECIALITE,
    SEMESTRE,
    MATIERES
}

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

    var specialite by remember { mutableStateOf(Spe.None) }
    var semestre by remember {mutableIntStateOf(0)}

    when (currentScreen) {
        Screen.SPECIALITE -> {
            SpeView(
                onSpecialityClick = { spe ->
                    currentScreen = Screen.SEMESTRE
                    specialite = Spe.fromInt(spe)
                }
            )
        }

        Screen.SEMESTRE -> {
            SemView(
                onSemesterClick = { sem ->
                    currentScreen = Screen.MATIERES
                    semestre = sem
                }
            )
        }

        Screen.MATIERES -> {

        }
    }
}

@Composable
fun SpeView(spe: List<String> = listSpe, onSpecialityClick: (Int) -> Unit) {
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
                        onSpecialityClick(index)
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
fun SemView(sem: Int = 6, onSemesterClick: (Int) -> Unit) {
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