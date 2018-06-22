package com.alfred.heartnews.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.alfred.heartnews.R
import kotlinx.android.synthetic.main.activity_ab.*
import java.io.Serializable

/**
 * Created by alfred on 2018/6/5.
 */
class KtAActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ab)

        button.setOnClickListener {
            val ab = ABBean()

            var list = mutableListOf<ABBean>()
            for (i in 0..3) {
                ab.hello = "hello"
                ab.world = 100
            }

            val intent = Intent(this@KtAActivity, KtBActivity::class.java)
            intent.putExtra("test",list as Serializable)
            startActivity(intent)
        }
    }
}