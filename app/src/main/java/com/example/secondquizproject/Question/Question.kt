package com.example.secondquizproject.Question

data class Question(
    val question:String,
    val option1:String,
    val option2: String,
    val option3: String,
    val answer:Int,
    var answerSelected: Int?
)
