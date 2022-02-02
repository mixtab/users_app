package ua.com.tabarkevych.usersapp.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>(data = null, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}