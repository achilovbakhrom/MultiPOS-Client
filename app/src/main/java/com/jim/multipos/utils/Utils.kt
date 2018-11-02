package com.jim.multipos.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.jim.multipos.customView.recyclerView.adapter.BaseViewHolder
import java.util.regex.Pattern
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.os.Environment.DIRECTORY_PICTURES
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


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

fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
    return Uri.parse(path)
}


fun saveImageToUri(photo: Bitmap, context: Context) : File {
    // Assume block needs to be inside a Try/Catch block.
    val path = Environment.getExternalStorageDirectory().toString()
    var fOut: OutputStream? = null
    val counter = 0
    val file = File(path, "CompanyPhoto$counter.jpg") // the File to save , append increasing numeric counter to prevent files from getting overwritten.
    fOut = FileOutputStream(file)
    photo.compress(Bitmap.CompressFormat.JPEG, 85, fOut) // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
    fOut.flush() // Not really required
    fOut.close() // do not forget to close the stream

    MediaStore.Images.Media.insertImage(context.contentResolver, file.absolutePath, file.name, file.name)
    return file
}