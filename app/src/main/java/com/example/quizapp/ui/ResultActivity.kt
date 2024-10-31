package com.example.quizapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.utils.Constans

class ResultActivity : AppCompatActivity() {
    private lateinit var finishButton : Button
    private lateinit var scoreTextView: TextView
    private lateinit var nameTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        scoreTextView = findViewById(R.id.textview_score)
        nameTextView = findViewById(R.id.textview_name)

        val totalquestion = intent.getIntExtra(Constans.totalQuestion,0)
        val score = intent.getIntExtra(Constans.score, 0)
        val name = intent.getStringExtra(Constans.usn)


        scoreTextView.text = "Your score is $score out of $totalquestion"
        nameTextView.text = name

        finishButton.setOnClickListener{
            Intent(this, MainActivity::class.java).also { startActivity(it) }
        }
    }
}