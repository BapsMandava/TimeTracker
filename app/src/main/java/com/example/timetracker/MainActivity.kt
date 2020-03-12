package com.example.timetracker

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.timetracker.databinding.ActivityMainBinding
import com.example.timetracker.models.CheckInChekOutRequest
import com.example.timetracker.models.LoginRequest
import com.example.timetracker.models.LoginResponse
import com.example.timetracker.network.ServiceGenerator
import com.example.timetracker.viewmodel.LoginViewModel
import com.example.timetracker.viewmodel.TimeTrackerViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import java.util.*


class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding;

    companion object {
        val TAG = LoginActivity::class.java.simpleName
    }

    private lateinit var timeTrackerViewModel: TimeTrackerViewModel

    private lateinit var loginResponse: LoginResponse
    lateinit var checkInChekOutRequest: CheckInChekOutRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.executePendingBindings()
        ServiceGenerator(this)
        initialiseViewModel()
        getJobDetails()
        circle_button.setOnClickListener {
            checkInChekOutRequest = CheckInChekOutRequest("-6.2446691", "106.8779625")

            var bufferScreen = Intent(this, BufferActivity::class.java)

            if ((it.circle_button.text).equals("Check In")) {
                bufferScreen.putExtra("Type",0)
                startActivityForResult(bufferScreen,10)
               // clockInClockOutBufferScreen(0)
            } else {
              //  clockInClockOutBufferScreen(1)
                bufferScreen.putExtra("Type",1)
                startActivityForResult(bufferScreen,20)
            }

        }
    }

    private fun initialiseViewModel() {
        timeTrackerViewModel =
            ViewModelProviders.of(this).get(TimeTrackerViewModel(application)::class.java)
    }

    private fun getJobDetails() {
        showProgressBar(true)
        timeTrackerViewModel.getWorkDetails().observe(this, Observer { repoList ->
            Log.i(LoginActivity.TAG, "Viewmodel response: $repoList")
            repoList?.let {
                showProgressBar(false)
                binding.jobDetails = it
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateCheckIn() {
        showProgressBar(true)
        timeTrackerViewModel.updateClockIn(checkInChekOutRequest)
            .observe(this, Observer { repoList ->
                Log.i(LoginActivity.TAG, "Viewmodel response: $repoList")
                repoList?.let {
                    showProgressBar(false)
                    if (it != null) {
                        circle_button.setText("Clock Out")
                        txtClockInValue.setText(it.clock_in_time)
                    }
                }
            })
    }

    private fun updateCheckOut() {
        showProgressBar(true)
        timeTrackerViewModel.updateClockOut(checkInChekOutRequest)
            .observe(this, Observer { repoList ->
                Log.i(LoginActivity.TAG, "Viewmodel response: $repoList")
                repoList?.let {
                    showProgressBar(false)
                    if (it != null) {
                        circle_button.setText("Clock In")
                    }
                }
            })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == 0){
            updateCheckIn()
        }else if(resultCode == 1){
            updateCheckOut()
        }
    }


}
