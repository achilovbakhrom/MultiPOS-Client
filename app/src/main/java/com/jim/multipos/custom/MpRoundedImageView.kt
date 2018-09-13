package com.jim.multipos.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ImageView.ScaleType.CENTER_CROP
import android.widget.ImageView.ScaleType.CENTER_INSIDE


class MpRoundedImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : AppCompatImageView(context, attrs, defStyle) {

    private var borderWidth: Float = 0.toFloat()
    private var canvasSize: Int = 0
    private var colorFilter_: ColorFilter? = null

    private var image: Bitmap? = null
    private var drawable_: Drawable? = null
    private var paint: Paint? = null
    private var paintBorder: Paint? = null
    private var paintBackground: Paint? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        // Init paint
        paint = Paint()
        paint!!.isAntiAlias = true

        paintBorder = Paint()
        paintBorder!!.isAntiAlias = true

        paintBackground = Paint()
        paintBackground!!.isAntiAlias = true

        val defaultBorderSize = DEFAULT_BORDER_WIDTH * context.resources.displayMetrics.density
        setBorderWidth(defaultBorderSize)
        setBorderColor(Color.TRANSPARENT)

        setBackgroundColor(Color.TRANSPARENT)

    }

    //region Set Attr Method
    fun setBorderWidth(borderWidth: Float) {
        this.borderWidth = borderWidth
        requestLayout()
        invalidate()
    }

    fun setBorderColor(borderColor: Int) {
        if (paintBorder != null)
            paintBorder!!.color = borderColor
        invalidate()
    }

    override fun setBackgroundColor(backgroundColor: Int) {
        if (paintBackground != null)
            paintBackground!!.color = backgroundColor
        invalidate()
    }

    override fun setColorFilter(colorFilter: ColorFilter) {
        if (this.colorFilter_ === colorFilter)
            return
        this.colorFilter_ = colorFilter
        drawable_ = null // To force re-update shader
        invalidate()
    }

    override fun getScaleType(): ImageView.ScaleType {
        val currentScaleType = super.getScaleType()
        return if (currentScaleType == null || currentScaleType != CENTER_INSIDE) CENTER_CROP else currentScaleType
    }

    override fun setScaleType(scaleType: ImageView.ScaleType) {
        if (scaleType != CENTER_CROP && scaleType != CENTER_INSIDE) {
            throw IllegalArgumentException(String.format("ScaleType %s not supported. " + "Just ScaleType.CENTER_CROP & ScaleType.CENTER_INSIDE are available for this library.", scaleType))
        } else {
            super.setScaleType(scaleType)
        }
    }
    //endregion

    //region Draw Method
    override fun onDraw(canvas: Canvas) {
        // Load the bitmap
        loadBitmap()

        // Check if image isn't null
        if (image == null)
            return

        if (!isInEditMode) {
            canvasSize = Math.min(canvas.width, canvas.height)
        }

        // circleCenter is the x or y of the view's center
        // radius is the radius in pixels of the cirle to be drawn
        // paint contains the shader that will texture the shape
        val circleCenter = (canvasSize - borderWidth * 2).toInt() / 2

        // Draw Border
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter + borderWidth, paintBorder)
        // Draw Circle background
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter.toFloat(), paintBackground)
        // Draw CircularImageView
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter.toFloat(), paint)
    }

    private fun loadBitmap() {
        if (drawable_ === drawable)
            return

        drawable_ = drawable
        image = drawableToBitmap(drawable)
        updateShader()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasSize = Math.min(w, h)
        if (image != null)
            updateShader()
    }


    private fun updateShader() {
        if (image == null)
            return

        // Create Shader
        val shader = BitmapShader(image!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        // Center Image in Shader
        var scale = 0f
        var dx = 0f
        var dy = 0f

        when (scaleType) {
            ImageView.ScaleType.CENTER_CROP -> if (image!!.width * height > width * image!!.height) {
                scale = height / image!!.height.toFloat()
                dx = (width - image!!.width * scale) * 0.5f
            } else {
                scale = width / image!!.width.toFloat()
                dy = (height - image!!.height * scale) * 0.5f
            }
            ImageView.ScaleType.CENTER_INSIDE -> if (image!!.width * height < width * image!!.height) {
                scale = height / image!!.height.toFloat()
                dx = (width - image!!.width * scale) * 0.5f
            } else {
                scale = width / image!!.width.toFloat()
                dy = (height - image!!.height * scale) * 0.5f
            }
        }

        val matrix = Matrix()
        matrix.setScale(scale, scale)
        matrix.postTranslate(dx, dy)
        shader.setLocalMatrix(matrix)

        // Set Shader in Paint
        paint!!.shader = shader

        // Apply colorFilter
        paint!!.colorFilter = colorFilter_
    }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        } else if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        try {
            // Create Bitmap object out of the drawable
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }
    //endregion

    //region Measure Method
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    private fun measureWidth(measureSpec: Int): Int {
        val result: Int
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            // The parent has determined an exact size for the child.
            result = specSize
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize
        } else {
            // The parent has not imposed any constraint on the child.
            result = canvasSize
        }

        return result
    }

    private fun measureHeight(measureSpecHeight: Int): Int {
        val result: Int
        val specMode = View.MeasureSpec.getMode(measureSpecHeight)
        val specSize = View.MeasureSpec.getSize(measureSpecHeight)

        if (specMode == View.MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = canvasSize
        }

        return result + 2
    }

    companion object {

        private val DEFAULT_BORDER_WIDTH = 1f
    }
}