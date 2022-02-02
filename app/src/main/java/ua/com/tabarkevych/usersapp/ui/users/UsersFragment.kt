package ua.com.tabarkevych.usersapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import ua.com.tabarkevych.usersapp.databinding.FragmentUsersBinding
import ua.com.tabarkevych.usersapp.domain.model.User
import ua.com.tabarkevych.usersapp.presentation.main.UsersViewModel
import ua.com.tabarkevych.usersapp.ui.base.BaseFragment
import ua.com.tabarkevych.usersapp.ui.users.adapter.UsersAdapter
import ua.com.tabarkevych.usersapp.ui.users.adapter.UsersItemDecorator

@AndroidEntryPoint
class UsersFragment() :
    BaseFragment<FragmentUsersBinding>(), UsersAdapter.EventListener {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentUsersBinding::inflate

    private val navController by lazy { findNavController() }
    val viewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MaterialFadeThrough().let {
            enterTransition = it
            exitTransition = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initRecycler()
    }

    private fun initRecycler() {
        binding.recyclerUsers.let {
            it.addOnScrollListener(postsLoadListener)
            it.addItemDecoration(UsersItemDecorator())
            it.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            it.adapter = UsersAdapter(this)
        }
    }

    private fun subscribeObservers() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when {
                state.users != null -> {
                    showLoading(false)
                    showUsersLoading(false)
                    showUsers(state.users)
                }
                state.isLoading -> {
                    showUsersLoading(true)
                }
                state.error.isNotBlank() -> {
                    showError(state.error)
                }
            }
        })
    }

    private fun showUsers(users: List<User>) {
        val adapter = binding.recyclerUsers.adapter as UsersAdapter
        adapter.insertPosts(users)
    }


    var isLoadingUsers = false
    private val postsLoadListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (!isLoadingUsers) {
                val postsAdapter = (binding.recyclerUsers.adapter as UsersAdapter)
                val offset = postsAdapter.itemCount
                val lastVisibleItemPosition: Int =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (lastVisibleItemPosition >= offset - 4) {
                    viewModel.getUsers()
                }
            }
        }
    }

    private fun showLoading(show: Boolean) {
        isLoadingUsers = show
        binding.progressMain.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showUsersLoading(show: Boolean) {
        isLoadingUsers = show
        binding.progressNextUsers.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onUserSelected(userLogin:String) {
       navController.navigate(UsersFragmentDirections.actionUsersFragmentToUserInfoFragment().setUserLogin(userLogin))
    }
}
