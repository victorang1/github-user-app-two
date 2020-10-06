package com.example.dicodingsubmissiontwo.pref

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {

    private val PREF_NAME = "GithubPref"
    private val REMINDER_STATUS = "reminder_status"

    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    fun switchReminder(isChecked: Boolean) {
        editor.putBoolean(REMINDER_STATUS, isChecked)
        editor.apply()
    }

    fun getReminderStatus(): Boolean = pref.getBoolean(REMINDER_STATUS, false)
}