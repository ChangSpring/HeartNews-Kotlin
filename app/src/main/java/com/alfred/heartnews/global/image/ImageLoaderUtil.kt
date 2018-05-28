package com.alfred.heartnews.global.image

import android.app.Activity
import android.content.Context
import android.widget.ImageView

/**
 * Created by alfred on 2018/5/25.
 */
class ImageLoaderUtil() {
    companion object {

        /**
         * 用glide加载图片首先需要判断的,避免出现'You cannot start a load for a destroyed activity at '此类crash
         *
         * @param context
         * @return
         */
        fun isValidContextForGlide(context: Context?): Boolean {
            if (context == null) {
                return false
            }

            if (context is Activity) {
                if (context.isDestroyed || context.isFinishing) {
                    return false
                }
            }
            return true
        }


        fun display(context: Context, imageView: ImageView?, url: String) {
            if (imageView == null) {
                throw IllegalArgumentException("image view is null")
            }
//            GlideAPP.with(context).load(url)
//                    .crossFade().into(imageView)
        }



    }
}