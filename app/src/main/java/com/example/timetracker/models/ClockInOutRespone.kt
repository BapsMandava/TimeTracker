package com.example.timetracker.models

data class ClockInOutRespone(
    val clock_in_latitude: String,
    val clock_in_longitude: String,
    val clock_in_time: String,
    val clock_out_latitude: Any,
    val clock_out_longitude: Any,
    val clock_out_time: Any,
    val id: Int,
    val notes: Any,
    val partner: Partner,
    val schedule: Any,
    val staff_request: StaffRequest,
    val status: String,
    val timesheet: Int
)

data class StaffRequest(
    val client: Client,
    val id: Int,
    val location: Location,
    val position: Position,
    val status: String,
    val title: String,
    val uid: String
)

data class Partner(
    val first_name: String,
    val id: Int,
    val image: String,
    val last_name: String,
    val mobile: String
)

data class CheckInChekOutRequest(
    var latitude: String,
    val longitude: String
)

data class ClockOutResponse (
    val timesheet : Timesheet,
    val require_feedback : Boolean
)

data class Timesheet (

    val id : Int,
    val clock_in_time : String,
    val clock_out_time : String,
    val clock_in_latitude : Double,
    val clock_in_longitude : Double,
    val clock_out_latitude : Double,
    val clock_out_longitude : Double,
    val partner : Partner,
    val timesheet : Int,
    val status : String,
    val notes : String,
    val staff_request : Staff_request,
    val schedule : String
)


data class Staff_request (

    val id : Int,
    val uid : String,
    val status : String,
    val title : String,
    val location : Location,
    val client : Client,
    val position : Position
)