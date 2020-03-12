package com.example.timetracker.network

import com.example.timetracker.models.ClockInOutRespone
import com.example.timetracker.models.JobDetailsResponse
import com.example.timetracker.models.LoginRequest
import com.example.timetracker.models.LoginResponse
import io.reactivex.Observable


class ApiClient {

    fun performLogin(loginRequest:LoginRequest): Observable<LoginResponse> {
        return ServiceGenerator.instance.getRepoApi().getLoginResponse(loginRequest)
    }

    fun getWorkDetails(): Observable<JobDetailsResponse> {
        return ServiceGenerator.instance.getRepoApi().getWorkDetails()
    }

    fun updateClockIn(): Observable<ClockInOutRespone> {
        return ServiceGenerator.instance.getRepoApi().postClockIn()
    }

    fun updateClockOut(): Observable<ClockInOutRespone> {
        return ServiceGenerator.instance.getRepoApi().postClockOut()
    }

}