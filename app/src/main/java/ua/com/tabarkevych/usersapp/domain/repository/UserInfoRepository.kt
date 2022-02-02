package ua.com.tabarkevych.usersapp.domain.repository

import ua.com.tabarkevych.usersapp.data.remote.dto.UserInfoDto

interface UserInfoRepository {
    suspend fun getUserInfo(login:String): UserInfoDto

}