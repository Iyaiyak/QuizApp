package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constans
import kotlinx.coroutines.selects.select

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView

    private lateinit var checkButton: Button

    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView
    private lateinit var optionFour: TextView
    private var questionCounter = 0
    private var score = 0

    private lateinit var name: String

    //  private val currentPosition =1
    private lateinit var questionList: MutableList<Question>
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        progressBar = findViewById(R.id.progressBar)
        textViewProgress = findViewById(R.id.textView_progress)
        textViewQuestion = findViewById(R.id.textView_question)
        flagImage = findViewById(R.id.flag_image)
        optionOne = findViewById(R.id.textview_option_1)
        optionTwo = findViewById(R.id.textview_option_2)
        optionThree = findViewById(R.id.textview_option_3)
        optionFour = findViewById(R.id.textview_option_4)
        checkButton = findViewById(R.id.button_check)

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        checkButton.setOnClickListener(this)

        questionList = Constans.getQuestions()

        val questionsList = Constans.getQuestions()
        Log.d("QuestionSize", "${questionsList.size}")

        showNextQuestion()

        if (intent.hasExtra(Constans.usn)) {
            name = intent.getStringExtra(Constans.usn)!!

        }
    }

    private fun showNextQuestion() {
        val question = questionList[questionCounter]


        if (questionCounter < questionList.size) {
            checkButton.text = "CHECK"
            currentQuestion = questionList[questionCounter]

            resetOpt()

            flagImage.setImageResource(question.image)
            progressBar.progress = questionCounter
            textViewProgress.text = "${questionCounter + 1} / ${progressBar.max}"
            textViewQuestion.text = question.question
            optionOne.text = question.optionOne
            optionTwo.text = question.optionTwo
            optionThree.text = question.optionThree
            optionFour.text = question.optionFour

        } else {
            checkButton.text = "FINISH"
            Intent(this, ResultActivity::class.java).also {
                it.putExtra(Constans.usn, name)
                it.putExtra(Constans.score, score)
                it.putExtra(Constans.totalQuestion, questionList.size)

                startActivity(it)
            }
        }


        questionCounter++
        answered = false

    }

    private fun resetOpt() {
        val options = mutableListOf<TextView>()
        options.add(optionOne)
        options.add(optionTwo)
        options.add(optionThree)
        options.add(optionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_border_option_bg
            )
        }
    }

    private fun selectedOption(tv: TextView, select: Int) {
        resetOpt()

        selectedAnswer = select

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.select_border_option_bg
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textview_option_1 -> {
                selectedOption(optionOne, 1)
            }

            R.id.textview_option_2 -> {
                selectedOption(optionTwo, 2)
            }

            R.id.textview_option_3 -> {
                selectedOption(optionThree, 3)
            }

            R.id.textview_option_4 -> {
                selectedOption(optionFour, 4)
            }

            R.id.button_check -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer = 0
            }
        }
    }

    private fun checkAnswer() {
        answered = true


        if (selectedAnswer == currentQuestion.correctAnswer) {
            highlightAnswer(selectedAnswer)
            score++

        } else {
            when (selectedAnswer) {
                1 -> {
                    optionOne.background =
                        ContextCompat.getDrawable(this, R.drawable.wrong_border_option_bg)
                }

                2 -> {
                    optionTwo.background =
                        ContextCompat.getDrawable(this, R.drawable.wrong_border_option_bg)
                }

                3 -> {
                    optionThree.background =
                        ContextCompat.getDrawable(this, R.drawable.wrong_border_option_bg)
                }

                4 -> {
                    optionFour.background =
                        ContextCompat.getDrawable(this, R.drawable.wrong_border_option_bg)
                }
            }
        }
        checkButton.text = "NEXT"
        showSolution()
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer
        highlightAnswer(selectedAnswer)
    }

    private fun highlightAnswer(answer: Int) {
        when (answer) {
            1 -> {
                optionOne.background =
                    ContextCompat.getDrawable(this, R.drawable.correct_border_option_bg)
            }

            2 -> {
                optionTwo.background =
                    ContextCompat.getDrawable(this, R.drawable.correct_border_option_bg)
            }

            3 -> {
                optionThree.background =
                    ContextCompat.getDrawable(this, R.drawable.correct_border_option_bg)
            }

            4 -> {
                optionFour.background =
                    ContextCompat.getDrawable(this, R.drawable.correct_border_option_bg)
            }
        }
    }
}