package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question

object Constans {

    const val usn = "username"
    const val totalQuestion = "total_question"
    const val score = "correct_answers"

    fun getQuestions(): MutableList<Question>{
        val questions = mutableListOf<Question>()

        val quest1 = Question(
            1, "What Country does this flag belong?", R.drawable.flag_indonesia,
            "Indonesia", "Malaysia","Vietnam", "Thailand", 1
        )
        questions.add(quest1)

        val quest2 = Question(
            2, "What Country does this flag belong?", R.drawable.flag_thailand,
             "Malaysia","Vietnam", "Thailand","Indonesia", 3
        )
        questions.add(quest2)

        val quest3 = Question(
            3, "What Country does this flag belong?", R.drawable.flag_vietnam,
            "Indonesia", "Malaysia","Vietnam", "Thailand", 3
        )
        questions.add(quest3)

        val quest4 = Question(
            4, "What Country does this flag belong?", R.drawable.flag_malaysia,
            "Indonesia", "Malaysia","Vietnam", "Thailand", 2
        )
        questions.add(quest4)

        val quest5 = Question(
            5, "What Country does this flag belong?", R.drawable.flag_germany,
            "England", "Germany","Spain", "Portugal", 2
        )
        questions.add(quest5)

        val quest6 = Question(
            6, "What Country does this flag belong?", R.drawable.flag_portugal,
            "England", "Germany","Spain", "Portugal", 4
        )
        questions.add(quest6)

        val quest7 = Question(
            7, "What Country does this flag belong?", R.drawable.flag_spain,
            "England", "Germany","Spain", "Portugal", 3
        )
        questions.add(quest7)

        val quest8 = Question(
            8, "What Country does this flag belong?", R.drawable.flag_england,
            "England", "Germany","Spain", "Portugal", 1
        )
        questions.add(quest8)

        val quest9 = Question(
            9, "What Country does this flag belong?", R.drawable.flag_japan,
            "Malaysia", "Japan","Spain", "Belgium", 2
        )
        questions.add(quest9)

        val quest10 = Question(
            10, "What Country does this flag belong?", R.drawable.flag_belgium,
            "England", "Belgium","Spain", "Japan", 2
        )
        questions.add(quest10)

        return questions
    }
}