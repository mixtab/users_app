package ua.com.tabarkevych.usersapp.presentation.user_info

import ua.com.tabarkevych.usersapp.domain.model.UserInfo

class UserInfoState(
    val isLoading: Boolean = false,
    val userInfo: UserInfo? = null,
    val error: String = ""
)