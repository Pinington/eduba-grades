package com.example.eduba_grades

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val listSpe : List<String> = listOf(
    "Automatique & Systemes Embarques",
    "Genie Industriel",
    "Informatique et Reseaux",
    "Mecanique",
    "Textiles et Fibres"
)

class PageViewer (
    val specialite: Int, // 0: ASE, 1: GI, 2: IR, 3: MECA, 4: TF
    val semestre: Int
)

@Composable
fun SpeView(spe: List<String> = listSpe) {
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(0.dp, 18.dp)
            .fillMaxWidth()
    ) {
        items(spe) {
            Card (
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(0.95F)
            ) {
                Text (
                    text = it,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun SemView(sem: Int = 6) {
    LazyColumn {
        items(sem) {
            Card (
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
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