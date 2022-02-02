package ua.com.tabarkevych.usersapp.extensions

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

fun Context.dpToPx(dp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics

    )
}

fun Float.dpToPx(): Float {
    return (this * Resources.getSystem().displayMetrics.density)
}