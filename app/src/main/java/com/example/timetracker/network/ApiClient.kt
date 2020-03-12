package com.example.timetracker.network

import com.example.timetracker.MyApplication
import com.example.timetracker.models.*
import io.reactivex.Observable


class ApiClient {

    fun performLogin(loginRequest:LoginRequest): Observable<LoginResponse> {
        return ServiceGenerator.instance.getRepoApi().getLoginResponse(loginRequest)
    }

    fun getWorkDetails(): Observable<JobDetailsResponse> {
        return ServiceGenerator.instance.getRepoApi().getWorkDetails("Token " +MyApplication.key)
    }

    fun updateClockIn(checkInChekOutRequest: CheckInChekOutRequest): Observable<ClockInOutRespone> {
        return ServiceGenerator.instance.getRepoApi().postClockIn(checkInChekOutRequest,"Token " +MyApplication.key)
    }

    fun updateClockOut(checkInChekOutRequest: CheckInChekOutRequest): Observable<ClockInOutRespone> {
        return ServiceGenerator.instance.getRepoApi().postClockOut(checkInChekOutRequest,"Token " +MyApplication.key)
    }

}