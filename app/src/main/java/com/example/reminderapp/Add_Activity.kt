package com.example.reminderapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction

import kotlinx.android.synthetic.main.activity_add_.*
import kotlinx.android.synthetic.main.content_add_.*

const val EXTRA_REMINDER = "EXTRA_REMINDER"

class Add_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            onSaveClick()
        }
    }

    private fun onSaveClick() {
        //if the input is empty show an error
        if (etAddReminder.text.isBlank()) {
            Toast.makeText(
                this, "The reminder cannot be empty"
                , Toast.LENGTH_SHORT
            ).show()
        }

        //create a reminder object
        var reminder = Reminder(etAddReminder.text.toString())

        //create the intent
        var resultIntent = Intent()
        resultIntent.putExtra(EXTRA_REMINDER, reminder)

        //set/return the result of the activity
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

}
