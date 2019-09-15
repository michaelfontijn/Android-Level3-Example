package com.example.reminderapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//TODO IDE is complaining but it still compiles, this is a know bug with the Parcelize..
@Parcelize
data class Reminder (
    var reminder: String
)  : Parcelable