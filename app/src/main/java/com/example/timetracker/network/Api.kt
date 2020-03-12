package com.example.timetracker.network

import io.reactivex.Observable
import android.R.id
import com.example.timetracker.models.*
import com.example.timetracker.utils.Constants
import org.json.JSONObject
import retrofit2.http.*


interface Api {
    @POST(value = Constants.LOGIN)
    fun getLoginResponse(@Body body: LoginRequest): Observable<LoginResponse>


    @GET(value = Constants.GET_WORK_DETAILS)
    fun getWorkDetails(@Header("Authorization") Token : String): Observable<JobDetailsResponse>

     @POST(value = Constants.POST_CLOCK_IN)
    fun postClockIn(@Body body: CheckInChekOutRequest,@Header("Authorization") Token : String): Observable<ClockInOutRespone>

     @POST(value = Constants.POST_CLOCK_OUT)
    fun postClockOut(@Body body: CheckInChekOutRequest,@Header("Authorization") Token : String): Observable<ClockInOutRespone>

/*    @POST(value = Constants.SELECTION_ID)
    fun postSelectionID(@Query("id", encoded = true) id: String): Observable<String>*/
}
