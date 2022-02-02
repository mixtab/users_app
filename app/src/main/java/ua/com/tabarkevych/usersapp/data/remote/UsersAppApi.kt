package ua.com.tabarkevych.usersapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ua.com.tabarkevych.usersapp.data.remote.dto.UsersDto
import ua.com.tabarkevych.usersapp.data.remote.dto.UserInfoDto

interface UsersAppApi {

    @GET("/users")
    suspend fun getUsers(
        @Query("since") amount: Int
    ): List<UsersDto>

    @GET("/users/{login}")
    suspend fun getUserInfo(
        @Path("login") login: String
    ): UserInfoDto
}