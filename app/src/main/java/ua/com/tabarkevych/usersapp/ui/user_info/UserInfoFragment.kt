package ua.com.tabarkevych.usersapp.ui.user_info

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import ua.com.tabarkevych.usersapp.R
import ua.com.tabarkevych.usersapp.databinding.FragmentUserInfoBinding
import ua.com.tabarkevych.usersapp.domain.model.UserInfo
import ua.com.tabarkevych.usersapp.extensions.loadUserInfoImage
import ua.com.tabarkevych.usersapp.presentation.user_info.UserInfoViewModel
import ua.com.tabarkevych.usersapp.ui.base.BaseFragment

@AndroidEntryPoint
class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentUserInfoBinding::inflate

    val viewModel: UserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }


    private fun subscribeObservers() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when {
                state.userInfo != null -> {
                    setupToolbar(state.userInfo.name)
                    showUserInfo(state.userInfo)
                    showLoading(false)
                }
                state.isLoading -> {
                    showLoading(true)
                }
                state.error.isNotBlank() -> {
                    showError(state.error)
                }
            }
        })
    }

    private fun setupToolbar(userName: String) {
        binding.toolbar.apply {
            title = userName
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun showUserInfo(userInfo: UserInfo) {
        with(binding) {
            imageUser.loadUserInfoImage(userInfo.avatarUrl)
            textUserName.text = userInfo.name
            textUserUrl.apply {
                text = userInfo.htmlUrl
                setOnClickListener {
                    try {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(userInfo.htmlUrl)
                            )
                        )
                    } catch (ignore: ActivityNotFoundException) {
                        Toast.makeText(context, resources.getText(R.string.user_info_url_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            textRepos.text =
                resources.getString(R.string.user_info_repos, userInfo.publicRepos)
            textGists.text =
                resources.getString(R.string.user_info_gists, userInfo.publicGists)
            textFollowers.text =
                resources.getString(R.string.user_info_followers, userInfo.followers)
        }
    }

    private fun showLoading(show: Boolean) {
        binding.progressMain.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }


}
