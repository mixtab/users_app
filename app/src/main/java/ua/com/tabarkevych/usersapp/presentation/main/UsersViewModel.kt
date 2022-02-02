package ua.com.tabarkevych.usersapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ua.com.tabarkevych.usersapp.common.Resource
import ua.com.tabarkevych.usersapp.domain.use_case.GetUsersUseCase
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val loadUsers: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UsersState>()
    val state: LiveData<UsersState> = _state


    init {
        getUsers()
    }

    private var since: Int = 0

    fun getUsers() {
        loadUsers(since).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UsersState(users = result.data ?: null)
                    result.data?.let { since = it.last().id }
                }
                is Resource.Error -> {
                    _state.value = UsersState(error = result.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = UsersState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}