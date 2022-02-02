package ua.com.tabarkevych.usersapp.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.com.tabarkevych.usersapp.databinding.ItemUserBinding
import ua.com.tabarkevych.usersapp.domain.model.User
import ua.com.tabarkevych.usersapp.extensions.loadCircleImage

open class UsersAdapter(
    val listener: EventListener
) : ListAdapter<User, UsersAdapter.UserViewHolder>(UsersDiffCallback) {

    interface EventListener {
        fun onUserSelected(userLogin:String)
    }

    private var users: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(vh: UserViewHolder, position: Int) {
        with(vh.binding) {
            val user = users[vh.adapterPosition]
            imageUser.loadCircleImage(user.avatarUrl, progressImage)
            textUserLogin.text = user.login
            cardViewUser.setOnClickListener { listener.onUserSelected(user.login) }

        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun insertPosts(posts: List<User>) {
        val lastPosition: Int = this.users.size
        this.users.addAll(posts)
        notifyItemRangeInserted(lastPosition, users.size)
    }

    class UserViewHolder(var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)
}

private object UsersDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean {
        val oldItemId = oldItem.login
        val newItemId = newItem.login
        return oldItemId == newItemId
    }

    override fun areContentsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean {
        return oldItem == newItem
    }
}