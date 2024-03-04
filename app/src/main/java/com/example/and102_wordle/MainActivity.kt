package com.example.and102_wordle

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.and102_wordle.R.id.textInputter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    var finalAnswer = FourLetterWordList.getRandomFourLetterWord()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var counter = 0

        val guessEntry = findViewById<TextInputEditText>(R.id.textInputter)
        val guessButton = findViewById<Button>(R.id.guessButton)
        val answer = findViewById<TextView>(R.id.answer)
        val guessDisplay2 = findViewById<TextView>(R.id.guessTrackerDisplay2)
        val guessDisplay3 = findViewById<TextView>(R.id.guessTrackerDisplay3)
        val guessDisplay4 = findViewById<TextView>(R.id.guessTrackerDisplay4)
        val guessDisplay5 = findViewById<TextView>(R.id.guessTrackerDisplay5)
        val guessDisplay6 = findViewById<TextView>(R.id.guessTrackerDisplay6)
        val guessTracker = findViewById<TextView>(R.id.displayGuess)
        val guessTracker2 = findViewById<TextView>(R.id.displayGuess2)
        val guessTracker3 = findViewById<TextView>(R.id.displayGuess3)
        val guessTracker4 = findViewById<TextView>(R.id.displayGuess4)
        val guessTracker5 = findViewById<TextView>(R.id.displayGuess5)
        val guessTracker6 = findViewById<TextView>(R.id.displayGuess6)

        answer.text = finalAnswer

        guessButton.setOnClickListener{
            hideKeyboard()
            if(guessEntry.text?.all { it.isLetter() } == true && guessEntry.text?.length == 4 || guessButton.text == "RESTART!"){
                counter++
                if(counter == 1) {
                    guessTracker.text = guessEntry.text
                    guessDisplay2.visibility = View.VISIBLE
                    guessTracker2.text = checkGuess(guessEntry.text.toString())
                    guessTracker2.visibility = View.VISIBLE
                    guessEntry.setText("")
                } else if(counter == 2){
                    guessTracker3.text = guessEntry.text
                    guessTracker3.visibility = View.VISIBLE
                    guessDisplay3.visibility = View.VISIBLE
                    guessDisplay4.visibility = View.VISIBLE
                    guessTracker4.text = checkGuess(guessEntry.text.toString())
                    guessTracker4.visibility = View.VISIBLE
                    guessEntry.setText("")
                } else if (counter == 3){
                    guessTracker5.text = guessEntry.text
                    guessTracker5.visibility = View.VISIBLE
                    guessDisplay5.visibility = View.VISIBLE
                    guessDisplay6.visibility = View.VISIBLE
                    guessTracker6.text = checkGuess(guessEntry.text.toString())
                    guessTracker6.visibility = View.VISIBLE
                    answer.visibility = View.VISIBLE
                    guessButton.text = "RESTART!"
                    guessEntry.setText("")
                } else{
                    counter = 0
                    answer.visibility = View.INVISIBLE
                    finalAnswer = FourLetterWordList.getRandomFourLetterWord()
                    answer.text = finalAnswer
                    guessButton.text = "GUESS!"
                    guessTracker.text = "____"
                    guessDisplay2.visibility = View.INVISIBLE
                    guessTracker2.text = "____"
                    guessTracker2.visibility = View.INVISIBLE
                    guessDisplay3.visibility = View.INVISIBLE
                    guessTracker3.text = "____"
                    guessTracker3.visibility = View.INVISIBLE
                    guessDisplay4.visibility = View.INVISIBLE
                    guessTracker4.text = "____"
                    guessTracker4.visibility = View.INVISIBLE
                    guessDisplay5.visibility = View.INVISIBLE
                    guessTracker5.text = "____"
                    guessTracker5.visibility = View.INVISIBLE
                    guessDisplay6.visibility = View.INVISIBLE
                    guessTracker6.text = "____"
                    guessTracker6.visibility = View.INVISIBLE
                    guessEntry.setText("")
                }
            } else {
                guessEntry.setText("")
                Toast.makeText(this, "Enter a Four-Letter Guess!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            val wordToGuess = finalAnswer
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}