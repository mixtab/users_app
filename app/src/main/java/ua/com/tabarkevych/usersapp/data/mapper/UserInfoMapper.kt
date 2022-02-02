package ua.com.tabarkevych.usersapp.data.mapper

import ua.com.tabarkevych.usersapp.data.remote.dto.UserInfoDto
import ua.com.tabarkevych.usersapp.domain.model.UserInfo

fun UserInfoDto.toUserInfo() = UserInfo(
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    name = name,
    publicRepos = publicRepos,
    publicGists = publicGists,
    followers = followers
)


fun UserInfo.toUserInfoDto() = UserInfoDto(
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    name = name,
    publicRepos = publicRepos,
    publicGists = publicGists,
    followers = followers
)