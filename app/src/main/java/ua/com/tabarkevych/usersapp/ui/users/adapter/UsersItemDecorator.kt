package ua.com.tabarkevych.usersapp.ui.users.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ua.com.tabarkevych.usersapp.extensions.dpToPx

class UsersItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }
        if (itemPosition == 0) {
            outRect.top = view.context.dpToPx(10).toInt()
        }
        outRect.bottom = view.context.dpToPx(10).toInt()
    }
}