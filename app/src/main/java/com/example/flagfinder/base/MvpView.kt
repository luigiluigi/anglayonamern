package com.example.flagfinder.base

interface MvpView {

    fun showLoading()

    fun hideLoading()

    fun showCallFailedDialog(message:String)

    fun showMessageDialog(header:String,title:String,message:String)
}