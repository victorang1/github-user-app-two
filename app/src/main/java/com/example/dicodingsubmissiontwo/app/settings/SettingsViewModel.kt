package com.example.dicodingsubmissiontwo.app.settings

import androidx.lifecycle.ViewModel
import com.example.dicodingsubmissiontwo.pref.AppPreferences

class SettingsViewModel(private val appPreferences: AppPreferences): ViewModel() {

    fun isReminderChecked(): Boolean {
        return appPreferences.getReminderStatus()
    }

    fun saveReminderStatus(isChecked: Boolean) {
        appPreferences.switchReminder(isChecked)
    }
}