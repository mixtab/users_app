package ua.com.tabarkevych.usersapp.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ua.com.tabarkevych.usersapp.common.Resource
import ua.com.tabarkevych.usersapp.data.mapper.toUsersList
import ua.com.tabarkevych.usersapp.domain.model.User
import ua.com.tabarkevych.usersapp.domain.repository.UsersRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    operator fun invoke(since:Int): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading())
            val users = repository.getUsers(since).toUsersList()
            emit(Resource.Success(users))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?:"ERROR"))
        } catch (e: HttpException) {
            emit(Resource.Error("Please check your internet connection."))
        }
    }
}