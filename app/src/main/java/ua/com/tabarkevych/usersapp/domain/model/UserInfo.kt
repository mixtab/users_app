package ua.com.tabarkevych.usersapp.domain.model

class UserInfo(
    val login: String = "",
    val avatarUrl: String = "",
    val htmlUrl:String = "",
    val name: String = "",
    val publicRepos: Int = 0,
    val publicGists: Int = 0,
    val followers: Int = 0,
)