package com.example.eduba_grades

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.eduba_grades.ui.theme.EdubaGradesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val listSpe = listOf(
            "Automatique & Systemes Embarques",
            "Genie Industriel",
            "Informatique et Reseaux",
            "Mecanique",
            "Textiles et Fibres"
        );

        setContent {
            EdubaGradesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    Column {
                        BannerItem()
                        SpeView(listSpe)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EdubaGradesTheme {
        Greeting("Android")
    }
}

@Composable
fun BannerItem() {
    Card(
        shape = RectangleShape,
        colors = CardColors(
            Color.Black,
            Color.Black,
            Color.Red,
            Color.Gray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 30.dp)
    ) {
        Text(
            text = "Eduba",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .padding(20.dp)
        )
    }
}

@Composable
fun SpeView(spe: List<String>) {
    LazyColumn {
        items(spe) {
            Card (
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
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