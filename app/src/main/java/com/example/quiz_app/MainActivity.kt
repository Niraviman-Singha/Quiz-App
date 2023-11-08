package com.example.quiz_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quiz_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val questions = arrayOf(
        "Founders of Firebase?",
        "What is the full form of FCM in firebase?",
        "Choose the build-in database in Android"
    )

    private val options = arrayOf(
        arrayOf("Jaismin Tamplin","Richard","James Tamplin"),
        arrayOf("Firebase Cloud Merge","Firebase Cloud Messaging"," Flexible Cloud Messaging"),
        arrayOf("MySQL","Oracle","SQLite")
    )

    private val correctAnswers = arrayOf(2,1,2)

    private var currentQuestionIndex = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        displayQuestions()

        binding.option1Button.setOnClickListener {
            checkAnswers(0)
        }

        binding.option2Button.setOnClickListener {
            checkAnswers(1)
        }

        binding.option3Button.setOnClickListener {
            checkAnswers(2)
        }
        binding.restartButton.setOnClickListener {
            restartQuiz()
        }
    }

    private fun correctButtonColors(buttonIndex:Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex:Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))

    }

    private fun showResults(){
        Toast.makeText(this,"Your score :$score out of ${questions.size}",Toast.LENGTH_SHORT).show()
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestions(){

        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = options[currentQuestionIndex][2]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColors()

    }

    private fun checkAnswers(selectedAnswerIndex:Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if(selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)

        }
        else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        if (currentQuestionIndex < questions.size - 1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestions()},1000)
        }
        else{
            showResults()
        }

    }
    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestions()
        binding.restartButton.isEnabled = false
    }
}