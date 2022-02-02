package ua.com.tabarkevych.usersapp.data.repository

import ua.com.tabarkevych.usersapp.data.remote.UsersAppApi
import ua.com.tabarkevych.usersapp.data.remote.dto.UsersDto
import ua.com.tabarkevych.usersapp.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: UsersAppApi
) : UsersRepository {

     override suspend fun getUsers(since:Int): List<UsersDto> {
        return api.getUsers(since)
    }

}