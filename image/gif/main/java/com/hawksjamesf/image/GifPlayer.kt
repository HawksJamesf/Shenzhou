package com.hawksjamesf.image

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ImageView
import java.io.FileDescriptor
import java.net.URI

/**
 * Copyright ® $ 2017
 * All right reserved.
 *
 * @author: hawks.jamesf
 * @since: Jan/07/2020  Tue
 */
class GifPlayer : BitmapListener {
    companion object {
        const val MSG_START = 0
        const val MSG_PAUSE = 1
        const val MSG_STOP = 2

        init {
            System.loadLibrary("gif_jni")
        }
    }

    var mEventHandler: EventHandler
    private var mImageView: ImageView? = null

    init {
        mEventHandler = when {
            Looper.myLooper() != null -> {
                EventHandler(this, Looper.myLooper()!!)
            }
            Looper.getMainLooper() != null -> {
                EventHandler(this, Looper.getMainLooper())
            }
            else -> {
                EventHandler(this, Looper.getMainLooper())
            }
        }
    }


    external fun setSource(assetName: String, manager: AssetManager)
    //    public external setDataSource(afd:AssetFileDescriptor )
    private external fun setDataSource(fd: FileDescriptor, offset: Long, length: Long)

    external fun setSource1(uriPath: String)
    external fun getGifWidth(): Int
    external fun getGifHeight(): Int
    external fun start()
    external fun setBitmap(bitmap: Bitmap?)
//    fun start() {
//        mEventHandler.sendEmptyMessage(MSG_START)
//    }

    fun pause() {
        mEventHandler.sendEmptyMessage(MSG_PAUSE)
    }

    fun stop() {
        mEventHandler.removeMessages(MSG_START)
        mEventHandler.removeMessages(MSG_PAUSE)
    }

    //来自网络的地址，来自sdcard的地址
    fun setSource(uri: URI) {
        setSource1(uri.toString())
    }

    fun createAndBind(context: Context, ivGif: ImageView, assetName: String, assetManager: AssetManager): GifPlayer {
        setSource(assetName, assetManager)//setSource 之后才能拿到width、height
        bindImageView(ivGif)
        return this
    }

    fun bindImageView(imageView: ImageView) {
        val bitmap = Bitmap.createBitmap(getGifWidth(), getGifHeight(), Bitmap.Config.ARGB_8888)
        setBitmap(bitmap)
        mImageView = imageView
    }


    inner class EventHandler : Handler {
        private var mPlayer: GifPlayer

        constructor(player: GifPlayer, looper: Looper) : super(looper) {
            mPlayer = player
        }

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MSG_START -> {
//                    setImageBitmap(updateBitmap())
                    sendEmptyMessage(MSG_START)
                }
                MSG_PAUSE -> {
                }
                MSG_STOP -> {
                }

            }
        }
    }

    override fun onBitmapAvailable(bitmap: Bitmap?, width: Int, height: Int) {

    }

    override fun onBitmapSizeChanged(bitmap: Bitmap?, width: Int, height: Int) {
    }

    override fun onBitmapDestroyed(bitmap: Bitmap?): Boolean {
        return true
    }

    override fun onBitmapUpdated(bitmap: Bitmap?) {
        mImageView?.setImageBitmap(bitmap)
    }
}