package com.example.eduba_grades

import kotlinx.serialization.Serializable

@Serializable
data class UniteEnseignement(
    val name: String,
    val modules: List<Module>
) {
    fun compute(): List<Pair<String, Int>> {
        return listOf(Pair("A", 1))
    }
}