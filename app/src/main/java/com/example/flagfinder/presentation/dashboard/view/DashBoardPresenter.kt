package com.example.flagfinder.presentation.dashboard.view

import android.content.Context
import com.example.flagfinder.base.MvpView
import com.example.flagfinder.clienthelper.ApiService
import com.example.flagfinder.clienthelper.ClientHelper
import com.example.flagfinder.model.FlagResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashBoardPresenter(val context: Context, val mvpView: DashBoardMvpView) {

    val apiService = ClientHelper().retroBuilder(context).create(ApiService::class.java)

    var listOfAllFlag = ArrayList<FlagResponseItem>()
    var tempList = ArrayList<FlagResponseItem>()

    fun getListOfFlags() {

        mvpView.showLoading()
        apiService.getRecords().enqueue(object : Callback<ArrayList<FlagResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<FlagResponseItem>>,
                response: Response<ArrayList<FlagResponseItem>>
            ) {
                listOfAllFlag = response.body()!!
                mvpView.displayListOfFlags(response.body()!!)


            }

            override fun onFailure(call: Call<ArrayList<FlagResponseItem>>, t: Throwable) {

            }

        })
    }

    fun search(string:String){
        tempList.clear()
        if(string!=null && string!=""){
            for (item in listOfAllFlag){
                if(item!!.name!!.contains(string,true)){
                    tempList.add(item)
                }
            }
            mvpView.displayListOfFlags(tempList)
        }
        else{
            mvpView.displayListOfFlags(listOfAllFlag)
        }

    }


    interface DashBoardMvpView : MvpView {
        fun displayListOfFlags(list: ArrayList<FlagResponseItem>)


    }
}