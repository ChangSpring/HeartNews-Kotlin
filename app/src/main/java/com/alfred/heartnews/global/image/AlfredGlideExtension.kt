package com.alfred.heartnews.global.image

import com.alfred.heartnews.R
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Created by alfred on 2018/5/25.
 */
@GlideExtension
class AlfredGlideExtension private constructor() {

    companion object {
        @GlideOption
        fun normalImage(option: RequestOptions) {
            option.centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_loading)
                    .error(R.drawable.ic_empty_picture)
        }
    }


}