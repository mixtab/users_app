package ua.com.tabarkevych.usersapp.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ua.com.tabarkevych.usersapp.common.Resource
import ua.com.tabarkevych.usersapp.data.mapper.toUserInfo
import ua.com.tabarkevych.usersapp.domain.model.UserInfo
import ua.com.tabarkevych.usersapp.domain.repository.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: UserInfoRepository
) {
    operator fun invoke(login:String): Flow<Resource<UserInfo>> = flow {
        try {
            emit(Resource.Loading())
            val userInfo = repository.getUserInfo(login).toUserInfo()
            emit(Resource.Success(userInfo))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?:"ERROR"))
        } catch (e: HttpException) {
            emit(Resource.Error("Please check your internet connection."))
        }
    }
}