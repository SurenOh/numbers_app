package com.example.myapplication.model

enum class ErrorMessage(val message: String) {
    IncorrectValue("Please type correct value!"),
    Unknown("Error! Please try again later!"),
    NoInternet("Internet is unavailable!"),
    HttpError("Oops! Something went wrong with the network. Please try again later!"),
    ServerError("Server error!")
}