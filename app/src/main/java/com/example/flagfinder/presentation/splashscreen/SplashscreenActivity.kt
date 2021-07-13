package com.example.flagfinder.presentation.splashscreen

import android.content.Intent
import com.bumptech.glide.Glide
import com.example.flagfinder.R
import com.example.flagfinder.base.BaseActivity
import com.example.flagfinder.presentation.dashboard.view.DashBoardActivity
import kotlinx.android.synthetic.main.activity_splashscreen.*


class SplashscreenActivity:BaseActivity() {

    override val ActivityLayout: Int
        get() = R.layout.activity_splashscreen

    override fun initViews() {

        Glide.with(this)
            .load(R.drawable.appicon)
            .into(iv_icon)


        /****** Create Thread that will sleep for 5 seconds****/
        /****** Create Thread that will sleep for 5 seconds */
        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep((8 * 1000).toLong())

                    // After 5 seconds redirect to another intent
                    val i = Intent(baseContext, DashBoardActivity::class.java)
                    startActivity(i)

                    //Remove activity
                    finish()
                } catch (e: Exception) {
                }
            }
        }
        // start thread
        // start thread
        background.start()


    }
}