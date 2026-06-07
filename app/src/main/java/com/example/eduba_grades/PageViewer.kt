package com.example.eduba_grades

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
    SEMESTRE
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(Screen.SPECIALITE) }

    when (currentScreen) {
        Screen.SPECIALITE -> {
            SpeView(
                onSpecialityClick = {
                    currentScreen = Screen.SEMESTRE
                }
            )
        }

        Screen.SEMESTRE -> {
            SemView()
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
fun SemView(sem: Int = 6) {
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