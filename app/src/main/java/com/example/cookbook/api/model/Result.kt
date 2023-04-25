package com.example.cookbook.api.model

data class Result(
    val analyzedInstructions: List<AnalyzedInstruction>,
    val diets: List<String>,
    val id: Int,
    val image: String,
    val imageType: String,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val summary: String,
    val title: String,
)