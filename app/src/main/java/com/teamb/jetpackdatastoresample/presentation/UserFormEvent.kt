package com.teamb.jetpackdatastoresample.presentation

sealed class UserFormEvent {
    data class FirstNameChanged(val firstName: String) : UserFormEvent()
    data class LastNameChanged(val lastName: String) : UserFormEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : UserFormEvent()
    data class EmailChanged(val email: String) : UserFormEvent()
    object Submit : UserFormEvent()
}
