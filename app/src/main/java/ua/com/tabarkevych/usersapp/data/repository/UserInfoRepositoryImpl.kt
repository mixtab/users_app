package ua.com.tabarkevych.usersapp.data.repository

import ua.com.tabarkevych.usersapp.data.remote.UsersAppApi
import ua.com.tabarkevych.usersapp.data.remote.dto.UserInfoDto
import ua.com.tabarkevych.usersapp.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val api: UsersAppApi
) : UserInfoRepository {
    override suspend fun getUserInfo(login:String): UserInfoDto {
        return api.getUserInfo(login)
    }


}