package com.example.eduba_grades

import kotlinx.serialization.Serializable

@Serializable
data class Module(
    val name: String,
    val value: Int
)
@Serializable
data class UniteEnseignement(
    val name: String,
    val modules: List<Module>
)
@Serializable
data class RootData(
    val ue_ase: Map<String, List<UniteEnseignement>> = emptyMap(),
    val ue_gi: Map<String, List<UniteEnseignement>> = emptyMap(),
    val ue_ir: Map<String, List<UniteEnseignement>> = emptyMap(),
    val ue_meca: Map<String, List<UniteEnseignement>> = emptyMap(),
    val ue_tf: Map<String, List<UniteEnseignement>> = emptyMap()
)