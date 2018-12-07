package oupson.apng

import android.content.Context
import android.content.SharedPreferences
import android.graphics.*
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.uiThread
import oupson.apng.exceptions.NotApngException
import oupson.apng.utils.ApngAnimatorOptions
import oupson.apng.utils.Utils
import oupson.apng.utils.Utils.Companion.isApng
import java.io.File
import java.net.URL

/**
 * Class to play APNG
 */
class ApngAnimator(private val context: Context, targetView: ImageView) {
    var isPlaying = true
    var isLoading = false
    var isLoop = true
    var isApng = false
    var isAutoPlay = true

    private var frames = ArrayList<Frame>()
    private val generatedFrame = ArrayList<Bitmap>()

    private var bitmapBuffer: Bitmap? = null
    private var imageView: ImageView

    private var activeAnimation: CustomAnimationDrawable? = null
    private var doOnLoaded: (ApngAnimator) -> Unit = {}
    //    private var animationLoopListener: () -> Unit = {}
    var onLoadedListener: OnLoadedListener? = null
    private var durations: ArrayList<Float>? = null
    private var loadingUrl: String = ""
    private var loadNotApng = true
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("apngAnimator", Context.MODE_PRIVATE)

    init {
        this.imageView = targetView
    }

    /**
     * Specify if the library could load non apng file
     */
    fun loadNotApng(boolean: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("loadNotApng", boolean)
        editor.apply()
    }

    /**
     * Load into an imageview
     * @param imageView Image view selected.
     */
    fun loadInto(imageView: ImageView): ApngAnimator {
        this.imageView = imageView
        return this
    }

    /**
     * Load an APNG file and starts playing the animation.
     * @param file The file to load
     * @param speed The speed
     * @throws NotApngException
     */
    fun load(file: File, speed: Float? = null, apngAnimatorOptions: ApngAnimatorOptions? = null) {
        doAsync {
            val bytes = file.readBytes()
            if (isApng(bytes)) {
                isApng = true
                // Download PNG
                APNGDisassembler.disassemble(file.readBytes()).frames.apply {
                    draw(this)
                }
                setupAnimationDrawableAndStart()
            } else {
                if (loadNotApng) {
                    context.runOnUiThread {
                        imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
                    }
                } else {
                    throw NotApngException()
                }
            }
        }
    }

    /**
     * Load an APNG file and starts playing the animation.
     * @param uri The uri to load
     * @param speed The speed
     * @throws NotApngException
     */
    fun load(uri: Uri) {
        doAsync {
            val bytes = context.contentResolver.openInputStream(uri).readBytes()
            if (isApng(bytes)) {
                isApng = true
                // Download PNG
                APNGDisassembler.disassemble(bytes).frames.apply {
                    draw(this)
                }
                setupAnimationDrawableAndStart()
            } else {
                if (loadNotApng) {
                    context.runOnUiThread {
                        imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
                    }
                } else {
                    throw NotApngException()
                }
            }
        }
    }

    /**
     * Load an APNG file and starts playing the animation.
     * @param url URL to load.
     * @param speed The speed
     * @throws NotApngException
     */
    fun loadUrl(url: URL) {
        doAsync(exceptionHandler = { e -> e.printStackTrace() }) {
            // Download PNG
            val start = System.currentTimeMillis()
            isLoading = true
            Loader.load(context, url).apply {
                if (url.toString() != this@ApngAnimator.loadingUrl) {
                    return@apply
                }
                isLoading = false
                Log.d("anhlt11", "loading time: ${(System.currentTimeMillis() - start).toFloat() / 1000f}")
                if (isApng(this)) {
                    isApng = true
                    APNGDisassembler.disassemble(this).frames.apply {
                        draw(this)
                    }
                    setupAnimationDrawableAndStart()
                } else {
                    if (loadNotApng) {
                        context.runOnUiThread {
                            //                            imageView.scaleType = this@ApngAnimator.scaleType ?: ImageView.ScaleType.FIT_CENTER

                            imageView.setImageBitmap(BitmapFactory.decodeByteArray(this@apply, 0, this@apply.size))
                        }
                    } else {
                        throw NotApngException()
                    }
                }
            }


        }
    }

    /**
     * Load an APNG file and starts playing the animation.
     * @param byteArray ByteArray of the file
     * @param speed The speed
     * @throws NotApngException
     */
    fun load(byteArray: ByteArray) {
        doAsync {
            if (isApng(byteArray)) {
                isApng = true
                APNGDisassembler.disassemble(byteArray).frames.apply {
                    draw(this)
                }
                setupAnimationDrawableAndStart()
            } else {
                if (loadNotApng) {
                    context.runOnUiThread {
                        if (imageView.drawable != null) {
                            imageView.setImageDrawable(null)
                        }
                        imageView.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size))
                    }
                } else {
                    throw NotApngException()
                }
            }
        }
    }

    /**
     * Load an APNG file
     * @param string Path of the file.
     * @param speed The speed
     * @throws NotApngException
     */
    fun load(string: String, speed: Float? = null, apngAnimatorOptions: ApngAnimatorOptions? = null) {
        doAsync {
            if (string.contains("http") || string.contains("https")) {
                val url = URL(string)
                Log.d("anhlt11", "loading: $string")
                loadingUrl = string
                loadUrl(url)
            } else if (File(string).exists()) {
                var pathToLoad = if (string.startsWith("content://")) string else "file://$string"
                pathToLoad = pathToLoad.replace("%", "%25").replace("#", "%23")
                val bytes = context.contentResolver.openInputStream(Uri.parse(pathToLoad)).readBytes()
                if (isApng(bytes)) {
                    load(bytes)
                } else {
                    if (loadNotApng) {
                        context.runOnUiThread {
                            imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
                        }
                    } else {
                        throw NotApngException()
                    }
                }
            }
        }
    }

    /**
     * Sets up the animation drawable and any required listeners. The animation will automatically start.
     */
    private fun setupAnimationDrawableAndStart() {
        doAsync {
            activeAnimation = toAnimationDrawable()
            activeAnimation?.isOneShot = !isLoop
            uiThread {
                imageView.apply {
                    setImageDrawable(activeAnimation)
                }
                doOnLoaded(this@ApngAnimator)
                onLoadedListener?.onLoaded()
                if (isAutoPlay) {
                    activeAnimation?.start()
                    isPlaying = true
                }
            }
        }
    }

    /**
     * Draw frames
     */
    private fun draw(extractedFrame: ArrayList<Frame>) {
        // Set last frame
        durations = ArrayList()
        frames.clear()
        frames.addAll(extractedFrame)
        bitmapBuffer = Bitmap.createBitmap(frames[0].maxWidth!!, frames[0].maxHeight!!, Bitmap.Config.ARGB_8888)
        generatedFrame.clear()
        for (i in 0 until frames.size) {
            // Iterator
            val it = frames[i]
            // Current bitmap for the frame
            val btm = Bitmap.createBitmap(frames[0].maxWidth!!, frames[0].maxHeight!!, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(btm)
            val current = BitmapFactory.decodeByteArray(it.byteArray, 0, it.byteArray.size).copy(Bitmap.Config.ARGB_8888, true)
            // Write buffer to canvas
            canvas.drawBitmap(bitmapBuffer, 0f, 0f, null)
            // Clear current frame rect
            // If `blend_op` is APNG_BLEND_OP_SOURCE all color components of the frame, including alpha, overwrite the current contents of the frame's output buffer region.
            if (it.blend_op == Utils.Companion.blend_op.APNG_BLEND_OP_SOURCE) {
                canvas.drawRect(it.x_offsets!!.toFloat(),
                        it.y_offsets!!.toFloat(),
                        it.x_offsets!! + current.width.toFloat(),
                        it.y_offsets!! + current.height.toFloat(),
                        { val paint = Paint(); paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR); paint }())
            }
            // Draw the bitmap
            canvas.drawBitmap(current, it.x_offsets!!.toFloat(), it.y_offsets!!.toFloat(), null)
            generatedFrame.add(btm)
            // Don't add current frame to bitmap buffer
            if (frames[i].dispose_op == Utils.Companion.dispose_op.APNG_DISPOSE_OP_PREVIOUS) {
                //Do nothings
            }
            // Add current frame to bitmap buffer
            // APNG_DISPOSE_OP_BACKGROUND: the frame's region of the output buffer is to be cleared to fully transparent black before rendering the next frame.
            else if (it.dispose_op == Utils.Companion.dispose_op.APNG_DISPOSE_OP_BACKGROUND) {
                val res = Bitmap.createBitmap(frames[0].maxWidth!!, frames[0].maxHeight!!, Bitmap.Config.ARGB_8888)
                val can = Canvas(res)
                can.drawBitmap(btm, 0f, 0f, null)
                can.drawRect(it.x_offsets!!.toFloat(), it.y_offsets!!.toFloat(), it.x_offsets!! + it.width.toFloat(), it.y_offsets!! + it.height.toFloat(), { val paint = Paint(); paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR); paint }())
                bitmapBuffer = res
            } else {
                bitmapBuffer = btm
            }
            durations?.add(it.delay)

        }
    }

    /**
     * Pause the animation
     */
    fun pause() {
        if (isApng) {
            isPlaying = false
            activeAnimation?.stop()
//            val animResume = CustomAnimationDrawable()
//            animResume.isOneShot = isLoop
//            val currentFrame = activeAnimation!!.current
//            val dura = ArrayList<Float>()
//            frameLoop@ for (i in 0 until anim?.numberOfFrames!!) {
//                val checkFrame = activeAnimation!!.getFrame(i)
//                if (checkFrame === currentFrame) {
//                    for (k in i until activeAnimation!!.numberOfFrames) {
//                        val frame = activeAnimation!!.getFrame(k)
//                        animResume.addFrame(frame, (durations!![k] / (speed ?: 1f)).toInt())
//                        dura.add(durations!![k])
//                    }
//                    for (k in 0 until i) {
//                        val frame = activeAnimation!!.getFrame(k)
//                        animResume.addFrame(frame, (durations!![k] / (speed ?: 1f)).toInt())
//                        dura.add(durations!![k])
//                    }
//                    activeAnimation = animResume
//                    imageView.setImageDrawable(activeAnimation)
//                    activeAnimation?.setOnAnimationLoopListener(animationLoopListener)
//                    imageView.invalidate()
//                    durations = dura
//                    break@frameLoop
//                }
//            }
        }
    }

    /**
     * Play the animation
     */
    fun play() {
        if (isApng) {
            isPlaying = true
            activeAnimation?.start()
        }
    }

    fun playAndHide() {
        if (isApng) {
            isPlaying = true
            activeAnimation?.start()
            Handler().postDelayed({
                stop()
            }, activeAnimation?.getTotalDuration()!!.toLong() + 500)
        }
    }

    fun stop() {
        if (isApng) {
            isPlaying = false
            activeAnimation?.stop()
            imageView.setImageDrawable(null)
        }
    }

    /**
     * Set animation loop listener
     * @param animationLoopListener The animation loop listener.
     */
//    fun setOnAnimationLoopListener(animationLoopListener: () -> Unit) {
//        if (isApng) {
//            this.animationLoopListener = animationLoopListener
//            anim?.setOnAnimationLoopListener(animationLoopListener)
//        }
//    }

//    /**
//     * Execute on loaded
//     */
    fun onLoaded(f: (ApngAnimator) -> Unit) {
        doOnLoaded = f
    }
//
//    fun centerCrop(boolean: Boolean) {
//        if (isApng) {
//            if (boolean) {
//                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//                pause()
//                play()
//            } else {
//                imageView.scaleType = null
//                pause()
//                play()
//            }
//        }
//    }

    /**
     * Converts the generated frames into an animation drawable ([CustomAnimationDrawable])
     * in the APNG will be used instead.
     */
    private fun toAnimationDrawable(): CustomAnimationDrawable {
        if (isApng) {
            return CustomAnimationDrawable().apply {
                for (i in 0 until generatedFrame.size) {
                    this.addFrame(BitmapDrawable(generatedFrame[i]), ((frames[i].delay)).toInt())
                }
            }
        } else {
            throw NotApngException()
        }
    }
}