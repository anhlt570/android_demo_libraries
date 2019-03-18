package com.example.ui

import android.content.Context

object Utility {
    fun convertDpToPx(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }
}
