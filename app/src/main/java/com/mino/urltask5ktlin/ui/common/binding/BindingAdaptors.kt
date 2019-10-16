package com.mino.urltask5ktlin.ui.common.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mino.urltask5ktlin.R
import com.mino.urltask5ktlin.utils.URL_AVAILABLE
import com.mino.urltask5ktlin.utils.URL_LOADING
import com.mino.urltask5ktlin.utils.URL_UNAVAILABLE

object BindingAdaptors {

    @BindingAdapter("app:urlIconSrc")
    fun loadUrlIcon(
        imageView: ImageView,
        availability: Int
    ) {

        when (availability) {
            URL_LOADING -> {
                imageView.setImageResource(R.drawable.progress)
            }
            URL_AVAILABLE -> {
                imageView.setImageResource(R.drawable.available)
            }
            URL_UNAVAILABLE -> {
                imageView.setImageResource(R.drawable.unavailable)
            }
        }
    }

    @BindingAdapter(
        value = ["swipeEnabled", "drawableSwipeLeft", "drawableSwipeRight", "bgColorSwipeLeft", "bgColorSwipeRight", "onItemSwipeLeft", "onItemSwipeRight"],
        requireAll = false
    )
    fun setItemSwipeToRecyclerView(
        recyclerView: RecyclerView,
        swipeEnabled: Boolean,
        drawableSwipeLeft: Drawable,
        drawableSwipeRight: Drawable,
        bgColorSwipeLeft: Int,
        bgColorSwipeRight: Int,
        onItemSwipeLeft: SwipeItemTouchHelperCallback.OnItemSwipeListener,
        onItemSwipeRight: SwipeItemTouchHelperCallback.OnItemSwipeListener
    ) {

        val swipeCallback =
            SwipeItemTouchHelperCallback.Builder(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
                .bgColorSwipeLeft(bgColorSwipeLeft)
                .bgColorSwipeRight(bgColorSwipeRight)
                .drawableSwipeLeft(drawableSwipeLeft)
                .drawableSwipeRight(drawableSwipeRight)
                .setSwipeEnabled(swipeEnabled)
                .onItemSwipeLeftListener(onItemSwipeLeft)
                .onItemSwipeRightListener(onItemSwipeRight)
                .build()

        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}