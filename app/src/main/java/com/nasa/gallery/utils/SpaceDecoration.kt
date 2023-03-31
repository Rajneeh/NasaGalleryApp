package com.nasa.gallery.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

internal class SpaceDecoration(private val spacing: Int, private val includeEdge: Boolean) : ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val spanCount = getTotalSpanCount(parent)

        /*item position*/
        val position = parent.getChildAdapterPosition(view!!)
        /*item column*/
        val column = position % spanCount
        if (includeEdge) {
            /*spacing - column * ((1f / spanCount) * spacing)*/
            outRect.left = spacing - column * spacing / spanCount

            /*(column + 1) * ((1f / spanCount) * spacing)*/outRect.right =
                (column + 1) * spacing / spanCount

            /*top edge*/if (position < spanCount) outRect.top = spacing

            /*item bottom*/outRect.bottom = spacing
        } else {
            /*column * ((1f / spanCount) * spacing)*/
            outRect.left = column * spacing / spanCount

            /*spacing - (column + 1) * ((1f /  spanCount) * spacing)*/outRect.right =
                spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                /*item top*/
                outRect.top = spacing
            }
        }
    }

    private fun getTotalSpanCount(parent: RecyclerView): Int {
        val layoutManager = parent.layoutManager
        return if (layoutManager is GridLayoutManager) layoutManager.spanCount else 1
    }
}