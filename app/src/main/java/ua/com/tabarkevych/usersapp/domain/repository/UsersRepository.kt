package ua.com.tabarkevych.usersapp.domain.repository

import ua.com.tabarkevych.usersapp.data.remote.dto.UsersDto

interface UsersRepository {
    suspend fun getUsers(since:Int):List<UsersDto>

}