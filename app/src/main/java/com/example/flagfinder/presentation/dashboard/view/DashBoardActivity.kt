package com.example.flagfinder.presentation.dashboard.view

import android.util.Log
import android.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.flagfinder.R
import com.example.flagfinder.base.BaseActivity
import com.example.flagfinder.model.FlagResponseItem
import com.example.flagfinder.presentation.dashboard.adapter.FlagAdapter
import com.example.flagfinder.presentation.locationmap.view.LocationMapActivity
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter

import com.mikepenz.itemanimators.SlideDownAlphaAnimator
import kotlinx.android.synthetic.main.activity_dashboard.*


class DashBoardActivity : BaseActivity(), DashBoardPresenter.DashBoardMvpView {


    lateinit var mPresenter: DashBoardPresenter
    lateinit var mAdapter: FastItemAdapter<FlagAdapter>


    override val ActivityLayout: Int
        get() = R.layout.activity_dashboard


    override fun initViews() {
        mPresenter = DashBoardPresenter(this, this)
        mAdapter = FastItemAdapter()
        rv_flag.adapter = mAdapter
        rv_flag.layoutManager = LinearLayoutManager(this)
        rv_flag.itemAnimator = SlideDownAlphaAnimator()


        Glide.with(this)
            .load(R.drawable.earth)
            .into(iv_earth)

        mPresenter.getListOfFlags()



        et_search.addTextChangedListener {
            mPresenter.search(it.toString())
        }


    }





    override fun displayListOfFlags(list: ArrayList<FlagResponseItem>) {
        Log.d("androidruntime", "dumaan dito")
        mAdapter.clear()
        list.map {
            mAdapter.add(FlagAdapter(this, it!!)
                .withOnClick {
                    startActivity(LocationMapActivity.getIntent(this, it))
                })
        }
        mAdapter.notifyAdapterDataSetChanged()
    }
}