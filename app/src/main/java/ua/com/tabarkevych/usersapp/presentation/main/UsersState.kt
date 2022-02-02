package ua.com.tabarkevych.usersapp.presentation.main

import ua.com.tabarkevych.usersapp.domain.model.User

class UsersState(
    val isLoading: Boolean = false,
    val users: List<User>? = null,
    val error: String = ""
)