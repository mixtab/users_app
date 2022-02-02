package ua.com.tabarkevych.usersapp.presentation.user_info

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ua.com.tabarkevych.usersapp.common.Resource
import ua.com.tabarkevych.usersapp.domain.use_case.GetUserInfoUseCase
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val loadUserInfo: GetUserInfoUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData<UserInfoState>()
    val state: LiveData<UserInfoState> = _state



    init {
        val login: String? = savedStateHandle["user_login"]
        login?.let {
            getUserInfo(login)
        }
    }

    private fun getUserInfo(userLogin: String) {
        loadUserInfo(userLogin).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserInfoState(userInfo = result.data ?: null)
                }
                is Resource.Error -> {
                    _state.value = UserInfoState(error = result.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = UserInfoState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}