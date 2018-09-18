package com.jim.multipos.utils

import android.content.Context
import java.util.regex.Pattern


fun convertDpToPx(context: Context, dp: Float): Float {
    return dp * context.getResources().getDisplayMetrics().density
}

fun convertPxToDp(context: Context, px: Float): Float {
    return px / context.resources.displayMetrics.density
}

fun isEmailValid(email: String): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(email)
    return matcher.matches()
}