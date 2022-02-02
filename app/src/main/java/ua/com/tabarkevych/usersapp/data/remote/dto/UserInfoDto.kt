package ua.com.tabarkevych.usersapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class UserInfoDto(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("public_repos") val publicRepos: Int,
    @SerializedName("public_gists") val publicGists: Int,
    @SerializedName("followers") val followers: Int,
)
