package com.example.timetracker.models

data class JobDetailsResponse(
    val id : Int,
    val created_date : String,
    val modified_date : String,
    val status : String,
    val uid : Int,
    val title : String,
    val cover_image : String,
    val description : String,
    val wage_amount : String,
    val wage_type : String,
    val staff_required : Int,
    val timezone : String,
    val gender : String,
    val min_age : Int,
    val max_age : Int,
    val require_experience : Boolean,
    val require_english : Boolean,
    val interview_time : String,
    val interview_info : String,
    val offer_statistics : Offer_statistics,
    val offer_counts : Offer_counts,
    val application_counts : Application_counts,
    val employment_counts : Employment_counts,
    val statistics : Statistics,
    val start_time : String,
    val end_time : String,
    val fixed_location : Boolean,
    val schedules : List<Schedules>,
    val client : Client,
    val location : Location,
    val position : Position,
    val manager : Manager
)

data class Address(
    val area: Area,
    val country: CountryXX,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val point: String,
    val province: Any,
    val street_1: String,
    val street_2: String,
    val zip: String
)

data class Manager (

    val id : Int,
    val name : String,
    val email : String,
    val phone : String,
    val role : String,
    val locations : List<String>
)

data class Schedules (

    val id : Int,
    val staff_request : Int,
    val recurrences : String,
    val start_date : String,
    val start_time : String,
    val end_time : String,
    val duration : String
)


data class Statistics (

    val no_show : Int
)


data class Employment_counts (

    val active : Int,
    val cancelled : Int,
    val ended : Int
)

data class Application_counts (

    val pending_onboarding : Int,
    val applied : Int,
    val pending_contract : Int,
    val rejected : Int,
    val withdrawn : Int,
    val hired : Int
)

data class Offer_counts (

    val sent : Int,
    val viewed : Int,
    val applied : Int,
    val pending_onboarding : Int,
    val pending_contract : Int,
    val confirmed : Int,
    val withdrawn : Int,
    val rejected : Int,
    val cancelled : Int,
    val no_show : Int,
    val contract_ended : Int
)

data class Offer_statistics (

    val sent : Int,
    val viewed : Int,
    val applied : Int,
    val confirmed : Int
)

data class Area(
    val city: City,
    val id: Int,
    val name: String
)

data class City(
    val country: CountryX,
    val id: Int,
    val name: String,
    val timezone: String
)

data class Client(
    val country: Country,
    val description: String,
    val id: Int,
    val logo: Any,
    val name: String,
    val status: String,
    val tier: String,
    val website: String
)

data class Country(
    val code: String,
    val currency_code: String,
    val dial_code: String,
    val id: Int,
    val name: String
)


data class CountryX(
    val code: String,
    val currency_code: String,
    val dial_code: String,
    val id: Int,
    val name: String
)

data class CountryXX(
    val code: String,
    val currency_code: String,
    val dial_code: String,
    val id: Int,
    val name: String
)


data class Location(
    val address: Address,
    val created_date: String,
    val id: Int,
    val modified_date: String,
    val name: String
)

data class Position(
    val active: Boolean,
    val id: Int,
    val name: String
)
