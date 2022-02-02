package ua.com.tabarkevych.usersapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UsersDto(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("id") val id: Int,
)

