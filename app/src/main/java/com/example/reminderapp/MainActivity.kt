package com.example.reminderapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            // Code to add to the floating button onClickListener:
            val reminder = etReminder.text.toString()
            addReminder(reminder)

        }

        //initialize the view
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews(){
        //Initialize the recycler view with a linear layout manager adapter.
        rvReminders.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)

        //set our adapter to the recyclerView on the activity(screen)
        rvReminders.adapter = reminderAdapter

        //add a vertical divider (line/ border) at the bottom of the items in the collection
        rvReminders.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        //attach the item touch helper to the recycler view
        createItemTouchHelper().attachToRecyclerView(rvReminders)
    }


    /**
     * Function to add a new reminder to the collection/ recycleView
     */
    private fun addReminder(reminder: String){
        if(reminder.isNotBlank()){
            reminders.add(Reminder(reminder))
            reminderAdapter.notifyDataSetChanged()
            etReminder.text?.clear()
        }else{
            Snackbar.make(etReminder, "You must fill the input field!", Snackbar.LENGTH_SHORT).show()
        }
    }

    /***
     * this method is used to bind to the onSwipe and onMove method to the recycler view.
     */
    private fun createItemTouchHelper(): ItemTouchHelper{

        //create / configure the callback method on swipe left
        val callback = object :  ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                reminders.removeAt(position)
                reminderAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}
