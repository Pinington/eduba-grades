package com.example.eduba_grades

import android.content.Context
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
}

fun loadRootData(context: Context): RootData {
    val jsonString = context.assets.open("data.json")
        .bufferedReader()
        .use { it.readText() }

    return json.decodeFromString(
        RootData.serializer(),
        jsonString
    )
}