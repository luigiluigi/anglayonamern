package com.example.flagfinder.presentation.locationmap.view

import android.content.Context
import android.util.Log
import com.example.flagfinder.base.MvpView
import com.example.flagfinder.model.FlagResponseItem
import com.google.android.gms.maps.model.LatLng

class LocationMapPresenter(context: Context,val mvpView: LocationMapMvpView) {


    lateinit var mFlagResponseItem: FlagResponseItem


    fun setFlagResponseItem(item:FlagResponseItem){
        mFlagResponseItem = item
        setSelectedFlagLocation()

    }


    private fun setSelectedFlagLocation(){
        Log.d("androidruntime","puta")
        val location = LatLng(mFlagResponseItem!!.latlng!![0]!!, mFlagResponseItem!!.latlng!![1]!!)
        mvpView.moveToSelectedFlag(location,mFlagResponseItem.flag!!)
    }

    fun displayDetails(){
        mvpView.displayDetails(mFlagResponseItem)
    }





    interface LocationMapMvpView:MvpView{
        fun moveToSelectedFlag(location:LatLng,imageUrl:String)

        fun displayDetails(item:FlagResponseItem)


    }
}