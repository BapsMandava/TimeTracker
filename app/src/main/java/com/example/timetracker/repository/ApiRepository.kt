package com.example.timetracker.repository

import android.content.Context
import com.example.timetracker.models.*
import com.example.timetracker.network.ApiClient
import com.example.timetracker.utils.ConnectivityUtil

import io.reactivex.Observable


class ApiRepository(private val context: Context) {


    // The ViewModel maintains a reference to the repository to get data.
    private var repoApiClient: ApiClient = ApiClient()

    fun performLogin(loginRequest:LoginRequest): Observable<LoginResponse> {
        val hasConnection = ConnectivityUtil.isNetworkAvailable(context)
        var observableFromApi: Observable<LoginResponse>? = null
        if (hasConnection) {
            observableFromApi = performLoginFromApi(loginRequest)
        }

        return Observable.concatArrayEager(observableFromApi)
    }

    fun getWorkDetails(): Observable<JobDetailsResponse> {
        val hasConnection = ConnectivityUtil.isNetworkAvailable(context)
        var observableFromApi: Observable<JobDetailsResponse>? = null
        if (hasConnection) {
            observableFromApi = getWorkDetailsFromApi()
        }

        return Observable.concatArrayEager(observableFromApi)
    }

    fun updateClockIn(checkInChekOutRequest:CheckInChekOutRequest): Observable<ClockInOutRespone> {
        val hasConnection = ConnectivityUtil.isNetworkAvailable(context)
        var observableFromApi: Observable<ClockInOutRespone>? = null
        if (hasConnection) {
            observableFromApi = updateClockInFromApi(checkInChekOutRequest)
        }

        return Observable.concatArrayEager(observableFromApi)
    }

    fun updateClockOut(checkInChekOutRequest: CheckInChekOutRequest): Observable<ClockInOutRespone> {
        val hasConnection = ConnectivityUtil.isNetworkAvailable(context)
        var observableFromApi: Observable<ClockInOutRespone>? = null
        if (hasConnection) {
            observableFromApi = updateClockOutFromApi(checkInChekOutRequest)
        }

        return Observable.concatArrayEager(observableFromApi)
    }

    private fun performLoginFromApi(loginRequest:LoginRequest): Observable<LoginResponse> {
        return repoApiClient.performLogin(loginRequest)
    }


    private fun getWorkDetailsFromApi(): Observable<JobDetailsResponse> {
        return repoApiClient.getWorkDetails()
    }


    private fun updateClockInFromApi(checkInChekOutRequest: CheckInChekOutRequest): Observable<ClockInOutRespone> {
        return repoApiClient.updateClockIn(checkInChekOutRequest)
    }


    private fun updateClockOutFromApi(checkInChekOutRequest: CheckInChekOutRequest): Observable<ClockInOutRespone> {
        return repoApiClient.updateClockOut(checkInChekOutRequest)
    }

}