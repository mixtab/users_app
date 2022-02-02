package ua.com.tabarkevych.usersapp.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import ua.com.tabarkevych.usersapp.R


val CIRCLE_IMAGE_RADIUS: Float = 50F
val USER_INFO_IMAGE_RADIUS: Float = 100F

fun ImageView.loadCircleImage(link: String, progress: ProgressBar) {
    progress.visibility = View.VISIBLE
    this.visibility = View.VISIBLE

    Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .transform(
                    CenterCrop(), GranularRoundedCorners(
                        CIRCLE_IMAGE_RADIUS.dpToPx(),
                        CIRCLE_IMAGE_RADIUS.dpToPx(),
                        CIRCLE_IMAGE_RADIUS.dpToPx(),
                        CIRCLE_IMAGE_RADIUS.dpToPx()
                    )
                )
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.ic_git_hub_logo)
        )
        .load(link)
        .transition(DrawableTransitionOptions.withCrossFade())
        .listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Drawable?>,
                isFirstResource: Boolean
            ): Boolean {
                progress.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any,
                target: Target<Drawable?>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                progress.visibility = View.GONE
                return false
            }
        })
        .into(this)
}

fun ImageView.loadUserInfoImage(link: String) {
    this.visibility = View.VISIBLE

    Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .transform(
                    CenterCrop(), GranularRoundedCorners(
                        USER_INFO_IMAGE_RADIUS.dpToPx(),
                        USER_INFO_IMAGE_RADIUS.dpToPx(),
                        USER_INFO_IMAGE_RADIUS.dpToPx(),
                        USER_INFO_IMAGE_RADIUS.dpToPx()
                    )
                )
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.ic_git_hub_logo)
        )
        .load(link)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
