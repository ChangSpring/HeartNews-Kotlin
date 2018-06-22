package com.alfred.heartnews.ui

import android.app.Activity
import android.os.Bundle
import com.alfred.heartnews.R
import kotlinx.android.synthetic.main.activity_ab.*

/**
 * Created by alfred on 2018/6/5.
 */

class KtBActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ab)

        var list = intent.getSerializableExtra("test") as ArrayList<ABBean>
        var string = StringBuffer()
        for (item in list) {
            string.append(item.hello + " " + item.world)
        }
//        var abc =  (intent.getSerializableExtra("test") as ABBean).hello
//        var def = (intent.getSerializableExtra("test") as ABBean) .world
        textview.text = if (string.isEmpty()) " null " else string
    }
}
