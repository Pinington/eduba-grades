package com.example.eduba_grades

class UniteEnseignement (
    val nom : String,
    val matieres : List<Pair<String, Int>>
)
{
    fun comppute(): List<Pair<String, Int>> {
        return listOf(Pair("A", 1))
    }
}