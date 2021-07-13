package com.example.flagfinder.presentation.dashboard.adapter

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.flagfinder.R
import com.example.flagfinder.base.*
import com.example.flagfinder.model.FlagResponseItem
import com.mikepenz.fastadapter.adapters.ItemAdapter.items
import kotlinx.android.synthetic.main.adapter_flag.view.*


class FlagAdapter(val context: Context, itemModel: FlagResponseItem):FastAdapterItem<FlagResponseItem,FlagAdapter,FlagAdapter.ViewHolder>(itemModel) {



    private lateinit var mOnClick: (FlagResponseItem) -> Unit
    fun withOnClick(listner: (FlagResponseItem) -> Unit): FlagAdapter {
        mOnClick = listner
        return this
    }


    override fun getType(): Int {
        return 0
    }

    override fun getLayoutRes(): Int {
       return R.layout.adapter_flag
    }

    override fun getViewHolder(v: View): ViewHolder {
     return  ViewHolder(v)
    }

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        SVGBuilder.requestSVGbuilder(context).diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(Uri.parse(model.flag))
            .into(holder.iv_flag)


        holder.cl_item.setOnClickListener {
            mOnClick(model)
        }

        holder.flag_name.text = model.name



    }





    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val iv_flag:AppCompatImageView = itemView.iv_flag
        val cl_item:ConstraintLayout = itemView.cl_item
        val flag_name:AppCompatTextView = itemView.flag_name


    }
}