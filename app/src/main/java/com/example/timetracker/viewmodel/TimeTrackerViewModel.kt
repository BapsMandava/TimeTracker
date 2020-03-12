package com.example.timetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.timetracker.models.CheckInChekOutRequest
import com.example.timetracker.models.ClockInOutRespone
import com.example.timetracker.models.JobDetailsResponse
import com.example.timetracker.models.LoginResponse
import com.example.timetracker.repository.ApiRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TimeTrackerViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: ApiRepository
    var workDetailsResponse: MutableLiveData<JobDetailsResponse> = MutableLiveData()
    var clockInRespone: MutableLiveData<ClockInOutRespone> = MutableLiveData()
    var clockOutRespone: MutableLiveData<ClockInOutRespone> = MutableLiveData()
    private val mDisposables = CompositeDisposable()

    init {
        repository = ApiRepository(application.baseContext)
    }

    fun getWorkDetails(): LiveData<JobDetailsResponse> {
        val observable: Observable<JobDetailsResponse> = repository.getWorkDetails()
        val result = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                workDetailsResponse.postValue(result)
            }, { error ->
                error.printStackTrace()
            }, {
                //completed
            })
        mDisposables.add(result)
        return workDetailsResponse
    }

    fun updateClockIn(checkInChekOutRequest:CheckInChekOutRequest): LiveData<ClockInOutRespone> {
        val observable: Observable<ClockInOutRespone> = repository.updateClockIn(checkInChekOutRequest)
        val result = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                clockInRespone.postValue(result)
            }, { error ->
                error.printStackTrace()
            }, {
                //completed
            })
        mDisposables.add(result)
        return clockInRespone
    }

    fun updateClockOut(checkInChekOutRequest:CheckInChekOutRequest): LiveData<ClockInOutRespone> {
        val observable: Observable<ClockInOutRespone> = repository.updateClockOut(checkInChekOutRequest)
        val result = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                clockOutRespone.postValue(result)
            }, { error ->
                error.printStackTrace()
            }, {
                //completed
            })
        mDisposables.add(result)
        return clockOutRespone
    }

    override fun onCleared() {
        super.onCleared()
        mDisposables.clear() //no more leaks. It takes care of lifecycle for you
    }
}