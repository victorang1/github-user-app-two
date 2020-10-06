package com.example.dicodingsubmissiontwo.app.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.databinding.ActivitySettingsBinding
import com.example.dicodingsubmissiontwo.receiver.AlarmReceiver
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var mBinding: ActivitySettingsBinding
    private lateinit var alarmReceiver: AlarmReceiver
    private val mViewModel: SettingsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        alarmReceiver = AlarmReceiver()
        val isChecked = mViewModel.isReminderChecked()
        mBinding.switchReminder.isChecked = isChecked
        initializeListener()
    }

    private fun initializeListener() {
        mBinding.switchReminder.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            alarmReceiver.setRepeatingAlarm(this)
        }
        else {
            alarmReceiver.cancelAlarm(this)
        }
        mViewModel.saveReminderStatus(isChecked)
    }
}