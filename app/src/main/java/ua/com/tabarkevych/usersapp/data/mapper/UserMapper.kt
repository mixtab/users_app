package ua.com.tabarkevych.usersapp.data.mapper

import ua.com.tabarkevych.usersapp.data.remote.dto.UsersDto
import ua.com.tabarkevych.usersapp.domain.model.User

fun UsersDto.toUser() = User(
    login = login,
    id = id,
    avatarUrl = avatarUrl
)


fun User.toUserDto() = UsersDto(
    login = login,
    id = id,
    avatarUrl = avatarUrl
)

fun List<UsersDto>?.toUsersList(): MutableList<User> {
    if (this == null) return mutableListOf()
    val news = this.map { it.toUser() }
    return news.toMutableList()
}


fun List<User>?.toUserDtoList(): MutableList<UsersDto> {
    if (this == null) return mutableListOf()
    val news = this.map { it.toUserDto() }
    return news.toMutableList()
}