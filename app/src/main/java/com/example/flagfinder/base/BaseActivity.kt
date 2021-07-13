package com.example.flagfinder.base

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog


abstract class BaseActivity : AppCompatActivity(),MvpView {

    abstract val ActivityLayout: Int

    abstract fun initViews()

    lateinit var mDialog:AppCompatDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityLayout)


        initViews()










    }



    override fun hideLoading() {


    }

    override fun showLoading() {

    }

    override fun showCallFailedDialog(message: String) {

    }

    override fun showMessageDialog(header: String, title: String, message: String) {

    }

    fun isShowing(isShowing:Boolean):Int{
        if(isShowing){
            return View.VISIBLE
        }
        return  View.GONE
    }


}