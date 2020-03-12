package com.example.timetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.timetracker.models.ClockInOutRespone
import com.example.timetracker.models.JobDetailsResponse
import com.example.timetracker.models.LoginRequest
import com.example.timetracker.models.LoginResponse
import com.example.timetracker.repository.ApiRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: ApiRepository
    var loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    private val mDisposables = CompositeDisposable()

    init {
        repository = ApiRepository(application.baseContext)
    }

    fun perforLogin(loginRequest:LoginRequest): LiveData<LoginResponse> {
        val observable: Observable<LoginResponse> = repository.performLogin(loginRequest)
        val result = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                loginResponse.postValue(result)
            }, { error ->
                error.printStackTrace()
            }, {
                //completed
            })
        mDisposables.add(result)
        return loginResponse
    }


    override fun onCleared() {
        super.onCleared()
        mDisposables.clear() //no more leaks. It takes care of lifecycle for you
    }
}